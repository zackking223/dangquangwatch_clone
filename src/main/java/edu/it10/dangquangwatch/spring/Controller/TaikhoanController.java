package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.AppCustomException.SaveAccountException;
import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;

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

  @GetMapping(value = "/add")
  public String addTaikhoan(Model model, HttpSession session) {
    var errorMessage = session.getAttribute(ErrorEnum.ADMIN_ACCOUNTS_ERROR.name());
    if (errorMessage != null) {
      model.addAttribute("errorMessage", (String)errorMessage);
      session.removeAttribute(ErrorEnum.ADMIN_ACCOUNTS_ERROR.name());
    }
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
  public String addTaiKhoan(TaiKhoan taikhoan, HttpSession session) {
    try {
      taikhoanService.dangKyQuanTri(taikhoan);
    } catch (SaveAccountException e) {
      e.printStackTrace();
      session.setAttribute(ErrorEnum.ADMIN_ACCOUNTS_ERROR.name(), e.getMessage());
      return "redirect:/admin/accounts/add";
    }
    return "redirect:/admin/accounts/";
  }

  @PostMapping(value = "/save")
  public String save(TaiKhoan taikhoan) {
    taikhoanService.updateTaiKhoan(taikhoan, "/admin/accounts/edit?id=" + taikhoan.getUsername());
    return "redirect:/admin/accounts/";
  }

  @GetMapping(value = "/delete")
  public String delete(@RequestParam("id") String username) {
    taikhoanService.deleteById(username);
    return "redirect:/admin/accounts/";
  }

  @GetMapping(value = "/activate")
  public String activate(@RequestParam("id") String username) {
    taikhoanService.activate(username);
    return "redirect:/admin/accounts/";
  }

  @GetMapping(value = "/deactivate")
  public String deactivate(@RequestParam("id") String username) {
    taikhoanService.deactivate(username);
    return "redirect:/admin/accounts/";
  }
}