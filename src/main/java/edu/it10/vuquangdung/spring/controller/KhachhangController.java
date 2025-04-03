package edu.it10.vuquangdung.spring.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.vuquangdung.spring.AppCustomException.ErrorEnum;
import edu.it10.vuquangdung.spring.entity.TaiKhoan;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanManager;
import edu.it10.vuquangdung.spring.service.taikhoan.TaiKhoanSearchManager;

import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/khachhang")
public class KhachhangController {
    private final TaiKhoanSearchManager searchManager;
    private final TaiKhoanManager taikhoanManager;

    public KhachhangController(TaiKhoanSearchManager searchManager, TaiKhoanManager taikhoanManager) {
        this.searchManager = searchManager;
        this.taikhoanManager = taikhoanManager;
    }

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

        Page<TaiKhoan> data = searchManager.searchTaiKhoanKhachHang(searchStr, fromStr, toStr, pageNum);

        model.addAttribute("sotrang", data.getTotalPages());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("from", from.isPresent() ? from.get() : "");
        model.addAttribute("to", to.isPresent() ? to.get() : "");
        model.addAttribute("taikhoans", data.getContent());

        return "admin/khachhang/index";
    }

    @GetMapping(value = "/add")
    public String add(HttpSession session, Model model, @RequestParam("error") Optional<String> error) {
        String errorMessage = null;
        String province = "";
        String district = "";
        String ward = "";
        String extra = "";
        TaiKhoan taikhoan = null;

        if (error.isPresent()) {
            errorMessage = error.get();
        } else if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            } else {
                var tempErrorMessage = session.getAttribute(ErrorEnum.REGISTER_ERROR.name());
                var tempTaiKhoan = session.getAttribute("taikhoan");
                if (tempErrorMessage != null) {
                    errorMessage = (String) tempErrorMessage;
                    session.removeAttribute(ErrorEnum.REGISTER_ERROR.name());

                    if (tempTaiKhoan != null) {
                        taikhoan = (TaiKhoan) tempTaiKhoan;
                        session.removeAttribute("taikhoan");
                        String address = taikhoan.getDiachi();

                        if (taikhoan.getDiachi() != null && !taikhoan.getDiachi().equals("Chưa có")) {
                            String[] addressSplit = address.split(", ", 4);
                            if (addressSplit.length == 4) {
                                province = addressSplit[0];
                                district = addressSplit[1];
                                ward = addressSplit[2];
                                extra = addressSplit[3];
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("province", province);
        model.addAttribute("district", district);
        model.addAttribute("ward", ward);
        model.addAttribute("extra", extra);
        model.addAttribute("errorMessage", errorMessage);

        if (taikhoan != null) {
            model.addAttribute("taikhoan", taikhoan);
        } else {
            model.addAttribute("taikhoan", new TaiKhoan());
        }
        return "admin/khachhang/addKhachHang";
    }

    @GetMapping(value = "/edit")
    public String edit(HttpSession session, @RequestParam("id") String username, Model model) {
        TaiKhoan taikhoanEdit = taikhoanManager.getTaiKhoan(username);

        if (taikhoanEdit == null) {
            return "redirect:/admin/khachhang/";
        }

        String province = "";
        String district = "";
        String ward = "";
        String extra = "";
        String errorMessage = null;

        String[] addressSplit = taikhoanEdit.getDiachi().split(", ", 4);

        if (session != null) {
            var tempErrorMessage = session.getAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name());
            if (tempErrorMessage != null) {
                errorMessage = (String) tempErrorMessage;
                session.removeAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name());
            }
        }

        if (addressSplit.length == 4) {
            province = addressSplit[0];
            district = addressSplit[1];
            ward = addressSplit[2];
            extra = addressSplit[3];
        }
        model.addAttribute("province", province);
        model.addAttribute("district", district);
        model.addAttribute("ward", ward);
        model.addAttribute("extra", extra);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("taikhoan", taikhoanEdit);
        return "admin/khachhang/editKhachHang";
    }

    @PostMapping(value = "/add")
    public String add(HttpSession session, TaiKhoan taikhoan, Model model) {
        String diachi = taikhoan.getDiachi();

        if (diachi == null || diachi.isEmpty() || diachi.split(", ", 4).length < 4) {
            session.setAttribute(ErrorEnum.REGISTER_ERROR.name(), "Địa chỉ không hợp lệ!");
            session.setAttribute("taikhoan", taikhoan);
            return "redirect:/admin/khachhang/add";
        }

        taikhoanManager.dangKyKhachHang(taikhoan, "/admin/khachhang/add");
        return "redirect:/admin/khachhang/";
    }

    @PostMapping(value = "/save")
    public String save(HttpSession session, TaiKhoan taikhoan) {
        String diachi = taikhoan.getDiachi();
        String sodienthoai = taikhoan.getSodienthoai();
        if (sodienthoai == null) {
            session.setAttribute(ErrorEnum.REGISTER_ERROR.name(), "Số điện thoại không hợp lệ!");
            return "redirect:/admin/khachhang/edit?id=" + taikhoan.getUsername();
        }

        if (diachi == null || diachi.isEmpty() || diachi.split(", ", 4).length < 4) {
            session.setAttribute(ErrorEnum.REGISTER_ERROR.name(), "Địa chỉ không hợp lệ!");
            return "redirect:/admin/khachhang/edit?id=" + taikhoan.getUsername();
        }
        taikhoanManager.updateTaiKhoan(taikhoan, "/admin/khachhang/edit?id=" + taikhoan.getUsername());
        return "redirect:/admin/khachhang/";
    }

    @GetMapping(value = "/activate")
    public String activate(@RequestParam("id") String username, Model model) {
        taikhoanManager.activate(username);
        return "redirect:/admin/khachhang/";
    }

    @GetMapping(value = "/deactivate")
    public String deactivate(@RequestParam("id") String username, Model model) {
        taikhoanManager.deactivate(username);
        return "redirect:/admin/khachhang/";
    }
}