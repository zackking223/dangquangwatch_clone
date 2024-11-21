package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.AppCustomException.ControllerException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.service.AnhtrangsucService;
import edu.it10.dangquangwatch.spring.service.LichSuKhoService;
import edu.it10.dangquangwatch.spring.service.TrangsucService;
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
@RequestMapping(path = "/admin/trangsuc")
public class TrangsucController {
  @Autowired
  private TrangsucService trangsucService;
  @Autowired
  private AnhtrangsucService anhtrangsucService;
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

    Page<Trangsuc> data = trangsucService.search(searchStr, fromStr, toStr, pageNum);
    List<Trangsuc> trangsucs = data.getContent();

    model.addAttribute("trangsucs", trangsucs);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");

    var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.INDEX.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "/admin/trangsuc/index";
  }

  @GetMapping(value = "/add")
  public String add(HttpSession session, Model model) {
    model.addAttribute("trangsuc", new Trangsuc());

    var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.ADD.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "/admin/trangsuc/addTrangsuc";
  }

  @GetMapping(value = "/edit")
  public String edit(HttpSession session, @RequestParam("id") Integer matrangsuc, Model model) {
    Optional<Trangsuc> trangsucEdit = trangsucService.findById(matrangsuc);
    trangsucEdit.ifPresent(trangsuc -> {
      model.addAttribute("trangsuc", trangsuc);
      model.addAttribute("images", trangsuc.getImages());
    });

    var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EDIT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "/admin/trangsuc/editTrangsuc";
  }

  @GetMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<Trangsuc> data = trangsucService.findById(id);
    if (data.isPresent()) {
      Trangsuc trangsuc = data.get();
      model.addAttribute("trangsuc", trangsuc);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/trangsuc/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.IMPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.IMPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/trangsuc/nhap";
  }

  @PostMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinNhap") String thongTinNhap) {
    trangsucService.incAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.NhapKho(
        username + " nhập " + soLuong + " trang sức mã " + id + ". Hết tổng cộng: " + giaTien + " vnđ. " + thongTinNhap,
        username, giaTien);

    return "redirect:/admin/trangsuc/";
  }

  @GetMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<Trangsuc> data = trangsucService.findById(id);
    if (data.isPresent()) {
      Trangsuc trangsuc = data.get();
      model.addAttribute("trangsuc", trangsuc);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/trangsuc/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.EXPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EXPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/trangsuc/xuat";
  }

  @PostMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinXuat") String thongTinXuat) {

    trangsucService.decAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.XuatKho(
        username + " xuất " + soLuong + " trang sức mã " + id + ". Trị giá: " + giaTien + ". " + thongTinXuat,
        username);

    return "redirect:/admin/trangsuc/";
  }

  @PostMapping(value = "/update")
  public String update(Trangsuc trangsuc) {
    trangsucService.save(trangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping(value = "/save")
  public String save(Trangsuc trangsuc, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (trangsuc.getMatrangsuc() != null) {
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(trangsuc);
  
        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      trangsucService.save(trangsuc);
    } else {
      Trangsuc data = trangsucService.save(trangsuc);
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(data);
  
        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer matrangsuc,
      Model model) {
    Optional<Trangsuc> trangsuc = trangsucService.findById(matrangsuc);

    trangsuc.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(dh);

        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/trangsuc/edit?id=" + matrangsuc;
  }

  @GetMapping(value = "/activate")
  public String activate(@RequestParam("id") Integer matrangsuc, Model model) {
    trangsucService.activate(matrangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @GetMapping(value = "/deactivate")
  public String deactivate(@RequestParam("id") Integer matrangsuc, Model model) {
    trangsucService.deactivate(matrangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhtrangsucService.deleteAnhtrangsuc(maanh);
    return "redirect:/admin/trangsuc/";
  }
}