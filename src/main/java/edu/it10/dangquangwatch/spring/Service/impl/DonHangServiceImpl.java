package edu.it10.dangquangwatch.spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.EmptyOrNullListException;
import edu.it10.dangquangwatch.spring.AppCustomException.OrderItemException;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderStatus;
import edu.it10.dangquangwatch.spring.repository.DonHangRepository;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import edu.it10.dangquangwatch.spring.service.DonghoService;
import edu.it10.dangquangwatch.spring.service.EmailService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import edu.it10.dangquangwatch.spring.service.TrangsucService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DonHangServiceImpl implements DonHangService {
  private static final Logger log = LoggerFactory.getLogger(DonHangServiceImpl.class);
  @Autowired
  DonHangRepository donHangRepository;
  @Autowired
  ChiTietDonHangServiceImpl ctdhService;
  @Autowired
  EmailService emailService;
  @Autowired
  DonghoService donghoService;
  @Autowired
  KinhMatService kinhMatService;
  @Autowired
  ButkyService butKyService;
  @Autowired
  TrangsucService trangSucService;
  @Autowired
  PhuKienService phuKienService;

  @Override
  public Page<DonHang> getAllDonHang(int page) {
    return donHangRepository.findAll(PageRequest.of(page, 10));
  }

  @Override
  public Page<DonHang> searchDonHang(String username, String hoten, String diachi, String tensanpham, String tinhtrang,
      String thanhtoan, Integer tongtien, String from, String to, int page) {
    return donHangRepository.searchDonHang(username, hoten, diachi, tensanpham, tinhtrang, thanhtoan, tongtien, from,
        to,
        PageRequest.of(page, 10));
  }

  @Override
  public Optional<DonHang> findDonHangById(int madonhang) {
    return donHangRepository.findById(madonhang);
  }

  @Override
  @Transactional
  public void addDonHang(DonHang donHang) {
    if (donHang.getItems() != null) {
      if (donHang.getItems().size() > 0) {
        for (ChiTietDonHang item : donHang.getItems()) {
          checkItemQuantity(item);
        }

        DonHang result = donHangRepository.save(donHang);

        for (ChiTietDonHang item : donHang.getItems()) {
          saveOrderItem(item, result);
        }

        try {
          emailService.sendOrderSuccessEmail(donHangRepository.findById(result.getMaDonHang()).get());
        } catch (MessagingException e) {
          log.warn("WARNING - Cannot send order confirm to {}", result.getTaikhoan().getUsername());
        }
      } else {
        throw new EmptyOrNullListException("Phải có ít nhất 1 đơn hàng!");
      }
    }
  }

  void saveOrderItem(ChiTietDonHang item, DonHang result) {
    item.setDonhang(result);
    item.setNGAYTHEM(result.getNGAYTHEM());
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findDonghoById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        dongHo.setSoluong(dongHo.getSoluong() - item.getSoLuong());
        donghoService.saveDongho(dongHo);
        break;
      case "phukien":
        PhuKien phuKien = phuKienService.findPhuKienById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        phuKien.setSoLuong(phuKien.getSoLuong() - item.getSoLuong());
        phuKienService.savePhuKien(phuKien);
        break;
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findKinhMatById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        kinhMat.setSoLuong(kinhMat.getSoLuong() - item.getSoLuong());
        kinhMatService.saveKinhMat(kinhMat);
        break;
      case "butky":
        Butky butKy = butKyService.findButkyById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        butKy.setSoluong(butKy.getSoluong() - item.getSoLuong());
        butKyService.saveButky(butKy);
        break;
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findTrangsucById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        trangSuc.setSoluong(trangSuc.getSoluong() - item.getSoLuong());
        trangSucService.saveTrangsuc(trangSuc);
        break;
      default:
        throw new OrderItemException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
    }
    ctdhService.saveCTDH(item);
  }

  void checkItemQuantity(ChiTietDonHang item) {
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findDonghoById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        if (dongHo.getSoluong() < item.getSoLuong()) {
          throw new OrderItemException("Số lượng mua vượt giới hạn, mã đồng hồ: " + item.getMaSanPham());
        }
        break;
      case "phukien":
        PhuKien phuKien = phuKienService.findPhuKienById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        if (phuKien.getSoLuong() < item.getSoLuong()) {
          throw new OrderItemException("Số lượng mua vượt giới hạn, mã phụ kiện: " + item.getMaSanPham());
        }
        break;
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findKinhMatById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        if (kinhMat.getSoLuong() < item.getSoLuong()) {
          throw new OrderItemException("Số lượng mua vượt giới hạn, mã kính mắt: " + item.getMaSanPham());
        }
        break;
      case "butky":
        Butky butKy = butKyService.findButkyById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        if (butKy.getSoluong() < item.getSoLuong()) {
          throw new OrderItemException("Số lượng mua vượt giới hạn, mã bút ký: " + item.getMaSanPham());
        }
        break;
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findTrangsucById(item.getMaSanPham())
            .orElseThrow(() -> new OrderItemException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        if (trangSuc.getSoluong() < item.getSoLuong()) {
          throw new OrderItemException("Số lượng mua vượt giới hạn, mã trang sức: " + item.getMaSanPham());
        }
        break;
      default:
        throw new OrderItemException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
    }
  }

  @Override
  @Transactional
  public void updateDonHang(DonHang donHang) {
    donHangRepository.save(donHang);
  }

  @Override
  @Transactional
  public void deleteDonHang(int madonhang) {
    donHangRepository.deleteById(madonhang);
  }

  @Override
  public Page<DonHang> getMyDonHang(String searchStr, String tinhtrang, String thanhtoan, String username, String from,
      String to, int page) {
    return donHangRepository.getMyDonHang(searchStr, tinhtrang, thanhtoan, username, from, to,
        PageRequest.of(page, 10));
  }

  @Override
  public void removeSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();
      DonHang donhang = ctdh.getDonhang();

      donhang.setTongTien(donhang.getTongTien() - (ctdh.getGiaTien() * ctdh.getSoLuong()));

      donHangRepository.save(donhang);
      ctdhService.deleteCTDH(maCTDH);
    }
  }

  @Override
  public void addSP(int madonhang, ChiTietDonHang ctdh) {
    Optional<DonHang> data = donHangRepository.findById(madonhang);

    if (data.isPresent()) {
      DonHang donhang = data.get();
      ctdh.setDonhang(donhang);
      Integer oldTonTien = donhang.getTongTien();
      donhang.setTongTien(oldTonTien + (ctdh.getGiaTien() * ctdh.getSoLuong()));

      donHangRepository.save(donhang);
      ctdhService.saveCTDH(ctdh);
    }
  }

  @Override
  public void decSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();

      if (ctdh.getSoLuong() > 1) {
        DonHang donhang = ctdh.getDonhang();
        ctdh.setSoLuong(ctdh.getSoLuong() - 1);
        donhang.setTongTien(donhang.getTongTien() - ctdh.getGiaTien());

        donHangRepository.save(donhang);
        ctdhService.saveCTDH(ctdh);
      } else {
        removeSP(maCTDH);
      }
    }
  }

  @Override
  public void incSP(int maCTDH) {
    Optional<ChiTietDonHang> data = ctdhService.findCTDHById(maCTDH);

    if (data.isPresent()) {
      ChiTietDonHang ctdh = data.get();

      DonHang donhang = ctdh.getDonhang();
      ctdh.setSoLuong(ctdh.getSoLuong() + 1);
      donhang.setTongTien(donhang.getTongTien() + ctdh.getGiaTien());

      donHangRepository.save(donhang);
      ctdhService.saveCTDH(ctdh);
    }
  }

  @Override
  public void updateStatus(Integer madonhang, OrderStatus status) {
    Optional<DonHang> opt = donHangRepository.findById(madonhang);
    ServiceException exception = new ServiceException();
    exception.setPath("/admin/donhang");

    if (opt.isPresent()) {
      DonHang currentData = opt.get();
      if (currentData.getTinhTrang().equals(OrderStatus.CANCELLED)) {
        exception.setMessage("Đơn hàng " + madonhang + " đã bị hủy!");
        throw exception;
      } else {
        currentData.setTinhTrang(status);
        updateDonHang(currentData);
      }

    } else {
      exception.setMessage("Không tìm thấy đơn hàng mã: " + madonhang);
      throw exception;
    }
  }
}
