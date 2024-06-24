package edu.it10.dangquangwatch.spring.controller;  

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;  

import java.util.List;  

@Controller
@RequestMapping(path = "/admin/accounts")  
public class TaikhoanController {  
  @Autowired private TaiKhoanService taikhoanService;  

  @GetMapping("/")  
  public String index(Model model) {  
    List<TaiKhoan> taikhoans = taikhoanService.getAllTaiKhoanQuanTri();  

    model.addAttribute("taikhoans", taikhoans);  

    return "/admin/quantrivien/index";  
  }  

  @GetMapping(value = "/add")  
  public String addTaikhoan(Model model) {  
    model.addAttribute("taikhoan", new TaiKhoan());  
    return "/admin/quantrivien/addQuanTriVien";  
  }  

  @GetMapping(value = "/edit")  
  public String editTaiKhoan(@RequestParam("id") String username, Model model) {  
    TaiKhoan taikhoanEdit = taikhoanService.getTaiKhoan(username); 
    if(taikhoanEdit != null){
        model.addAttribute("taikhoan", taikhoanEdit);  
        return "/admin/quantrivien/editQuanTriVien";  
    }  
    else{
        return "redirect:/admin/accounts/";
    }
  }  

  @PostMapping(value = "/add")  
  public String addTaiKhoan(TaiKhoan taikhoan) {  
    taikhoanService.dangKyQuanTri(taikhoan);  
    return "redirect:/admin/accounts/";  
  }  

  @PostMapping(value = "/save")  
  public String save(TaiKhoan taikhoan) {  
    taikhoanService.updateTaiKhoan(taikhoan);  
    return "redirect:/admin/accounts/";  
  }  

  @GetMapping(value = "/delete")  
  public String deleteTaiKhoan(@RequestParam("id") String username, Model model) {  
    taikhoanService.deleteTaiKhoanByUsername(username);  
    return "redirect:/admin/accounts/";  
  }  
}