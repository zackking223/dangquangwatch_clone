package edu.it10.dangquangwatch.spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin/thongke")
public class ThongkeController {

  @GetMapping("/")
  public String index(Model model){

    return "admin/thongke/index";
  }
}