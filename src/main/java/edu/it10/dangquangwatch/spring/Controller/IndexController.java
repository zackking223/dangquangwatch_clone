package edu.it10.dangquangwatch.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.service.DonghoService;

@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private DonghoService donghoService;

    @GetMapping
    public String index(Model model) {
        List<Dongho> donghos = donghoService.getAllDongho();

        model.addAttribute("sanphambanchay", donghos);
        model.addAttribute("donghoeposswiss", donghos);
        model.addAttribute("diamondd", donghos);
        model.addAttribute("philippeauguste", donghos);
        model.addAttribute("jacqueslemans", donghos);
        model.addAttribute("ariesgold", donghos);
        model.addAttribute("atlanticswiss", donghos);
        model.addAttribute("citizen", donghos);
        model.addAttribute("tsarbomba", donghos);

        return "index";
    }
}
