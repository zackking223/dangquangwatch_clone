package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.AppCustomException.ControllerException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.Anhbutky;
import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.service.AnhbutkyService;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import edu.it10.dangquangwatch.spring.service.LichSuKhoService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/butky")
public class ButkyController {
  @Autowired
  private ButkyService butkyService;
  @Autowired
  private AnhbutkyService anhbutkyService;
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

    Page<Butky> data = butkyService.search(searchStr, fromStr, toStr, pageNum);
    List<Butky> butkys = data.getContent();

    model.addAttribute("butkys", butkys);
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

    return "admin/butky/index";
  }

  @GetMapping(value = "/add")
  public String add(HttpSession session, Model model) {
    model.addAttribute("butky", new Butky());
    var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.ADD.name());
      model.addAttribute("errorMessage", errorMessage);
    }
    return "admin/butky/addButky";
  }

  @GetMapping(value = "/edit")
  public String edit(HttpSession session, @RequestParam("id") Integer mabutky, Model model) {
    Optional<Butky> butkyEdit = butkyService.findById(mabutky);
    butkyEdit.ifPresent(butky -> {
      model.addAttribute("butky", butky);
      model.addAttribute("images", butky.getImages());
    });
    var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EDIT.name());
      model.addAttribute("errorMessage", errorMessage);
    }
    return "admin/butky/editButky";
  }

  @GetMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer mabutky,
      Model model) {
    Optional<Butky> data = butkyService.findById(mabutky);
    if (data.isPresent()) {
      Butky butky = data.get();
      model.addAttribute("butky", butky);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/butky/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.IMPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.IMPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/butky/nhap";
  }

  @PostMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinNhap") String thongTinNhap) {
    butkyService.incAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.NhapKho(
        username + " nhập " + soLuong + " bút ký mã " + id + ". Hết tổng cộng: " + giaTien + " vnđ. " + thongTinNhap,
        username, giaTien);

    return "redirect:/admin/butky/";
  }

  @GetMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer mabutky,
      Model model) {
    Optional<Butky> data = butkyService.findById(mabutky);
    if (data.isPresent()) {
      Butky butky = data.get();
      model.addAttribute("butky", butky);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/butky/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.EXPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EXPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/butky/xuat";
  }

  @PostMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinXuat") String thongTinXuat) {

    butkyService.decAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.XuatKho(
        username + " xuất " + soLuong + " bút ký mã " + id + ". Trị giá: " + giaTien + ". " + thongTinXuat,
        username);

    return "redirect:/admin/butky/";
  }

  @PostMapping("/update")
  public String update(Butky butky) {
    butkyService.save(butky);
    return "redirect:/admin/butky/";
  }

  @PostMapping(value = "/save")
  public String save(Butky butky, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (butky.getMabutky() != null) {
      for (MultipartFile file : files) {
        Anhbutky anhbutky = new Anhbutky();
        anhbutky.setFile(file);
        anhbutky.setButky(butky);

        try {
          anhbutkyService.save(anhbutky);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      butkyService.save(butky);
    } else {
      if (files == null || files.size() == 0) {
        throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.ADD, "/admin/butky/add");
      }
      Butky data = butkyService.save(butky);
      for (MultipartFile file : files) {
        Anhbutky anhbutky = new Anhbutky();
        anhbutky.setFile(file);
        anhbutky.setButky(data);

        try {
          anhbutkyService.save(anhbutky);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/butky/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer mabutky,
      Model model) {
    if (files == null || files.size() == 0) {
      throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.EDIT, "/admin/butky/edit?id=" + mabutky);
    }
    Optional<Butky> butky = butkyService.findById(mabutky);
    butky.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhbutky anhbutky = new Anhbutky();
        anhbutky.setFile(file);
        anhbutky.setButky(dh);

        try {
          anhbutkyService.save(anhbutky);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/butky/edit?id=" + mabutky;
  }

  @GetMapping(value = "/activate")
  public String activate(@RequestParam("id") Integer mabutky, Model model) {
    butkyService.activate(mabutky);
    return "redirect:/admin/butky/";
  }

  @GetMapping(value = "/deactivate")
  public String deactivate(@RequestParam("id") Integer mabutky, Model model) {
    butkyService.deactivate(mabutky);
    return "redirect:/admin/butky/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhbutkyService.delete(maanh);
    return "redirect:/admin/butky/";
  }
}