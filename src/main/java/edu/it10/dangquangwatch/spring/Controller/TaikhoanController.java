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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/accounts")
public class TaikhoanController {
  @Autowired
  private TaiKhoanService taikhoanService;

  @GetMapping("/")
  public String index(Model model,
      @RequestParam("search") Optional<String> username,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("from") Optional<String> from,
      @RequestParam("to") Optional<String> to) {
    String fromStr = "2001-01-01";
    String toStr = "3000-01-01";
    String usernameStr = "";
    int pageNum = 0;
    if (page.isPresent())
      pageNum = page.get() - 1;
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
    if (username.isPresent())
      usernameStr = username.get();

    Page<TaiKhoan> data = taikhoanService.searchTaiKhoanQuanTri(usernameStr, fromStr, toStr, pageNum);

    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("page", pageNum);
    model.addAttribute("search", usernameStr);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("taikhoans", data.getContent());

    return "/admin/quantrivien/index";
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }

  @PostMapping("/test")
  public @ResponseBody String handleTest(@RequestParam("from") String from) {
    return from;
  }

  @GetMapping(value = "/add")
  public String addTaikhoan(Model model, @RequestParam("error") Optional<String> error) {
    String errorMessage = null;
    if (error.isPresent()) {
      errorMessage = "Username đã tồn tại!";
    }
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("taikhoan", new TaiKhoan());
    return "/admin/quantrivien/addQuanTriVien";
  }

  @GetMapping(value = "/edit")
  public String editTaiKhoan(@RequestParam("id") String username, Model model) {
    TaiKhoan taikhoanEdit = taikhoanService.getTaiKhoan(username);
    if (taikhoanEdit != null) {
      model.addAttribute("taikhoan", taikhoanEdit);
      return "/admin/quantrivien/editQuanTriVien";
    } else {
      return "redirect:/admin/accounts/";
    }
  }

  @PostMapping(value = "/add")
  public String addTaiKhoan(TaiKhoan taikhoan) {
    try {
      taikhoanService.dangKyQuanTri(taikhoan);
    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/admin/accounts/add?error=dupname";
    }
    return "redirect:/admin/accounts/";
  }

  @PostMapping(value = "/save")
  public String save(TaiKhoan taikhoan) {
    taikhoanService.updateTaiKhoan(taikhoan);
    return "redirect:/admin/accounts/";
  }

  @GetMapping(value = "/delete")
  public String deleteTaiKhoan(@RequestParam("id") String username) {
    taikhoanService.deleteTaiKhoanByUsername(username);
    return "redirect:/admin/accounts/";
  }
}