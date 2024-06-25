package edu.it10.dangquangwatch.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.service.DonghoService;
// import edu.it10.dangquangwatch.spring.service.ButkyService;
// import edu.it10.dangquangwatch.spring.service.KinhMatService;
// import edu.it10.dangquangwatch.spring.service.PhuKienService;
// import edu.it10.dangquangwatch.spring.service.TrangsucService;

@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private DonghoService donghoService;
    // @Autowired
    // private TrangsucService trangsucService;
    // @Autowired
    // private PhuKienService phuKienService;
    // @Autowired
    // private KinhMatService kinhMatService;
    // @Autowired
    // private ButkyService butkyService;

    @GetMapping
    public String index(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page, Model model) {
        if (search.isPresent()) {
            String searchStr = search.get();

            if (searchStr.isEmpty()) {
                return renderIndex(model);
            }

            int pageNum = 0;
            if (page.isPresent()) pageNum = page.get();

            model.addAttribute("search", searchStr);
            model.addAttribute("donghos", donghoService.getAllDonghoByTendongho(searchStr, pageNum).getContent());

            return "search";
        } else {
            return renderIndex(model);
        }
    }

    private String renderIndex(Model model) {
        model.addAttribute("sanphambanchay", donghoService.getAllDonghoByTendongho("", 0).getContent());
        model.addAttribute("donghoeposswiss", donghoService.getAllDonghoByTendongho("Epos Swiss", 0).getContent());
        model.addAttribute("diamondd", donghoService.getAllDonghoByTendongho("Diamond", 0).getContent());
        model.addAttribute("philippeauguste", donghoService.getAllDonghoByTendongho("Auguste", 0).getContent());
        model.addAttribute("jacqueslemans", donghoService.getAllDonghoByTendongho("Jacques", 0).getContent());
        model.addAttribute("ariesgold", donghoService.getAllDonghoByTendongho("Aries", 0).getContent());
        model.addAttribute("atlanticswiss", donghoService.getAllDonghoByTendongho("Atlantic", 0).getContent());
        model.addAttribute("citizen", donghoService.getAllDonghoByTendongho("Citizen", 0).getContent());
        model.addAttribute("tsarbomba", donghoService.getAllDonghoByTendongho("Tsar", 0).getContent());

        return "index";
    }

    @GetMapping("error")
    public String errorRedirect() {
        return "redirect: /";
    }
}
