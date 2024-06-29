package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/khachhang")
public class KhachhangController {
  @Autowired
  private TaiKhoanService taikhoanService;

  @GetMapping("/")
  public String index(Model model,
      @RequestParam("search") Optional<String> search,
      @RequestParam("page") Optional<Integer> page,
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
    String searchStr = "";
    int pageNum = 0;
    if (page.isPresent())
      pageNum = page.get() - 1;
    if (search.isPresent())
      searchStr = search.get().trim();

    Page<TaiKhoan> data = taikhoanService.searchTaiKhoanKhachHang(searchStr, fromStr, toStr, pageNum);

    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("taikhoans", data.getContent());

    return "admin/khachhang/index";
  }

  @GetMapping(value = "/add")
  public String addTaikhoan(Model model, @RequestParam("error") Optional<String> error) {
    String errorMessage = null;
    if (error.isPresent()) {
      errorMessage = error.get();
    }
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("taikhoan", new TaiKhoan());
    return "admin/khachhang/addKhachHang";
  }

  @GetMapping(value = "/edit")
  public String editTaiKhoan(@RequestParam("id") String username, Model model) {
    TaiKhoan taikhoanEdit = taikhoanService.getTaiKhoan(username);
    if (taikhoanEdit != null) {
      model.addAttribute("taikhoan", taikhoanEdit);
      return "admin/khachhang/editKhachHang";
    } else {
      return "redirect:/admin/khachhang/";
    }
  }

  @PostMapping(value = "/add")
  public String addTaiKhoan(TaiKhoan taikhoan, Model model) {
    try {
      taikhoanService.dangKyKhachHang(taikhoan);
    } catch (Exception e) {
      e.printStackTrace();

      String errorMessage = "Username đã tồn tại!";
      if (e.getMessage().contains("for key \'sodienthoai_unique\'")) {
        errorMessage = "Số điện thoại đã tồn tại!";
      }
      model.addAttribute("errorMessage", errorMessage);
      model.addAttribute("taikhoan", taikhoan);
      return "admin/khachhang/addKhachHang";
    }
    return "redirect:/admin/khachhang/";
  }

  @PostMapping(value = "/save")
  public String save(TaiKhoan taikhoan) {
    taikhoanService.updateTaiKhoan(taikhoan);
    return "redirect:/admin/khachhang/";
  }

  @GetMapping(value = "/delete")
  public String deleteTaiKhoan(@RequestParam("id") String username, Model model) {
    taikhoanService.deleteTaiKhoanByUsername(username);
    return "redirect:/admin/khachhang/";
  }
}