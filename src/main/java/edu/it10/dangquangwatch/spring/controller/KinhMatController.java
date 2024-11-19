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

    Page<KinhMat> data = kinhMatService.search(searchStr, fromStr, toStr, pageNum);
    List<KinhMat> kinhmats = data.getContent();

    model.addAttribute("kinhMats", kinhmats);
    model.addAttribute("page", pageNum);
    model.addAttribute("search", searchStr);
    model.addAttribute("from", from.isPresent() ? from.get() : "");
    model.addAttribute("to", to.isPresent() ? to.get() : "");
    model.addAttribute("sotrang", data.getTotalPages());

    return "admin/kinhmat/index";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("kinhMat", new KinhMat());
    return "admin/kinhmat/addKinhMat";
  }

  @GetMapping("/edit")
  public String edit(@RequestParam("id") Integer maKinhMat, Model model) {
    Optional<KinhMat> kinhMatEdit = kinhMatService.findById(maKinhMat);
    kinhMatEdit.ifPresent(kinhMat -> {
      model.addAttribute("kinhMat", kinhMat);
      model.addAttribute("images", kinhMat.getImages());
    });
    return "admin/kinhmat/editKinhMat";
  }

  @PostMapping("/update")
  public String update(KinhMat kinhMat) {
    kinhMatService.save(kinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/save")
  public String save(KinhMat kinhMat, @RequestParam("file") List<MultipartFile> files) throws IOException {
    if (kinhMat.getMaKinhMat() != null) {
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(kinhMat);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      kinhMatService.save(kinhMat);
    } else {
      KinhMat data = kinhMatService.save(kinhMat);
      for (MultipartFile file : files) {
        Anhkinhmat anhkinhmat = new Anhkinhmat();
        anhkinhmat.setFile(file);
        anhkinhmat.setKinhmat(data);

        try {
          anhkinhmatService.saveAnhkinhmat(anhkinhmat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/uploadimage")
  public String uploadImage(@RequestParam("file") List<MultipartFile> files, @RequestParam("id") Integer makinhmat,
      Model model) {
    Optional<KinhMat> kinhmat = kinhMatService.findById(makinhmat);

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

  @GetMapping("/activate")
  public String activate(@RequestParam("id") Integer maKinhMat, Model model) {
    kinhMatService.activate(maKinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @GetMapping("/deactivate")
  public String deactivate(@RequestParam("id") Integer maKinhMat, Model model) {
    kinhMatService.deactivate(maKinhMat);
    return "redirect:/admin/kinhmat/";
  }

  @PostMapping("/deleteimage")
  public String deleteImage(@RequestParam("id") Integer maanh) throws IOException {
    anhkinhmatService.deleteAnhkinhmat(maanh);
    return "redirect:/admin/kinhmat/";
  }
}
