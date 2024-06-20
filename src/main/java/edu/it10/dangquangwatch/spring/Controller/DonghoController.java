package edu.it10.dangquangwatch.spring.controller;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  
import edu.it10.dangquangwatch.spring.service.DonghoService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  

import java.util.List;  
import java.util.Optional;  

@Controller  
@RequestMapping(path = "/admin/dongho")
public class DonghoController {  
  @Autowired private DonghoService donghoService;  

  @GetMapping("/")  
  public String index(Model model) {  
    List<Dongho> donghos = donghoService.getAllDongho();  

    model.addAttribute("donghos", donghos);  

    return "admin/dongho/index";  
  }  

  @GetMapping(value = "/add")  
  public String addDongho(Model model) {  
    model.addAttribute("dongho", new Dongho());  
    return "admin/dongho/addDongho";  
  }  

  @GetMapping(value = "/edit")  
  public String editDongho(@RequestParam("madongho") Long madongho, Model model) {  
    Optional<Dongho> donghoEdit = donghoService.findDonghoById(madongho);  
    donghoEdit.ifPresent(dongho -> model.addAttribute("dongho", dongho));  
    return "admin/dongho/editDongho";  
  }  

  @PostMapping(value = "save")  
  public String save(Dongho dongho) {  
    donghoService.saveDongho(dongho);  
    return "redirect:/";  
  }  

  @GetMapping(value = "/delete")  
  public String deleteDongho(@RequestParam("id") Long madongho, Model model) {  
    donghoService.deleteDongho(madongho);  
    return "redirect:/";  
  }  
}