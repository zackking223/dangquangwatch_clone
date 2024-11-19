package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.service.AnhtrangsucService;
import edu.it10.dangquangwatch.spring.service.TrangsucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/trangsuc")
public class TrangsucController {
  @Autowired
  private TrangsucService trangsucService;
  @Autowired
  private AnhtrangsucService anhtrangsucService;

  @GetMapping("/")
  public String index(Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("search") Optional<String> search,
      @RequestParam("from") Optional<String> from,
      @RequestParam("to") Optional<String> to) {
    String fromStr = "2001-01-01";
    String toStr = "3000-01-01";
    if (from.isPresent()) {
      if (!from.get().isEmpty()) {
        fromStr = from.get();
      }
    }
    if (to.isPresent()) {
      if (!to.get().isEmpty()) {
        toStr = to.get();
      }
    }
    int pageNum = 0;
    String searchStr = "";

    if (search.isPresent()) {
      searchStr = search.get().trim();
    }

    if (page.isPresent()) {
      pageNum = page.get() - 1;
    }

    Page<Trangsuc> data = trangsucService.search(searchStr, fromStr, toStr, pageNum);
    List<Trangsuc> trangsucs = data.getContent();

    model.addAttribute("trangsucs", trangsucs);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("sotrang", data.getTotalPages());
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");

    return "/admin/trangsuc/index";
  }

  @GetMapping(value = "/add")
  public String add(Model model) {
    model.addAttribute("trangsuc", new Trangsuc());
    return "/admin/trangsuc/addTrangsuc";
  }

  @GetMapping(value = "/edit")
  public String edit(@RequestParam("id") Integer matrangsuc, Model model) {
    Optional<Trangsuc> trangsucEdit = trangsucService.findById(matrangsuc);
    trangsucEdit.ifPresent(trangsuc -> {
      model.addAttribute("trangsuc", trangsuc);
      model.addAttribute("images", trangsuc.getImages());
    });
    return "/admin/trangsuc/editTrangsuc";
  }

  @PostMapping(value = "/update")
  public String update(Trangsuc trangsuc) {
    trangsucService.save(trangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping(value = "/save")
  public String save(Trangsuc trangsuc, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (trangsuc.getMatrangsuc() != null) {
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(trangsuc);
  
        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      trangsucService.save(trangsuc);
    } else {
      Trangsuc data = trangsucService.save(trangsuc);
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(data);
  
        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer matrangsuc,
      Model model) {
    Optional<Trangsuc> trangsuc = trangsucService.findById(matrangsuc);

    trangsuc.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhtrangsuc anhtrangsuc = new Anhtrangsuc();
        anhtrangsuc.setFile(file);
        anhtrangsuc.setTrangsuc(dh);

        try {
          anhtrangsucService.saveAnhtrangsuc(anhtrangsuc);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/trangsuc/edit?id=" + matrangsuc;
  }

  @GetMapping(value = "/activate")
  public String activate(@RequestParam("id") Integer matrangsuc, Model model) {
    trangsucService.activate(matrangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @GetMapping(value = "/deactivate")
  public String deactivate(@RequestParam("id") Integer matrangsuc, Model model) {
    trangsucService.deactivate(matrangsuc);
    return "redirect:/admin/trangsuc/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhtrangsucService.deleteAnhtrangsuc(maanh);
    return "redirect:/admin/trangsuc/";
  }
}