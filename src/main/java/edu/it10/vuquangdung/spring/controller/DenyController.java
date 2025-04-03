package edu.it10.vuquangdung.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DenyController {
  @GetMapping("/access-denied")
  public String accessDenied() {
    return "access-denied";
  }
}
