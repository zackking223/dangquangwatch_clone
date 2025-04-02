package edu.it10.dangquangwatch.spring.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.it10.dangquangwatch.spring.AppCustomException.ControllerException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.Anhphukien;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.service.AnhphukienService;
import edu.it10.dangquangwatch.spring.service.LichSuHeThongService;
import edu.it10.dangquangwatch.spring.service.LichSuKhoService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/admin/phukien")
public class PhuKienController {
  @Autowired
  private PhuKienService phuKienService;
  @Autowired
  private AnhphukienService anhphukienService;
  @Autowired
  LichSuKhoService lichSuKhoService;
  private static final Logger log = LoggerFactory.getLogger(PhuKienController.class);

  @Autowired
  LichSuHeThongService lichSuHeThongService;

  @GetMapping("/")
  public String index(
      HttpSession session,
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("search") Optional<String> search,
      @RequestParam("from") Optional<String> from,
      @RequestParam("to") Optional<String> to) {
    String fromStr = "2001-01-01";
    String toStr = "3000-01-01";
    if (from.isPresent()) {
      if (!from.get().isEmpty()) {
        fromStr = from.get();
      }
    }
    if (to.isPresent()) {
      if (!to.get().isEmpty()) {
        toStr = to.get();
      }
    }
    int pageNum = 0;
    String searchStr = "";

    if (search.isPresent()) {
      searchStr = search.get().trim();
    }

    if (page.isPresent()) {
      pageNum = page.get() - 1;
    }

    Page<PhuKien> data = phuKienService.search(searchStr, fromStr, toStr, pageNum);
    List<PhuKien> phukiens = data.getContent();

    model.addAttribute("phuKiens", phukiens);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("sotrang", data.getTotalPages());

    var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.INDEX.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/phukien/index";
  }

  @GetMapping("/add")
  public String add(HttpSession session, Model model) {
    model.addAttribute("phuKien", new PhuKien());

    var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.ADD.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/phukien/addPhuKien";
  }

  @GetMapping("/edit")
  public String edit(HttpSession session, @RequestParam("id") Integer maPhuKien, Model model) {
    Optional<PhuKien> phuKienEdit = phuKienService.findById(maPhuKien);
    phuKienEdit.ifPresent(phuKien -> {
      model.addAttribute("phuKien", phuKien);
      model.addAttribute("images", phuKien.getImages());
      log.info("DANH SACH: {}", phuKien.getImages());
    });

    var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EDIT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/phukien/editPhuKien";
  }

  @GetMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<PhuKien> data = phuKienService.findById(id);
    if (data.isPresent()) {
      PhuKien phuKien = data.get();
      model.addAttribute("phuKien", phuKien);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/phukien/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.IMPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.IMPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/phukien/nhap";
  }

  @PostMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinNhap") String thongTinNhap) {
    phuKienService.incAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.NhapKho(
        username + " nhập " + soLuong + " phụ kiện mã " + id + ". Hết tổng cộng: " + giaTien + " vnđ. " + thongTinNhap,
        username, giaTien);

    return "redirect:/admin/phukien/";
  }

  @GetMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<PhuKien> data = phuKienService.findById(id);
    if (data.isPresent()) {
      PhuKien phuKien = data.get();
      model.addAttribute("phuKien", phuKien);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/phukien/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.EXPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EXPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/phukien/xuat";
  }

  @PostMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinXuat") String thongTinXuat) {

    phuKienService.decAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.XuatKho(
        username + " xuất " + soLuong + " phụ kiện mã " + id + ". Trị giá: " + giaTien + ". " + thongTinXuat,
        username);

    return "redirect:/admin/phukien/";
  }

  @PostMapping("/update")
  public String update(PhuKien phuKien) {
    phuKienService.save(phuKien);
    return "redirect:/admin/phukien/";
  }

  @PostMapping("/save")
  public String save(PhuKien phuKien, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (phuKien.getMaPhuKien() != null) {
      for (MultipartFile file : files) {
        Anhphukien anhphukien = new Anhphukien();
        anhphukien.setFile(file);
        anhphukien.setPhukien(phuKien);

        try {
          anhphukienService.save(anhphukien);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      phuKienService.save(phuKien);
    } else {
      if (files == null || files.size() == 0) {
        throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.ADD, "/admin/phukien/add");
      }
      PhuKien data = phuKienService.save(phuKien);
      for (MultipartFile file : files) {
        Anhphukien anhphukien = new Anhphukien();
        anhphukien.setFile(file);
        anhphukien.setPhukien(data);

        try {
          anhphukienService.save(anhphukien);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/phukien/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer maphukien,
      Model model) {
    if (files == null || files.size() == 0) {
      throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.EDIT, "/admin/phukien/edit?id=" + maphukien);
    }
    Optional<PhuKien> phukien = phuKienService.findById(maphukien);

    phukien.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhphukien anhphukien = new Anhphukien();
        anhphukien.setFile(file);
        anhphukien.setPhukien(dh);

        try {
          anhphukienService.save(anhphukien);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/phukien/edit?id=" + maphukien;
  }

  @GetMapping("/activate")
  public String activate(@RequestParam("id") Integer maPhuKien, Model model) {
    phuKienService.activate(maPhuKien);
    return "redirect:/admin/phukien/";
  }

  @GetMapping("/deactivate")
  public String deactivate(@RequestParam("id") Integer maPhuKien, Model model) {
    phuKienService.deactivate(maPhuKien);
    return "redirect:/admin/phukien/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhphukienService.delete(maanh);
    return "redirect:/admin/phukien/";
  }

  @PostMapping("/delete")
  public String delete(HttpSession session, @RequestParam Integer id, @RequestParam String thongTin) {
    String username = (String) session.getAttribute("username");
    lichSuHeThongService.AddDeleteProductHistory(thongTin, username);
    phuKienService.delete(id);
    return "redirect:/admin/phukien/";
  }
}
