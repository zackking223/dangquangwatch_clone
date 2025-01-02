package edu.it10.dangquangwatch.spring.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import edu.it10.dangquangwatch.spring.AppCustomException.OrderException;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.entity.ChiTietDonHang;
import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderPaymentStatus;
import edu.it10.dangquangwatch.spring.entity.enumeration.OrderStatus;
import edu.it10.dangquangwatch.spring.entity.response.ApiResponse;
import edu.it10.dangquangwatch.spring.helper.DateStringHelper;
import edu.it10.dangquangwatch.spring.notification.NotificationBody;
import edu.it10.dangquangwatch.spring.notification.NotificationType;
import edu.it10.dangquangwatch.spring.payment.GlobalCardInfo;
import edu.it10.dangquangwatch.spring.payment.LocalCardInfo;
import edu.it10.dangquangwatch.spring.payment.PaymentService;
import edu.it10.dangquangwatch.spring.repository.DonHangRepository;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import edu.it10.dangquangwatch.spring.service.DonHangService;
import edu.it10.dangquangwatch.spring.service.DonghoService;
import edu.it10.dangquangwatch.spring.service.EmailService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
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
  @Autowired
  SimpMessagingTemplate messagingTemplate;
  @Autowired
  PaymentService paymentService;
  @Autowired
  ThongKeService thongKeService;

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

  DonHang checkForExceptionAndSetPrice(DonHang donHang) {
    if (donHang.getTaikhoan() == null) {
      throw new OrderException("Không tìm thấy tài khoản");
    }

    if (donHang.getItems() == null) {
      throw new OrderException("Phải có ít nhất 1 sản phẩm!");
    }

    if (donHang.getItems().size() < 1) {
      throw new OrderException("Phải có ít nhất 1 sản phẩm!");
    }

    if (donHang.getTaikhoan().getSodienthoai() == null) {
      throw new OrderException("Vui lòng thêm số điện thoại cho tài khoản!");
    }

    if (donHang.getTaikhoan().getDiachi().equals("Chưa có") || donHang.getTaikhoan().getDiachi() == null) {
      throw new OrderException("Vui lòng địa chỉ cho tài khoản!");
    }

    if (donHang.getItems() != null && !donHang.getItems().isEmpty()) {
      Integer tongTien = 0;
      for (ChiTietDonHang item : donHang.getItems()) {
        Integer giaTien = checkItemQuantityAndPrice(item);
        item.setGiaTien(giaTien);
        tongTien += giaTien * item.getSoLuong();
      }

      donHang.setTongTien(tongTien);
    }

    return donHang;
  }

  @Override
  public DonHang validate(DonHang donHang, TaiKhoan taiKhoan) {
    donHang = checkForExceptionAndSetPrice(donHang);

    donHang.setTinhTrang(OrderStatus.WaitForApproval);

    if (donHang.getDiaChi() == null || donHang.getDiaChi().isEmpty()) {
      donHang.setDiaChi(taiKhoan.getDiachi());
    }

    if (donHang.getGhiChu() == null || donHang.getGhiChu().isEmpty()) {
      donHang.setGhiChu("Không có");
    }

    if (donHang.getNGAYTHEM() == null) {
      donHang.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());
    }

    donHang.setTaikhoan(taiKhoan);

    return donHang;
  }

  @Override
  @Transactional
  public DonHang save(DonHang donHang, OrderStatus status, OrderPaymentStatus paymentStatus) {
    donHang = checkForExceptionAndSetPrice(donHang);
    donHang.setTinhTrang(status);
    donHang.setThanhToan(paymentStatus);

    DonHang result = donHangRepository.save(donHang);

    for (ChiTietDonHang item : donHang.getItems()) {
      saveOrderItem(item, result);
    }

    return result;
  }

  @Override
  @Transactional
  public ApiResponse checkOut(DonHang donHang) {
    donHang = checkForExceptionAndSetPrice(donHang);
    donHang.setThanhToan(OrderPaymentStatus.ON_RECIEVED);

    DonHang result = donHangRepository.save(donHang);

    for (ChiTietDonHang item : donHang.getItems()) {
      saveOrderItem(item, result);
    }

    try {
      emailService.sendOrderSuccessEmail(result);
    } catch (MessagingException e) {
      log.warn("WARNING - Cannot send order confirm to {}", result.getTaikhoan().getUsername());
    }

    // Gửi thông báo cho quản trị viên
    NotificationBody notification = new NotificationBody();
    notification.setMessage(donHang.getTaikhoan().getUsername() + " đã đặt một đơn hàng mới!");
    notification.setTitle("Đơn hàng mới");
    notification.setType(NotificationType.SUCCESS);
    notification.setUrl("/admin/donhang/?email=" + donHang.getTaikhoan().getUsername());

    messagingTemplate.convertAndSend("/topic/notifications", notification);
    return new ApiResponse(true, "Đặt hàng thành công!");
  }

  @Override
  @Transactional
  public ApiResponse checkOut(DonHang donHang, GlobalCardInfo cardInfo) {
    donHang = checkForExceptionAndSetPrice(donHang);
    paymentService.processPayment(cardInfo);
    donHang.setThanhToan(OrderPaymentStatus.PAID);
    DonHang result = donHangRepository.save(donHang);

    for (ChiTietDonHang item : donHang.getItems()) {
      saveOrderItem(item, result);
    }

    try {
      emailService.sendOrderSuccessEmail(donHangRepository.findById(result.getMaDonHang()).get());
    } catch (MessagingException e) {
      log.warn("WARNING - Cannot send order confirm to {}", result.getTaikhoan().getUsername());
    }

    // Gửi thông báo cho quản trị viên
    NotificationBody notification = new NotificationBody();
    notification.setMessage(donHang.getTaikhoan().getUsername() + " đã đặt một đơn hàng mới!");
    notification.setTitle("Đơn hàng mới");
    notification.setType(NotificationType.SUCCESS);
    notification.setUrl("/admin/donhang/?email=" + donHang.getTaikhoan().getUsername());

    messagingTemplate.convertAndSend("/topic/notifications", notification);
    return new ApiResponse(true, "Đặt hàng thành công!");
  }

  @Override
  @Transactional
  public ApiResponse checkOut(DonHang donHang, LocalCardInfo cardInfo) {
    donHang = checkForExceptionAndSetPrice(donHang);
    paymentService.processPayment(cardInfo);
    donHang.setThanhToan(OrderPaymentStatus.PAID);
    DonHang result = donHangRepository.save(donHang);

    for (ChiTietDonHang item : donHang.getItems()) {
      saveOrderItem(item, result);
    }

    try {
      emailService.sendOrderSuccessEmail(donHangRepository.findById(result.getMaDonHang()).get());
    } catch (MessagingException e) {
      log.warn("WARNING - Cannot send order confirm to {}", result.getTaikhoan().getUsername());
    }

    // Gửi thông báo cho quản trị viên
    NotificationBody notification = new NotificationBody();
    notification.setMessage(donHang.getTaikhoan().getUsername() + " đã đặt một đơn hàng mới!");
    notification.setTitle("Đơn hàng mới");
    notification.setType(NotificationType.SUCCESS);
    notification.setUrl("/admin/donhang/?email=" + donHang.getTaikhoan().getUsername());

    messagingTemplate.convertAndSend("/topic/notifications", notification);
    return new ApiResponse(true, "Đặt hàng thành công!");
  }

  @Transactional
  void saveOrderItem(ChiTietDonHang item, DonHang result) {
    item.setDonhang(result);
    item.setNGAYTHEM(result.getNGAYTHEM());
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        dongHo.setSoluong(dongHo.getSoluong() - item.getSoLuong());
        dongHo.setSoluongdatmua(dongHo.getSoluongdatmua() + item.getSoLuong());
        donghoService.save(dongHo);
        break;
      case "phukien":
        PhuKien phuKien = phuKienService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        phuKien.setSoLuong(phuKien.getSoLuong() - item.getSoLuong());
        phuKien.setSoluongdatmua(phuKien.getSoluongdatmua() + item.getSoLuong());
        phuKienService.save(phuKien);
        break;
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        kinhMat.setSoLuong(kinhMat.getSoLuong() - item.getSoLuong());
        kinhMat.setSoluongdatmua(kinhMat.getSoluongdatmua() + item.getSoLuong());
        kinhMatService.save(kinhMat);
        break;
      case "butky":
        Butky butKy = butKyService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        butKy.setSoluong(butKy.getSoluong() - item.getSoLuong());
        butKy.setSoluongdatmua(butKy.getSoluongdatmua() + item.getSoLuong());
        butKyService.save(butKy);
        break;
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        trangSuc.setSoluong(trangSuc.getSoluong() - item.getSoLuong());
        trangSuc.setSoluongdatmua(trangSuc.getSoluongdatmua() + item.getSoLuong());
        trangSucService.save(trangSuc);
        break;
      default:
        throw new OrderException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
    }
    ctdhService.saveCTDH(item);
  }

  Integer checkItemQuantityAndPrice(ChiTietDonHang item) {
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        if (dongHo.getSoluong() < item.getSoLuong()) {
          throw new OrderException("Số lượng mua vượt giới hạn, mã đồng hồ: " + item.getMaSanPham());
        }
        return dongHo.getGiatien();
      case "phukien":
        PhuKien phuKien = phuKienService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        if (phuKien.getSoLuong() < item.getSoLuong()) {
          throw new OrderException("Số lượng mua vượt giới hạn, mã phụ kiện: " + item.getMaSanPham());
        }
        return phuKien.getGiaTien();
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        if (kinhMat.getSoLuong() < item.getSoLuong()) {
          throw new OrderException("Số lượng mua vượt giới hạn, mã kính mắt: " + item.getMaSanPham());
        }
        return kinhMat.getGiaTien();
      case "butky":
        Butky butKy = butKyService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        if (butKy.getSoluong() < item.getSoLuong()) {
          throw new OrderException("Số lượng mua vượt giới hạn, mã bút ký: " + item.getMaSanPham());
        }
        return butKy.getGiatien();
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        if (trangSuc.getSoluong() < item.getSoLuong()) {
          throw new OrderException("Số lượng mua vượt giới hạn, mã trang sức: " + item.getMaSanPham());
        }
        return trangSuc.getGiaTien();
      default:
        throw new OrderException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
    }
  }

  @Transactional
  void reSupply(ChiTietDonHang item) {
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        dongHo.setSoluong(dongHo.getSoluong() + item.getSoLuong());
        dongHo.setSoluongdatmua(dongHo.getSoluongdatmua() - item.getSoLuong());
        donghoService.save(dongHo);
        break;
      case "phukien":
        PhuKien phuKien = phuKienService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        phuKien.setSoLuong(phuKien.getSoLuong() + item.getSoLuong());
        phuKien.setSoluongdatmua(phuKien.getSoluongdatmua() - item.getSoLuong());
        phuKienService.save(phuKien);
        break;
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        kinhMat.setSoLuong(kinhMat.getSoLuong() + item.getSoLuong());
        kinhMat.setSoluongdatmua(kinhMat.getSoluongdatmua() - item.getSoLuong());
        kinhMatService.save(kinhMat);
        break;
      case "butky":
        Butky butKy = butKyService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        butKy.setSoluong(butKy.getSoluong() + item.getSoLuong());
        butKy.setSoluongdatmua(butKy.getSoluongdatmua() - item.getSoLuong());
        butKyService.save(butKy);
        break;
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findByIdWithLock(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        trangSuc.setSoluong(trangSuc.getSoluong() + item.getSoLuong());
        trangSuc.setSoluongdatmua(trangSuc.getSoluongdatmua() - item.getSoLuong());
        trangSucService.save(trangSuc);
        break;
      default:
        throw new OrderException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
    }
  }

  void clearTempAmount(ChiTietDonHang item) {
    switch (item.getLoaiSanPham()) {
      case "dongho":
        Dongho dongHo = donghoService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Đồng hồ không tồn tại, mã: " + item.getMaSanPham()));
        dongHo.setSoluongdatmua(dongHo.getSoluongdatmua() - item.getSoLuong());
        donghoService.save(dongHo);
        break;
      case "phukien":
        PhuKien phuKien = phuKienService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Phụ kiện không tồn tại, mã: " + item.getMaSanPham()));
        phuKien.setSoluongdatmua(phuKien.getSoluongdatmua() - item.getSoLuong());
        phuKienService.save(phuKien);
        break;
      case "kinhmat":
        KinhMat kinhMat = kinhMatService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Kính mắt không tồn tại, mã: " + item.getMaSanPham()));
        kinhMat.setSoluongdatmua(kinhMat.getSoluongdatmua() - item.getSoLuong());
        kinhMatService.save(kinhMat);
        break;
      case "butky":
        Butky butKy = butKyService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Bút ký không tồn tại, mã: " + item.getMaSanPham()));
        butKy.setSoluongdatmua(butKy.getSoluongdatmua() - item.getSoLuong());
        butKyService.save(butKy);
        break;
      case "trangsuc":
        Trangsuc trangSuc = trangSucService.findById(item.getMaSanPham())
            .orElseThrow(() -> new OrderException("Trang sức không tồn tại, mã: " + item.getMaSanPham()));
        trangSuc.setSoluongdatmua(trangSuc.getSoluongdatmua() - item.getSoLuong());
        trangSucService.save(trangSuc);
        break;
      default:
        throw new OrderException("Loại sản phẩm không tồn tại, mã: " + item.getMaSanPham());
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
  public void updateStatus(Integer madonhang, OrderStatus status, OrderPaymentStatus paymentStatus) {
    Optional<DonHang> opt = donHangRepository.findById(madonhang);
    ServiceException exception = new ServiceException();
    exception.setPath("/admin/donhang");

    if (opt.isPresent()) {
      DonHang currentData = opt.get();
      if (currentData.getTinhTrang().equals(OrderStatus.CANCELLED.getValue())) {
        exception.setMessage("Đơn hàng " + madonhang + " đã bị hủy!");
        throw exception;
      } else {
        if (status.equals(OrderStatus.CANCELLED)) {
          if (currentData.getThanhToan().equals(OrderPaymentStatus.PAID.getValue())) {
            exception.setMessage("Không được từ chối đơn hàng đã thanh toán, mã đơn: " + madonhang);
            throw exception;
          }

          for (ChiTietDonHang item : currentData.getItems()) {
            reSupply(item);
          }
        } else if (status.equals(OrderStatus.COMPLETED)) {
          for (ChiTietDonHang item : currentData.getItems()) {
            clearTempAmount(item);
          }
          thongKeService.incDoanhThu(BigDecimal.valueOf(currentData.getTongTien()));
        }
        currentData.setTinhTrang(status);
        if (paymentStatus != null) {
          currentData.setThanhToan(paymentStatus);
          if (paymentStatus.equals(OrderPaymentStatus.PAID)) {
            try {
              emailService.sendOrderSuccessEmail(donHangRepository.findById(currentData.getMaDonHang()).get());
            } catch (MessagingException e) {
              log.warn("WARNING - Cannot send order confirm to {}", currentData.getTaikhoan().getUsername());
            }
          }
        }
        updateDonHang(currentData);
      }

    } else {
      exception.setMessage("Không tìm thấy đơn hàng mã: " + madonhang);
      throw exception;
    }
  }
}
