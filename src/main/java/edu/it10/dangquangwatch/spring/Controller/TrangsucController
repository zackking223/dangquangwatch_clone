package io.github.tubean.myspringcrud.controller;  

import io.github.tubean.myspringcrud.entity.Trangsuc;  
import io.github.tubean.myspringcrud.service.TrangsucService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  

import java.util.List;  
import java.util.Optional;  

@Controller
@RequestMapping("/admin/trangsuc")  
public class TrangsucController {  
  @Autowired private TrangsucService trangsucService;  

  @RequestMapping("/")  
  public String index(Model model) {  
    List<Trangsuc> trangsucs = trangsucService.getAllTrangsuc();  

    model.addAttribute("trangsucs", trangsucs);  

    return "/admin/trangsuc/index";  
  }  

  @RequestMapping(value = "add")  
  public String addTrangsuc(Model model) {  
    model.addAttribute("trangsuc", new Trangsuc());  
    return "/admin/trangsuc/addTrangsuc";  
  }  

  @RequestMapping(value = "/edit", method = RequestMethod.GET)  
  public String editTrangsuc(@RequestParam("id") Long matrangsuc, Model model) {  
    Optional<Trangsuc> trangsucEdit = trangsucService.findTrangsucById(matrangsuc);  
    trangsucEdit.ifPresent(trangsuc -> model.addAttribute("trangsuc", trangsuc));  
    return "/admin/trangsuc/editTrangsuc";  
  }  

  @RequestMapping(value = "save", method = RequestMethod.POST)  
  public String save(Trangsuc trangsuc) {  
    trangsucService.saveTrangsuc(trangsuc);  
    return "redirect:/";  
  }  

  @RequestMapping(value = "/delete", method = RequestMethod.GET)  
  public String deleteTrangsuc(@RequestParam("id") Long matrangsuc, Model model) {  
    trangsucService.deleteTrangsuc(matrangsuc);  
    return "redirect:/";  
  }  
}