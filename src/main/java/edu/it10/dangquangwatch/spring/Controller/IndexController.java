package edu.it10.dangquangwatch.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.entity.Dongho;
import edu.it10.dangquangwatch.spring.entity.KinhMat;
import edu.it10.dangquangwatch.spring.entity.PhuKien;
import edu.it10.dangquangwatch.spring.entity.Trangsuc;
import edu.it10.dangquangwatch.spring.entity.response.ResultItem;
import edu.it10.dangquangwatch.spring.entity.response.SearchResponse;
import edu.it10.dangquangwatch.spring.service.DonghoService;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import edu.it10.dangquangwatch.spring.service.KinhMatService;
import edu.it10.dangquangwatch.spring.service.PhuKienService;
import edu.it10.dangquangwatch.spring.service.ThongKeService;
import edu.it10.dangquangwatch.spring.service.TrangsucService;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;

@Controller // danh dau bo dieu khien
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
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/dongho/{id}") //
    public String trangDongHo(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<Dongho> donghoEdit = donghoService.findById(id);// dich vu dong ho -> repository -> csdl
        donghoEdit.ifPresent(dongho -> {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("dongho", dongho); // them thuoc tinh cho trang .html (thymeleaf) su dung
            model.addAttribute("title", dongho.getTendongho());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/dongho");
        });

        return "dongho";
    }

    @GetMapping("/phukien/{id}")
    public String trangPhuKien(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<PhuKien> phukienEdit = phuKienService.findById(id);
        phukienEdit.ifPresent(phukien -> {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("phukien", phukien);
            model.addAttribute("title", phukien.getTenPhuKien());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/dongho");
        });

        return "phukien";
    }

    @GetMapping("/butky/{id}")
    public String trangButKy(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<Butky> butkyEdit = butkyService.findById(id);
        butkyEdit.ifPresent(butky -> {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("butky", butky);
            model.addAttribute("title", butky.getTenbutky());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/dongho");
        });

        return "butky";
    }

    @GetMapping("/kinhmat/{id}")
    public String trangKinhMat(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<KinhMat> kinhmatEdit = kinhMatService.findById(id);
        kinhmatEdit.ifPresent(kinhmat -> {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("kinhmat", kinhmat);
            model.addAttribute("title", kinhmat.getTenKinhMat());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/dongho");
        });

        return "kinhmat";
    }

    @GetMapping("/trangsuc/{id}")
    public String trangTrangSuc(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<Trangsuc> trangsucEdit = trangsucService.findById(id);
        trangsucEdit.ifPresent(trangsuc -> {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("trangsuc", trangsuc);
            model.addAttribute("title", trangsuc.getTentrangsuc());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/dongho");
        });

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
            searchStr = search.get().trim();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = butkyService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);

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
            searchStr = search.get().trim();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = donghoService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);

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
            searchStr = search.get().trim();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = kinhMatService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);

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
            searchStr = search.get().trim();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = phuKienService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);

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
            searchStr = search.get().trim();
        if (page.isPresent())
            pageNum = page.get() - 1;
        data = trangsucService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);

        model.addAttribute("trangsucs", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("sotrang", data.getTotalPages());

        return "danhsach/trangsuc/search";
    }

    @PostMapping("/")
    public String postIndex() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(@RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page,
            Model model) {
        if (search.isPresent()) {
            String searchStr = search.get().trim();

            if (searchStr.isEmpty()) {
                return renderIndex(model);
            }

            int pageNum = 0;
            if (page.isPresent()) {
                pageNum = page.get() - 1;
            }

            List<Integer> soTrang_list = new ArrayList<Integer>();
            Page<Dongho> dongho_page = donghoService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01",
                    pageNum);
            soTrang_list.add(dongho_page.getTotalPages());

            Page<Trangsuc> trangsuc_page = trangsucService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01",
                    pageNum);
            soTrang_list.add(trangsuc_page.getTotalPages());

            Page<PhuKien> phukien_page = phuKienService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);
            soTrang_list.add(phukien_page.getTotalPages());

            Page<Butky> butky_page = butkyService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);
            soTrang_list.add(butky_page.getTotalPages());

            Page<KinhMat> kinhmat_page = kinhMatService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);
            soTrang_list.add(kinhmat_page.getTotalPages());

            model.addAttribute("search", searchStr);
            model.addAttribute("donghos", dongho_page.getContent());
            model.addAttribute("trangsucs", trangsuc_page.getContent());
            model.addAttribute("kinhmats", kinhmat_page.getContent());
            model.addAttribute("phukiens", phukien_page.getContent());
            model.addAttribute("butkys", butky_page.getContent());
            model.addAttribute("page", pageNum);
            model.addAttribute("sotrang", Collections.max(soTrang_list));

            return "search";
        } else {
            return renderIndex(model);
        }
    }

    @GetMapping("/searchall")
    public ResponseEntity<SearchResponse<ResultItem>> searchAll(@RequestParam("query") Optional<String> query) {
        if (query.isPresent()) {
            String searchStr = query.get().trim();

            if (searchStr.isEmpty()) {
                return ResponseEntity.ok().body(new SearchResponse<ResultItem>(false, "Query string is empty!"));
            }

            List<ResultItem> itemList = new ArrayList<ResultItem>();

            List<Dongho> donghoList = donghoService.search(searchStr);
            for (Dongho dongho : donghoList) {
                ResultItem item = new ResultItem();
                item.setTenSanPham(dongho.getTendongho());
                item.setMaSanPham(dongho.getMadongho());
                item.setLoaiSanPham("dongho");
                itemList.add(item);
            }
            
            List<PhuKien> phukienList = phuKienService.search(searchStr);
            for (PhuKien phuKien : phukienList) {
                ResultItem item = new ResultItem();
                item.setTenSanPham(phuKien.getTenPhuKien());
                item.setMaSanPham(phuKien.getMaPhuKien());
                item.setLoaiSanPham("phukien");
                itemList.add(item);
            }
            
            List<Butky> butkyList = butkyService.search(searchStr);
            for (Butky butky : butkyList) {
                ResultItem item = new ResultItem();
                item.setTenSanPham(butky.getTenbutky());
                item.setMaSanPham(butky.getMabutky());
                item.setLoaiSanPham("butky");
                itemList.add(item);
            }
            
            List<KinhMat> kinhMatList = kinhMatService.search(searchStr);
            for (KinhMat kinhMat : kinhMatList) {
                ResultItem item = new ResultItem();
                item.setTenSanPham(kinhMat.getTenKinhMat());
                item.setMaSanPham(kinhMat.getMaKinhMat());
                item.setLoaiSanPham("kinhmat");
                itemList.add(item);
            }
            
            List<Trangsuc> trangsucList = trangsucService.search(searchStr);
            for (Trangsuc trangsuc : trangsucList) {
                ResultItem item = new ResultItem();
                item.setTenSanPham(trangsuc.getTentrangsuc());
                item.setMaSanPham(trangsuc.getMatrangsuc());
                item.setLoaiSanPham("trangsuc");
                itemList.add(item);
            }

            return ResponseEntity.ok().body(new SearchResponse<ResultItem>(itemList.size() > 0, searchStr, itemList));
            
        } else {
            return ResponseEntity.ok().body(new SearchResponse<ResultItem>(false, "No query string provided!"));
        }
    }

    private String renderIndex(Model model) {
        thongKeService.incLuotTruyCap();
        model.addAttribute("sanphambanchay",
                donghoService.searchAvaiable("", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("donghoeposswiss",
                donghoService.searchAvaiable("Epos Swiss", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("diamondd",
                donghoService.searchAvaiable("Diamond", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("philippeauguste",
                donghoService.searchAvaiable("Auguste", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("jacqueslemans",
                donghoService.searchAvaiable("Jacques", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("ariesgold",
                donghoService.searchAvaiable("Aries", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("atlanticswiss",
                donghoService.searchAvaiable("Atlantic", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("citizen",
                donghoService.searchAvaiable("Citizen", "2001-01-01", "3000-01-01", 0).getContent());
        model.addAttribute("tsarbomba",
                donghoService.searchAvaiable("Tsar", "2001-01-01", "3000-01-01", 0).getContent());

        return "index";
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
