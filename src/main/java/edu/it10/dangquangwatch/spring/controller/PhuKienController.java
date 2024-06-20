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

import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.service.PhuKienService;

@Controller
@RequestMapping(path = "/admin/phukien")
public class PhuKienController {
  @Autowired
  private PhuKienService phuKienService;

  @GetMapping("/")
  public String index(Model model) {
    List<PhuKien> phuKiens = phuKienService.getAllPhuKien();

    model.addAttribute("phuKiens", phuKiens);

    return "admin/phukien/index";
  }

  @GetMapping("/add")
  public String addPhuKien(Model model) {
    model.addAttribute("phuKien", new PhuKien());
    return "admin/phukien/addPhuKien";
  }

  @GetMapping("/edit")
  public String editPhuKien(@RequestParam("maPhuKien") Integer maPhuKien, Model model) {
    Optional<PhuKien> phuKienEdit = phuKienService.findPhuKienById(maPhuKien);
    phuKienEdit.ifPresent(phuKien -> model.addAttribute("phuKien", phuKien));
    return "admin/phukien/editPhuKien";
  }

  @PostMapping("/save")
  public String save(PhuKien phuKien) {
    phuKienService.savePhuKien(phuKien);
    return "redirect:/admin/phukien/";
  }

  @GetMapping("/delete")
  public String deletePhuKien(@RequestParam("maPhuKien") Integer maPhuKien, Model model) {
    phuKienService.deletePhuKien(maPhuKien);
    return "redirect:/admin/phukien/";
  }
}
