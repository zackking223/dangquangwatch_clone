package edu.it10.dangquangwatch.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/dongho/{id}")
    public String trangDongHo(Model model, @PathParam("id") Integer id) {
        Optional<Dongho> donghoEdit = donghoService.findDonghoById(id);
        donghoEdit.ifPresent(dongho -> model.addAttribute("dongho", dongho));
        donghoEdit.ifPresent(dongho -> model.addAttribute("title", dongho.getTendongho()));

        return "dongho";
    }

    @GetMapping("/phukien/{id}")
    public String trangPhuKien(Model model, @PathParam("id") Integer id) {
        Optional<PhuKien> phukienEdit = phuKienService.findPhuKienById(id);
        phukienEdit.ifPresent(phukien -> model.addAttribute("phukien", phukien));
        phukienEdit.ifPresent(phukien -> model.addAttribute("title", phukien.getTenPhuKien()));

        return "phukien";
    }

    @GetMapping("/butky/{id}")
    public String trangButKy(Model model, @PathParam("id") Integer id) {
        Optional<Butky> butkyEdit = butkyService.findButkyById(id);
        butkyEdit.ifPresent(butky -> model.addAttribute("butky", butky));
        butkyEdit.ifPresent(butky -> model.addAttribute("title", butky.getTenbutky()));

        return "butky";
    }

    @GetMapping("/kinhmat/{id}")
    public String trangKinhMat(Model model, @PathParam("id") Integer id) {
        Optional<KinhMat> kinhmatEdit = kinhMatService.findKinhMatById(id);
        kinhmatEdit.ifPresent(kinhmat -> model.addAttribute("kinhmat", kinhmat));
        kinhmatEdit.ifPresent(kinhmat -> model.addAttribute("title", kinhmat.getTenSanPham()));

        return "kinhmat";
    }

    @GetMapping("/trangsuc/{id}")
    public String trangTrangSuc(Model model, @PathParam("id") Integer id) {
        Optional<Trangsuc> trangsucEdit = trangsucService.findTrangsucById(id);
        trangsucEdit.ifPresent(trangsuc -> model.addAttribute("trangsuc", trangsuc));
        trangsucEdit.ifPresent(trangsuc -> model.addAttribute("title", trangsuc.getTentrangsuc()));

        return "trangsuc";
    }

    @GetMapping("/butky")
    public String trangDanhSachButKy(@RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {
        Page<Butky> data;
        String searchStr = "";
        int pageNum = 0;

        if (search.isPresent())
            searchStr = search.get();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = butkyService.searchButky(searchStr, pageNum);

        model.addAttribute("butkys", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        return "danhsach/butky/search";
    }

    @GetMapping("/dongho")
    public String trangDanhSachDongHo(@RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {
        Page<Dongho> data;
        String searchStr = "";
        int pageNum = 0;
        Dongho fieldData = new Dongho();

        if (search.isPresent())
            searchStr = search.get();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = donghoService.getAllDonghoByTendongho(searchStr, pageNum);

        model.addAttribute("dongho", fieldData);
        model.addAttribute("donghos", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());
        return "danhsach/dongho/search";
    }

    @GetMapping("/kinhmat")
    public String trangDanhSachKinhMat(@RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {
        Page<KinhMat> data;
        String searchStr = "";
        int pageNum = 0;

        if (search.isPresent())
            searchStr = search.get();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = kinhMatService.searchKinhMat(searchStr, pageNum);

        model.addAttribute("kinhmats", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        return "danhsach/kinhmat/search";
    }

    @GetMapping("/phukien")
    public String trangDanhSachPhuKien(@RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {
        Page<PhuKien> data;
        String searchStr = "";
        int pageNum = 0;

        if (search.isPresent())
            searchStr = search.get();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = phuKienService.searchPhuKien(searchStr, pageNum);

        model.addAttribute("phukiens", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        return "danhsach/phukien/search";
    }

    @GetMapping("/trangsuc")
    public String trangDanhSachTrangSuc(@RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {
        Page<Trangsuc> data;
        String searchStr = "";
        int pageNum = 0;

        if (search.isPresent())
            searchStr = search.get();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = trangsucService.searchTrangsuc(searchStr, pageNum);

        model.addAttribute("trangsucs", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        return "danhsach/trangsuc/search";
    }

    @PostMapping("/")
    public String postIndex() {
        return "redirect: /";
    }

    @GetMapping("/")
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

    @GetMapping("/error")
    public String errorRedirect() {
        return "redirect: /";
    }

    public Integer convertToNum(String id) {
        if (isNumeric(id)) {
            return Integer.parseInt(id);
        } else {
            return 1;
        }
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
