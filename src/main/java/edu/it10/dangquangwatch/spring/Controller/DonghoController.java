package edu.it10.dangquangwatch.spring.controller;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  
import edu.it10.dangquangwatch.spring.service.DonghoService;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;  
import java.util.Optional;  

@Controller  
@RequestMapping(path = "/admin/dongho")
public class DonghoController {  
  @Autowired private DonghoService donghoService;  

  @GetMapping("/")  
  public String index(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page, Optional<Dongho> dongho, Model model) {
    model.addAttribute("dongho", new Dongho());
    Page<Dongho> data;
    String searchStr = "";
    int pageNum = 0;
    Dongho fieldData = new Dongho();

    if (search.isPresent()) searchStr = search.get();
    if (page.isPresent()) pageNum = page.get() - 1;
    
    if (dongho.isPresent()) {
      fieldData = dongho.get();
      data = donghoService.searchDongho(searchStr, fieldData, pageNum);  
    } else {
      data = donghoService.getAllDonghoByTendongho(searchStr, pageNum);
    }
    
    model.addAttribute("dongho", fieldData);  
    model.addAttribute("donghos", data.getContent());
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("sotrang", data.getTotalPages());  

    return "admin/dongho/index";  
  }
  
  @GetMapping(value = "/getall")
  public @ResponseBody List<Dongho> getAllDongHo() {
    return donghoService.getAllDongho();
  }

  @GetMapping(value = "/add")  
  public String addDongho(Model model) {  
    model.addAttribute("dongho", new Dongho());  
    return "admin/dongho/addDongHo";  
  }  

  @GetMapping(value = "/edit")  
  public String editDongho(@RequestParam("id") Integer madongho, Model model) {  
    Optional<Dongho> donghoEdit = donghoService.findDonghoById(madongho);  
    donghoEdit.ifPresent(dongho -> model.addAttribute("dongho", dongho));  
    return "admin/dongho/editDongHo";  
  }  

  @PostMapping(value = "save")  
  public String save(Dongho dongho) {
    donghoService.saveDongho(dongho);  
    return "redirect:/admin/dongho/";  
  }  

  @GetMapping(value = "/delete")  
  public String deleteDongho(@RequestParam("id") Integer madongho, Model model) {  
    donghoService.deleteDongho(madongho);  
    return "redirect:/admin/dongho/";  
  }  
}