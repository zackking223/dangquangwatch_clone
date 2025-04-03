package edu.it10.vuquangdung.spring.controller;

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

import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;
import edu.it10.vuquangdung.spring.entity.response.SearchResponse;
import edu.it10.vuquangdung.spring.service.SanPhamBienTheService;
import edu.it10.vuquangdung.spring.service.SanPhamService;
import edu.it10.vuquangdung.spring.service.ThongKeService;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;

@Controller // danh dau bo dieu khien
public class IndexController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private SanPhamBienTheService sanPhamBienTheService;
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/sanpham/{id}")
    public String trangSanPham(Model model, @PathParam("search") Optional<String> search,
            @PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<SanPham> opt = sanPhamService.findById(id);

        if (opt.isPresent()) {
            String searchStr = "";
            if (search.isPresent())
                searchStr = search.get().trim();
            thongKeService.incLuotXemSanPham();
            model.addAttribute("sanPham", opt.get());
            model.addAttribute("title", opt.get().getTen());
            model.addAttribute("search", searchStr);
            model.addAttribute("searchPath", "/sanpham");
            return "sanpham";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/bienthe/{id}")
    public String toParent(@PathVariable("id") String slug) {
        int id = convertToNum(slug);

        Optional<SanPhamBienThe> opt = sanPhamBienTheService.findById(id);

        if (opt.isPresent()) {
            return "redirect:/sanpham/" + opt.get().getSanPhamId();
        } else {
            return "redirect:/";
        }
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
            Page<SanPham> sanPham_page = sanPhamService.searchAvaiable(searchStr, "2001-01-01", "3000-01-01", pageNum);
            soTrang_list.add(sanPham_page.getTotalPages());
            model.addAttribute("search", searchStr);
            model.addAttribute("sanPhams", sanPham_page.getContent());
            model.addAttribute("page", pageNum);
            model.addAttribute("sotrang", Collections.max(soTrang_list));

            return "search";
        } else {
            Page<SanPham> sanPham_page = sanPhamService.searchAvaiable("", "2001-01-01", "3000-01-01", 0);
            model.addAttribute("sanPhams", sanPham_page.getContent());
            return renderIndex(model);
        }
    }

    @GetMapping("/searchall")
    public ResponseEntity<SearchResponse<SanPham>> searchAll(@RequestParam("query") Optional<String> query) {
        if (query.isPresent()) {
            String searchStr = query.get().trim();

            if (searchStr.isEmpty()) {
                return ResponseEntity.badRequest().body(new SearchResponse<SanPham>(false, "Query string is empty!"));
            }

            List<SanPham> itemList = sanPhamService.search(searchStr);

            return ResponseEntity.ok().body(new SearchResponse<SanPham>(itemList.size() > 0, searchStr, itemList));
            
        } else {
            return ResponseEntity.badRequest().body(new SearchResponse<SanPham>(false, "Không có chuỗi tìm kiếm!"));
        }
    }

    private String renderIndex(Model model) {
        thongKeService.incLuotTruyCap();
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
