package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.entity.Anhdongho;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.service.AnhdonghoService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/dongho")
public class DonghoController {
  @Autowired
  private DonghoService donghoService;
  @Autowired
  private AnhdonghoService anhdonghoService;

  @GetMapping("/")
  public String index(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page,
      Optional<Dongho> dongho, Model model) {
    model.addAttribute("dongho", new Dongho());
    Page<Dongho> data;
    String searchStr = "";
    int pageNum = 0;
    Dongho fieldData = new Dongho();

    if (search.isPresent())
      searchStr = search.get();
    if (page.isPresent())
      pageNum = page.get() - 1;

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
    List<String> gioitinh_options = new ArrayList<String>();
    gioitinh_options.add("Nam");
    gioitinh_options.add("Nữ");

    Optional<Dongho> donghoEdit = donghoService.findDonghoById(madongho);
    donghoEdit.ifPresent(dongho -> {
      model.addAttribute("dongho", dongho);
      model.addAttribute("images", dongho.getImages());
      model.addAttribute("gioitinh", dongho.getGioitinh());
      model.addAttribute("gioitinh_options", gioitinh_options);
    });
    return "admin/dongho/editDongHo";
  }

  @PostMapping("/update")
  public String update(Dongho dongho) {
    donghoService.saveDongho(dongho);
    return "redirect:/admin/dongho/edit?id=" + dongho.getMadongho();
  }

  @PostMapping(value = "/save")
  public String save(Dongho dongho, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (dongho.getMadongho() != null) {
      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(dongho);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      donghoService.saveDongho(dongho);
    } else {
      Dongho data = donghoService.saveDongho(dongho);

      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(data);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return "redirect:/admin/dongho/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer madongho,
      Model model) {
    Optional<Dongho> dongho = donghoService.findDonghoById(madongho);

    dongho.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhdongho anhdongho = new Anhdongho();
        anhdongho.setFile(file);
        anhdongho.setDongho(dh);

        try {
          anhdonghoService.saveAnhdongho(anhdongho);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/dongho/edit?id=" + madongho;
  }

  @GetMapping("/delete")
  public String deleteDongho(@RequestParam("id") Integer madongho, Model model) {
    donghoService.deleteDongho(madongho);
    return "redirect:/admin/dongho/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhdonghoService.deleteAnhdongho(maanh);
    return "redirect:/admin/dongho/";
  }
}