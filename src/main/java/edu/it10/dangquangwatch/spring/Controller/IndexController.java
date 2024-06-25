package edu.it10.dangquangwatch.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.service.DonghoService;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import edu.it10.dangquangwatch.spring.service.TrangsucService;

@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private DonghoService donghoService;

    @Autowired
    private TrangsucService trangsucService;
    @Autowired
    private PhuKienService phuKienService;
    @Autowired
    private KinhMatService kinhMatService;
    @Autowired
    private ButkyService butkyService;

    @GetMapping("dongho")
    public String trangDongHo(Model model, @RequestParam("id") Integer madongho) {
        
        Optional<Dongho> donghoEdit = donghoService.findDonghoById(madongho);
        donghoEdit.ifPresent(dongho -> model.addAttribute("dongho", dongho));
        donghoEdit.ifPresent(dongho -> model.addAttribute("title", dongho.getTendongho()));
        
        return "dongho";
    }

    @GetMapping("phukien")
    public String trangPhuKien(Model model, @RequestParam("id") Integer maphukien) {
        
        Optional<PhuKien> phukienEdit = phuKienService.findPhuKienById(maphukien);
        phukienEdit.ifPresent(phukien -> model.addAttribute("phukien", phukien));
        phukienEdit.ifPresent(phukien -> model.addAttribute("title", phukien.getTenPhuKien()));
        
        return "phukien";
    }

    @GetMapping("butky")
    public String trangButKy(Model model, @RequestParam("id") Integer mabutky) {
        
        Optional<Butky> butkyEdit = butkyService.findButkyById(mabutky);
        butkyEdit.ifPresent(butky -> model.addAttribute("butky", butky));
        butkyEdit.ifPresent(butky -> model.addAttribute("title", butky.getTenbutky()));
        
        return "butky";
    }

    @GetMapping("kinhmat")
    public String trangKinhMat(Model model, @RequestParam("id") Integer makinhmat) {
        
        Optional<KinhMat> kinhmatEdit = kinhMatService.findKinhMatById(makinhmat);
        kinhmatEdit.ifPresent(kinhmat -> model.addAttribute("kinhmat", kinhmat));
        kinhmatEdit.ifPresent(kinhmat -> model.addAttribute("title", kinhmat.getTenSanPham()));
        
        return "kinhmat";
    }

    @GetMapping("trangsuc")
    public String trangTrangSuc(Model model, @RequestParam("id") Integer matrangsuc) {
        
        Optional<Trangsuc> trangsucEdit = trangsucService.findTrangsucById(matrangsuc);
        trangsucEdit.ifPresent(trangsuc -> model.addAttribute("trangsuc", trangsuc));
        trangsucEdit.ifPresent(trangsuc -> model.addAttribute("title", trangsuc.getTentrangsuc()));
        
        return "trangsuc";
    }

    @GetMapping
    public String index(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page,
            Model model) {
        if (search.isPresent()) {
            String searchStr = search.get();

            if (searchStr.isEmpty()) {
                return renderIndex(model);
            }

            int pageNum = 0;
            if (page.isPresent())
                pageNum = page.get();

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
