package edu.it10.dangquangwatch.spring.controller;

import edu.it10.dangquangwatch.spring.entity.Anhbutky;
import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.service.AnhbutkyService;
import edu.it10.dangquangwatch.spring.service.ButkyService;
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
@RequestMapping(path = "/admin/butky")
public class ButkyController {
  @Autowired
  private ButkyService butkyService;
  @Autowired
  private AnhbutkyService anhbutkyService;

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

    Page<Butky> data = butkyService.searchButky(searchStr, fromStr, toStr, pageNum);
    List<Butky> butkys = data.getContent();

    model.addAttribute("butkys", butkys);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("sotrang", data.getTotalPages());

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
    butkyEdit.ifPresent(butky -> {
      model.addAttribute("butky", butky);
      model.addAttribute("images", butky.getImages());
    });
    return "/admin/butky/editButky";
  }

  @PostMapping("/update")
  public String update(Butky butky) {
    butkyService.saveButky(butky);
    return "redirect:/admin/butky/";
  }

  @PostMapping(value = "/save")
  public String save(Butky butky, @RequestParam("file") List<MultipartFile> files) throws IOException {
    for (MultipartFile file : files) {
      Anhbutky anhbutky = new Anhbutky();
      anhbutky.setFile(file);
      anhbutky.setButky(butky);

      try {
        anhbutkyService.saveAnhbutky(anhbutky);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    butkyService.saveButky(butky);
    return "redirect:/admin/butky/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer mabutky,
      Model model) {
    Optional<Butky> butky = butkyService.findButkyById(mabutky);

    butky.ifPresent(dh -> {
      for (MultipartFile file : files) {
        Anhbutky anhbutky = new Anhbutky();
        anhbutky.setFile(file);
        anhbutky.setButky(dh);

        try {
          anhbutkyService.saveAnhbutky(anhbutky);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    return "redirect:/admin/butky/edit?id=" + mabutky;
  }

  @GetMapping(value = "/delete")
  public String deleteButky(@RequestParam("id") Integer mabutky, Model model) {
    butkyService.deleteButky(mabutky);
    return "redirect:/admin/butky/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhbutkyService.deleteAnhbutky(maanh);
    return "redirect:/admin/butky/";
  }
}