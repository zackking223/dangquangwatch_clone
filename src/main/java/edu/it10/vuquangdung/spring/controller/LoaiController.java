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
import edu.it10.vuquangdung.spring.entity.Loai;
import edu.it10.vuquangdung.spring.service.LoaiService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/loai")
public class LoaiController {
    @Autowired
    private LoaiService loaiService;

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

        Page<Loai> data = loaiService.search(searchStr, pageNum);
        List<Loai> loaiList = data.getContent();

        model.addAttribute("loaiList", loaiList);
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.INDEX.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/loai/index";
    }

    @GetMapping(value = "/add")
    public String add(HttpSession session, Model model) {
        model.addAttribute("loai", new Loai());
        var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.ADD.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/loai/add";
    }

    @GetMapping(value = "/edit")
    public String edit(HttpSession session, @RequestParam String id, Model model) {
        Optional<Loai> opt = loaiService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("loai", opt.get());
        } else {
            return "redirect:/admin/loai/";
        }
        var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.EDIT.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/loai/edit";
    }

    @PostMapping("/save")
    public String save(Loai loai) {
        loai = loaiService.save(loai);
        return "redirect:/admin/loai/edit?id=" + loai.getLoaiId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        loaiService.delete(id);
        return "redirect:/admin/loai/";
    }
}
