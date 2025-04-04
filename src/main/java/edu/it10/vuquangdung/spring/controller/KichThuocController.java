package edu.it10.vuquangdung.spring.controller;

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

import edu.it10.vuquangdung.spring.AppCustomException.ErrorEnum;
import edu.it10.vuquangdung.spring.entity.KichThuoc;
import edu.it10.vuquangdung.spring.service.KichThuocService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/kichthuoc")
public class KichThuocController {
    @Autowired
    private KichThuocService kichThuocService;

    @GetMapping("/")
    public String index(
            HttpSession session,
            Model model,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> search) {
        int pageNum = 0;
        String searchStr = "";

        if (search.isPresent()) {
            searchStr = search.get().trim();
        }

        if (page.isPresent()) {
            pageNum = page.get() - 1;
        }

        Page<KichThuoc> data = kichThuocService.search(searchStr, pageNum);
        List<KichThuoc> kichThuocList = data.getContent();

        model.addAttribute("kichThuocList", kichThuocList);
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.INDEX.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/kichthuoc/index";
    }

    @GetMapping(value = "/add")
    public String add(HttpSession session, Model model) {
        model.addAttribute("kichThuoc", new KichThuoc());
        var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.ADD.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/kichthuoc/add";
    }

    @GetMapping(value = "/edit")
    public String edit(HttpSession session, @RequestParam String id, Model model) {
        Optional<KichThuoc> opt = kichThuocService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("kichThuoc", opt.get());
        } else {
            return "redirect:/admin/kichthuoc/";
        }
        var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.EDIT.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/kichthuoc/edit";
    }

    @PostMapping("/save")
    public String save(KichThuoc kichThuoc) {
        kichThuoc = kichThuocService.save(kichThuoc);
        return "redirect:/admin/kichthuoc/edit?id=" + kichThuoc.getKichThuocId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        kichThuocService.delete(id);
        return "redirect:/admin/kichthuoc/";
    }
}
