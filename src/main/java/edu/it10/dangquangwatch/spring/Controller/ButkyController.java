package edu.it10.dangquangwatch.spring.controller;  

import edu.it10.dangquangwatch.spring.entity.Butky;  
import edu.it10.dangquangwatch.spring.service.ButkyService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;  

import java.util.List;  
import java.util.Optional;  

@Controller
@RequestMapping(path = "/admin/butky")  
public class ButkyController {  
  @Autowired private ButkyService butkyService;  

  @GetMapping("/")  
  public String index(Model model) {  
    List<Butky> butkys = butkyService.getAllButky();  

    model.addAttribute("butkys", butkys);  

    return "/admin/butky/index";  
  }  

  @GetMapping(value = "/add")  
  public String addButky(Model model) {  
    model.addAttribute("butky", new Butky());  
    return "/admin/butky/addButky";  
  }  

  @GetMapping(value = "/edit")  
  public String editButky(@RequestParam("id") Integer mabutky, Model model) {  
    Optional<Butky> butkyEdit = butkyService.findButkyById(mabutky);  
    butkyEdit.ifPresent(butky -> model.addAttribute("butky", butky));  
    return "/admin/butky/editButky";  
  }  

  @PostMapping(value = "/save")  
  public String save(Butky butky) {  
    butkyService.saveButky(butky);  
    return "redirect:/admin/butky/";  
  }  

  @GetMapping(value = "/delete")  
  public String deleteButky(@RequestParam("id") Integer mabutky, Model model) {  
    butkyService.deleteButky(mabutky);  
    return "redirect:/admin/butky/";  
  }  
}