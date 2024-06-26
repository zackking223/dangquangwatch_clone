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

import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.service.AnhkinhmatService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;

@Controller
@RequestMapping(path = "/admin/kinhmat")
public class KinhMatController {
  @Autowired 
  private KinhMatService kinhMatService;
  @Autowired
  private AnhkinhmatService anhkinhmatService;

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

    Page<KinhMat> data = kinhMatService.searchKinhMat(searchStr, pageNum);
    List<KinhMat> kinhmats = data.getContent();

    model.addAttribute("kinhMats", kinhmats);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("sotrang", data.getTotalPages()); 

    return "admin/kinhmat/index";  
  }  

  @GetMapping("/add")  
  public String addKinhMat(Model model) {  
    model.addAttribute("kinhMat", new KinhMat());  
    return "admin/kinhmat/addKinhMat";  
  }  

  @GetMapping("/edit")  
  public String editKinhMat(@RequestParam("id") Integer maKinhMat, Model model) {  
    Optional<KinhMat> kinhMatEdit = kinhMatService.findKinhMatById(maKinhMat);  
    kinhMatEdit.ifPresent(kinhMat -> model.addAttribute("kinhMat", kinhMat));  
    return "admin/kinhmat/editKinhMat";  
  }  

  @PostMapping("/save")  
  public String save(KinhMat kinhMat, @RequestParam("file") Optional<MultipartFile> fileData) throws IOException {  
    MultipartFile file = null;

    if (fileData.isPresent())
      file = fileData.get();

    if (file != null && !file.isEmpty()) {
      Anhkinhmat anhkinhmat = new Anhkinhmat();
      anhkinhmat.setKinhmat(kinhMat);
      anhkinhmat.setFile(file);

      anhkinhmatService.saveAnhkinhmat(anhkinhmat);
    }
    kinhMatService.saveKinhMat(kinhMat);  
    return "redirect:/admin/kinhmat/";  
  } 
  
  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer makinhmat,
      Model model) {
    Optional<KinhMat> kinhmat = kinhMatService.findKinhMatById(makinhmat);

    kinhmat.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(dh);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/kinhmat/edit?id=" + makinhmat;
  }

  @GetMapping("/delete")  
  public String deleteKinhMat(@RequestParam("id") Integer maKinhMat, Model model) {  
    kinhMatService.deleteKinhMat(maKinhMat);  
    return "redirect:/admin/kinhmat/";  
  }
}
