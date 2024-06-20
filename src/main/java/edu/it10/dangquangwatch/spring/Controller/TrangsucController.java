package edu.it10.dangquangwatch.spring.controller;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  
import edu.it10.dangquangwatch.spring.service.TrangsucService;  
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
@RequestMapping(path = "/admin/trangsuc")  
public class TrangsucController {  
  @Autowired private TrangsucService trangsucService;  

  @GetMapping("/")  
  public String index(Model model) {  
    List<Trangsuc> trangsucs = trangsucService.getAllTrangsuc();  

    model.addAttribute("trangsucs", trangsucs);  

    return "/admin/trangsuc/index";  
  }  

  @GetMapping(value = "/add")  
  public String addTrangsuc(Model model) {  
    model.addAttribute("trangsuc", new Trangsuc());  
    return "/admin/trangsuc/addTrangsuc";  
  }  

  @GetMapping(value = "/edit")  
  public String editTrangsuc(@RequestParam("id") Integer matrangsuc, Model model) {  
    Optional<Trangsuc> trangsucEdit = trangsucService.findTrangsucById(matrangsuc);  
    trangsucEdit.ifPresent(trangsuc -> model.addAttribute("trangsuc", trangsuc));  
    return "/admin/trangsuc/editTrangsuc";  
  }  

  @PostMapping(value = "/save")  
  public String save(Trangsuc trangsuc) {  
    trangsucService.saveTrangsuc(trangsuc);  
    return "redirect:/admin/trangsuc/";  
  }  

  @GetMapping(value = "/delete")  
  public String deleteTrangsuc(@RequestParam("id") Integer matrangsuc, Model model) {  
    trangsucService.deleteTrangsuc(matrangsuc);  
    return "redirect:/admin/trangsuc/";  
  }  
}