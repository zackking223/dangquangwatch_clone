package edu.it10.dangquangwatch.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.service.KinhMatService;

@Controller
@RequestMapping(path = "/admin/kinhmat")
public class KinhMatController {
  @Autowired private KinhMatService kinhMatService;

  @GetMapping("/")  
  public String index(Model model) {  
    List<KinhMat> kinhMats = kinhMatService.getAllKinhMat();  

    model.addAttribute("kinhMats", kinhMats);  

    return "admin/kinhmat/index";  
  }  

  @GetMapping("/add")  
  public String addKinhMat(Model model) {  
    model.addAttribute("kinhMat", new KinhMat());  
    return "admin/kinhmat/addKinhMat";  
  }  

  @GetMapping("/edit")  
  public String editKinhMat(@RequestParam("maKinhMat") Integer maKinhMat, Model model) {  
    Optional<KinhMat> kinhMatEdit = kinhMatService.findKinhMatById(maKinhMat);  
    kinhMatEdit.ifPresent(kinhMat -> model.addAttribute("kinhMat", kinhMat));  
    return "admin/kinhmat/editKinhMat";  
  }  

  @PostMapping("/save")  
  public String save(KinhMat kinhMat) {  
    kinhMatService.saveKinhMat(kinhMat);  
    return "redirect:/admin/kinhmat/";  
  }  

  @GetMapping("/delete")  
  public String deleteKinhMat(@RequestParam("maKinhMat") Integer maKinhMat, Model model) {  
    kinhMatService.deleteKinhMat(maKinhMat);  
    return "redirect:/admin/kinhmat/";  
  }
}
