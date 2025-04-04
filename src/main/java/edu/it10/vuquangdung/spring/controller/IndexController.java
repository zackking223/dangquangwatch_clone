package edu.it10.vuquangdung.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import edu.it10.vuquangdung.spring.entity.MauSac;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;
import edu.it10.vuquangdung.spring.entity.response.SearchResponse;
import edu.it10.vuquangdung.spring.service.KichThuocService;
import edu.it10.vuquangdung.spring.service.LoaiService;
import edu.it10.vuquangdung.spring.service.MauSacService;
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
    private LoaiService loaiService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private KichThuocService kichThuocService;
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
    public String index(Model model) {
        Page<SanPham> sanPham_page = sanPhamService.searchAvaiable("", "2001-01-01", "3000-01-01", 0);
        model.addAttribute("sanPhams", sanPham_page.getContent());
        return renderIndex(model);
    }

    @GetMapping("/shop")
    public String browseShop(
            @RequestParam Optional<String> search,
            @RequestParam Optional<String> kichThuoc,
            @RequestParam Optional<String> mauSac,
            @RequestParam Optional<String> loai,
            @RequestParam Optional<Integer> giaTienFrom,
            @RequestParam Optional<Integer> giaTienTo,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> from,
            @RequestParam Optional<String> to,
            Model model) {

        String fromStr = null;
        String toStr = null;
        String kichThuocStr = "";
        String mauSacStr = "";
        String loaiStr = "";
        Integer giaTienFromNum = 0;
        Integer giaTienToNum = 0;

        if (from.isPresent()) {
            if (!from.get().isEmpty()) {
                fromStr = from.get();
            }
        }
        if (to.isPresent()) {
            if (!to.get().isEmpty()) {
                toStr = to.get();
            }
        }
        if (kichThuoc.isPresent()) {
            if (!kichThuoc.get().isEmpty()) {
                kichThuocStr = kichThuoc.get();
            }
        }
        if (mauSac.isPresent()) {
            if (!mauSac.get().isEmpty()) {
                mauSacStr = mauSac.get();
            }
        }
        if (loai.isPresent()) {
            if (!loai.get().isEmpty()) {
                loaiStr = loai.get();
            }
        }
        if (giaTienFrom.isPresent()) {
            if (giaTienFrom.get() > 0) {
                giaTienFromNum = giaTienFrom.get();
            }
        }
        if (giaTienTo.isPresent()) {
            if (giaTienTo.get() > 0) {
                giaTienToNum = giaTienTo.get();
            }
        }
        String searchStr = "";
        if (search.isPresent()) {
            searchStr = search.get();
        }
        int pageNum = 0;
        if (page.isPresent()) {
            pageNum = page.get();
        }
        List<String> kichThuocList = kichThuocService.getAllId();
        List<MauSac> mauSacList = mauSacService.getAll();
        List<String> loaiList = loaiService.getAllId();

        Page<SanPham> data = sanPhamService.search(searchStr, kichThuocStr, mauSacStr, loaiStr, giaTienFromNum, giaTienToNum, fromStr,
                toStr, PageRequest.of(pageNum, 10));

        model.addAttribute("sanPhamList", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("kichThuoc", kichThuocStr);
        model.addAttribute("mauSac", mauSacStr);
        model.addAttribute("loai", loaiStr);
        model.addAttribute("giaTien", giaTienFromNum);
        model.addAttribute("from", from.isPresent() ? from.get() : "");
        model.addAttribute("to", to.isPresent() ? to.get() : "");
        model.addAttribute("sotrang", data.getTotalPages());
        model.addAttribute("kichThuocList", kichThuocList);
        model.addAttribute("mauSacList", mauSacList);
        model.addAttribute("loaiList", loaiList);
        return "search";
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
