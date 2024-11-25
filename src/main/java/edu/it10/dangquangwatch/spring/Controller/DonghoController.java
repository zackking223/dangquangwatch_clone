package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.AppCustomException.ControllerException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.Anhdongho;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.service.AnhdonghoService;
import edu.it10.dangquangwatch.spring.service.DonghoService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/dongho")
public class DonghoController {
  @Autowired
  private DonghoService donghoService;
  @Autowired
  private AnhdonghoService anhdonghoService;
  @Autowired
  private LichSuKhoService lichSuKhoService;

  @GetMapping("/")
  public String index(
      HttpSession session,
      @RequestParam("search") Optional<String> search,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("from") Optional<String> from,
      @RequestParam("to") Optional<String> to,
      Optional<Dongho> dongho, Model model) {
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

    model.addAttribute("dongho", new Dongho());
    Page<Dongho> data;
    String searchStr = "";
    int pageNum = 0;
    Dongho fieldData = new Dongho();

    if (search.isPresent())
      searchStr = search.get().trim();
    if (page.isPresent())
      pageNum = page.get() - 1;

    if (dongho.isPresent()) {
      fieldData = dongho.get();
      data = donghoService.search(searchStr, fieldData, fromStr, toStr, pageNum);
    } else {
      data = donghoService.getAll(searchStr, fromStr, toStr, pageNum);
    }

    model.addAttribute("dongho", fieldData);
    model.addAttribute("donghos", data.getContent());
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

    return "admin/dongho/index";
  }

  @GetMapping(value = "/getall")
  public @ResponseBody List<Dongho> getAllDongHo() {
    return donghoService.getAllDongho();
  }

  @GetMapping(value = "/add")
  public String add(HttpSession session, Model model) {
    var errorMessage = session.getAttribute(ErrorEnum.ADD.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.ADD.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    model.addAttribute("dongho", new Dongho());
    return "admin/dongho/addDongHo";
  }

  @GetMapping(value = "/edit")
  public String edit(HttpSession session, @RequestParam("id") Integer madongho, Model model) {
    List<String> gioitinh_options = new ArrayList<String>();
    gioitinh_options.add("Nam");
    gioitinh_options.add("Nữ");

    Optional<Dongho> donghoEdit = donghoService.findById(madongho);
    donghoEdit.ifPresent(dongho -> {
      model.addAttribute("dongho", dongho);
      model.addAttribute("images", dongho.getImages());
      model.addAttribute("gioitinh", dongho.getGioitinh());
      model.addAttribute("gioitinh_options", gioitinh_options);
    });

    var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EDIT.name());
      model.addAttribute("errorMessage", errorMessage);
    }
    return "admin/dongho/editDongHo";
  }

  @GetMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<Dongho> data = donghoService.findById(id);
    if (data.isPresent()) {
      Dongho dongho = data.get();
      model.addAttribute("dongho", dongho);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/dongho/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.IMPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.IMPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/dongho/nhap";
  }

  @PostMapping("/nhap")
  public String nhap(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinNhap") String thongTinNhap) {
    donghoService.incAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.NhapKho(
        username + " nhập " + soLuong + " đồng hồ mã " + id + ". Hết tổng cộng: " + giaTien + " vnđ. " + thongTinNhap,
        username, giaTien);

    return "redirect:/admin/dongho/";
  }

  @GetMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      Model model) {
    Optional<Dongho> data = donghoService.findById(id);
    if (data.isPresent()) {
      Dongho dongho = data.get();
      model.addAttribute("dongho", dongho);
    } else {
      throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/dongho/");
    }

    var errorMessage = session.getAttribute(ErrorEnum.EXPORT.name());

    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.EXPORT.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/dongho/xuat";
  }

  @PostMapping("/xuat")
  public String xuat(
      HttpSession session,
      @RequestParam("id") Integer id,
      @RequestParam("soLuong") Integer soLuong,
      @RequestParam("giaTien") BigDecimal giaTien,
      @RequestParam("thongTinXuat") String thongTinXuat) {

    donghoService.decAmount(soLuong, id);
    String username = (String) session.getAttribute("username");
    lichSuKhoService.XuatKho(
        username + " xuất " + soLuong + " đồng hồ mã " + id + ". Trị giá: " + giaTien + ". " + thongTinXuat,
        username);

    return "redirect:/admin/dongho/";
  }

  @PostMapping("/update")
  public String update(Dongho dongho) {
    donghoService.save(dongho);
    return "redirect:/admin/dongho/edit?id=" + dongho.getMadongho();
  }

  @PostMapping(value = "/save")
  public String save(Dongho dongho, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (dongho.getMadongho() != null) {
      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(dongho);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      donghoService.save(dongho);
    } else {
      if (files == null || files.size() == 0) {
        throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.ADD, "/admin/dongho/add");
      }
      Dongho data = donghoService.save(dongho);

      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(data);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return "redirect:/admin/dongho/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer madongho,
      Model model) {
    if (files == null || files.size() == 0) {
      throw new ControllerException("Phải có ảnh sản phẩm!", ErrorEnum.EDIT, "/admin/dongho/edit?id=" + madongho);
    }
    Optional<Dongho> dongho = donghoService.findById(madongho);

    dongho.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(dh);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/dongho/edit?id=" + madongho;
  }

  @GetMapping("/activate")
  public String activate(@RequestParam("id") Integer madongho, Model model) {
    donghoService.activate(madongho);
    return "redirect:/admin/dongho/";
  }

  @GetMapping("/deactivate")
  public String deactivate(@RequestParam("id") Integer madongho, Model model) {
    donghoService.deactivate(madongho);
    return "redirect:/admin/dongho/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhdonghoService.deleteAnhdongho(maanh);
    return "redirect:/admin/dongho/";
  }
}