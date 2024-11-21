package edu.it10.dangquangwatch.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.entity.LichSuKho;
import edu.it10.dangquangwatch.spring.service.LichSuKhoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/admin/lichsukho")
public class LichSuKhoController {
  @Autowired
  private LichSuKhoService lichSuKhoService;

  @GetMapping("/")
  public String index(
    HttpSession session,
    Model model,
    @RequestParam("page") Optional<Integer> page,
    @RequestParam("hanhdong") Optional<String> hanhDong,
    @RequestParam("thongtin") Optional<String> thongTin,
    @RequestParam("from") Optional<String> from,
    @RequestParam("to") Optional<String> to,
    @RequestParam("nguoithuchien") Optional<String> nguoiThucHien
  ) {
    String hanhDongStr = "";
    if (hanhDong.isPresent()) {
      hanhDongStr = hanhDong.get();
    }
    model.addAttribute("hanhdong", hanhDongStr);
    
    String thongTinStr = "";
    if (thongTin.isPresent()) {
      thongTinStr = thongTin.get();
    }
    model.addAttribute("thongtin", thongTinStr);
    
    String fromStr = "";
    if (from.isPresent()) {
      fromStr = from.get();
    }
    model.addAttribute("from", fromStr);
    
    String toStr = "";
    if (to.isPresent()) {
      toStr = to.get();
    }
    model.addAttribute("to", toStr);
    
    String nguoiThucHienStr = "";
    if (nguoiThucHien.isPresent()) {
      nguoiThucHienStr = nguoiThucHien.get();
    }
    model.addAttribute("nguoithuchien", nguoiThucHienStr);

    int pageNum = 0;
    if (page.isPresent()) {
      pageNum = page.get() - 1;
    }
    model.addAttribute("page", pageNum);

    Page<LichSuKho> activities = lichSuKhoService.search(thongTinStr, hanhDongStr, nguoiThucHienStr, fromStr, toStr, pageNum);
    
    model.addAttribute("activities", activities.getContent());
    model.addAttribute("sotrang", activities.getTotalPages());

    var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
    if (errorMessage != null) {
      session.removeAttribute(ErrorEnum.INDEX.name());
      model.addAttribute("errorMessage", errorMessage);
    }

    return "admin/lichsukho/index";
  }
}
