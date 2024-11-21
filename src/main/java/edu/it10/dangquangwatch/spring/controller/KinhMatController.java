package edu.it10.dangquangwatch.spring.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.service.AnhkinhmatService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import edu.it10.dangquangwatch.spring.service.LichSuKhoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/admin/kinhmat")
public class KinhMatController {
  @Autowired
  private KinhMatService kinhMatService;
  @Autowired
  private AnhkinhmatService anhkinhmatService;
  @Autowired
  private LichSuKhoService lichSuKhoService;

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

    Page<KinhMat> data = kinhMatService.search(searchStr, fromStr, toStr, pageNum);
    List<KinhMat> kinhmats = data.getContent();

    model.addAttribute("kinhMats", kinhmats);
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
    return "admin/kinhmat/index";
  }

  @GetMapping("/add")
  public String add(HttpSession session, Model model) {
    model.addAttribute("kinhMat", new KinhMat());

    var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.ADD.name());
      model.addAttribute("errorMessage", errorMessage);
    }
    return "admin/kinhmat/addKinhMat";
  }

  @GetMapping("/edit")
  public String edit(HttpSession session, @RequestParam("id") Integer maKinhMat, Model model) {
    Optional<KinhMat> kinhMatEdit = kinhMatService.findById(maKinhMat);
    kinhMatEdit.ifPresent(kinhMat -> {
      model.addAttribute("kinhMat", kinhMat);
      model.addAttribute("images", kinhMat.getImages());
    });

    var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EDIT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/kinhmat/editKinhMat";
  }

  @GetMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<KinhMat> data = kinhMatService.findById(id);
    if (data.isPresent()) {
      KinhMat kinhMat = data.get();
      model.addAttribute("kinhMat", kinhMat);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/kinhmat/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.IMPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.IMPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/kinhmat/nhap";
  }

  @PostMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinNhap") String thongTinNhap) {
    kinhMatService.incAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.NhapKho(
        username + " nhập " + soLuong + " kính mắt mã " + id + ". Hết tổng cộng: " + giaTien + " vnđ. " + thongTinNhap,
        username, giaTien);

    return "redirect:/admin/kinhmat/";
  }

  @GetMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<KinhMat> data = kinhMatService.findById(id);
    if (data.isPresent()) {
      KinhMat kinhMat = data.get();
      model.addAttribute("kinhMat", kinhMat);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/kinhmat/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.EXPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EXPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/kinhmat/xuat";
  }

  @PostMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinXuat") String thongTinXuat) {

    kinhMatService.decAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.XuatKho(
        username + " xuất " + soLuong + " kính mắt mã " + id + ". Trị giá: " + giaTien + ". " + thongTinXuat,
        username);

    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/update")
  public String update(KinhMat kinhMat) {
    kinhMatService.save(kinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/save")
  public String save(KinhMat kinhMat, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (kinhMat.getMaKinhMat() != null) {
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(kinhMat);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      kinhMatService.save(kinhMat);
    } else {
      KinhMat data = kinhMatService.save(kinhMat);
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(data);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer makinhmat,
      Model model) {
    Optional<KinhMat> kinhmat = kinhMatService.findById(makinhmat);

    kinhmat.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(dh);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/kinhmat/edit?id=" + makinhmat;
  }

  @GetMapping("/activate")
  public String activate(@RequestParam("id") Integer maKinhMat, Model model) {
    kinhMatService.activate(maKinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @GetMapping("/deactivate")
  public String deactivate(@RequestParam("id") Integer maKinhMat, Model model) {
    kinhMatService.deactivate(maKinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhkinhmatService.deleteAnhkinhmat(maanh);
    return "redirect:/admin/kinhmat/";
  }
}
