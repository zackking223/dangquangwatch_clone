package edu.it10.dangquangwatch.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.it10.dangquangwatch.spring.entity.ThongKe;
import edu.it10.dangquangwatch.spring.service.ThongKeService;

@Controller
@RequestMapping(path = "/admin/thongke")
public class ThongkeController {
  @Autowired
  private ThongKeService thongKeService;

  @GetMapping("/")
  public String index(Model model) {

    List<ThongKe> thongKe = thongKeService.getAllThongKe();

    model.addAttribute("thongKes", thongKe);

    return "admin/thongke/index";
  }

}