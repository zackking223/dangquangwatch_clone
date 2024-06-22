package edu.it10.dangquangwatch.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.it10.dangquangwatch.spring.service.DonghoService;

@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private DonghoService donghoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sanphambanchay", donghoService.getAllDonghoByTendongho("", 0, 10));
        model.addAttribute("donghoeposswiss", donghoService.getAllDonghoByTendongho("Epos Swiss", 0, 10));
        model.addAttribute("diamondd", donghoService.getAllDonghoByTendongho("Diamond", 0, 10));
        model.addAttribute("philippeauguste", donghoService.getAllDonghoByTendongho("Auguste", 0, 10));
        model.addAttribute("jacqueslemans", donghoService.getAllDonghoByTendongho("Jacques", 0, 10));
        model.addAttribute("ariesgold", donghoService.getAllDonghoByTendongho("Aries", 0, 10));
        model.addAttribute("atlanticswiss", donghoService.getAllDonghoByTendongho("Atlantic", 0, 10));
        model.addAttribute("citizen", donghoService.getAllDonghoByTendongho("Citizen", 0, 10));
        model.addAttribute("tsarbomba", donghoService.getAllDonghoByTendongho("Tsar", 0, 10));

        return "index";
    }
}
