package edu.it10.dangquangwatch.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.it10.dangquangwatch.spring.entity.Anhphukien;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.service.AnhphukienService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;

@Controller
@RequestMapping(path = "/admin/phukien")
public class PhuKienController {
  @Autowired
  private PhuKienService phuKienService;
  @Autowired
  private AnhphukienService anhphukienService;

  @GetMapping("/")
  public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("search") Optional<String> search) {
    int pageNum = 0;
    String searchStr = "";

    if (search.isPresent()) {
      searchStr = search.get();
    }

    if (page.isPresent()) {
      pageNum = page.get() - 1;
    }

    Page<PhuKien> data = phuKienService.searchPhuKien(searchStr, pageNum);
    List<PhuKien> phukiens = data.getContent();

    model.addAttribute("phuKiens", phukiens);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("sotrang", data.getTotalPages()); 
    return "admin/phukien/index";
  }

  @GetMapping("/add")
  public String addPhuKien(Model model) {
    model.addAttribute("phuKien", new PhuKien());
    return "admin/phukien/addPhuKien";
  }

  @GetMapping("/edit")
  public String editPhuKien(@RequestParam("id") Integer maPhuKien, Model model) {
    Optional<PhuKien> phuKienEdit = phuKienService.findPhuKienById(maPhuKien);
    phuKienEdit.ifPresent(phuKien -> model.addAttribute("phuKien", phuKien));
    return "admin/phukien/editPhuKien";
  }

  @PostMapping("/save")
  public String save(PhuKien phuKien, @RequestParam("file") Optional<MultipartFile> fileData) throws IOException {
    MultipartFile file = null;

    if (fileData.isPresent())
      file = fileData.get();

    if (file != null && !file.isEmpty()) {
      Anhphukien anhphukien = new Anhphukien();
      anhphukien.setPhukien(phuKien);
      anhphukien.setFile(file);

      anhphukienService.saveAnhphukien(anhphukien);
    }
    phuKienService.savePhuKien(phuKien);
    return "redirect:/admin/phukien/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer maphukien,
      Model model) {
    Optional<PhuKien> phukien = phuKienService.findPhuKienById(maphukien);

    phukien.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhphukien anhphukien = new Anhphukien();
        anhphukien.setFile(file);
        anhphukien.setPhukien(dh);

        try {
          anhphukienService.saveAnhphukien(anhphukien);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/phukien/edit?id=" + maphukien;
  }

  @GetMapping("/delete")
  public String deletePhuKien(@RequestParam("id") Integer maPhuKien, Model model) {
    phuKienService.deletePhuKien(maPhuKien);
    return "redirect:/admin/phukien/";
  }
}
