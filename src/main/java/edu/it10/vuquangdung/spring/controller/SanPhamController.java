package edu.it10.vuquangdung.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.it10.vuquangdung.spring.AppCustomException.ControllerException;
import edu.it10.vuquangdung.spring.AppCustomException.ErrorEnum;
import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.MauSac;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;
import edu.it10.vuquangdung.spring.entity.response.ApiResponse;
import edu.it10.vuquangdung.spring.request.AddAnhSanPhamRequest;
import edu.it10.vuquangdung.spring.request.AddBienTheRequest;
import edu.it10.vuquangdung.spring.request.AddSanPhamRequest;
import edu.it10.vuquangdung.spring.service.AnhSanPhamService;
import edu.it10.vuquangdung.spring.service.KichThuocService;
import edu.it10.vuquangdung.spring.service.LoaiService;
import edu.it10.vuquangdung.spring.service.MauSacService;
import edu.it10.vuquangdung.spring.service.SanPhamBienTheService;
import edu.it10.vuquangdung.spring.service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping(path = "/admin/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private LoaiService loaiService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private SanPhamBienTheService sanPhamBienTheService;
    @Autowired
    private AnhSanPhamService anhSanPhamService;

    @GetMapping("/")
    public String index(
            HttpSession session,
            @RequestParam Optional<String> search,
            @RequestParam Optional<String> kichThuoc,
            @RequestParam Optional<String> mauSac,
            @RequestParam Optional<String> loai,
            @RequestParam Optional<Integer> giaTien,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> from,
            @RequestParam Optional<String> to,
            Model model) {
        String fromStr = "2001-01-01";
        String toStr = "3000-01-01";
        String kichThuocStr = "";
        String mauSacStr = "";
        String loaiStr = "";
        Integer giaTienNum = 0;

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
        if (giaTien.isPresent()) {
            if (giaTien.get() > 0) {
                giaTienNum = giaTien.get();
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

        Page<SanPham> data = sanPhamService.search(searchStr, fromStr, toStr, pageNum);

        model.addAttribute("sanPhamList", data.getContent());
        model.addAttribute("page", pageNum);
        model.addAttribute("search", searchStr);
        model.addAttribute("kichThuoc", kichThuocStr);
        model.addAttribute("mauSac", mauSacStr);
        model.addAttribute("loai", loaiStr);
        model.addAttribute("giaTien", giaTienNum);
        model.addAttribute("from", from.isPresent() ? from.get() : "");
        model.addAttribute("to", to.isPresent() ? to.get() : "");
        model.addAttribute("sotrang", data.getTotalPages());
        model.addAttribute("kichThuocList", kichThuocList);
        model.addAttribute("mauSacList", mauSacList);
        model.addAttribute("loaiList", loaiList);

        var errorMessage = session.getAttribute(ErrorEnum.INDEX.name());
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute(ErrorEnum.INDEX.name());
        }

        return "admin/sanpham/index";
    }

    @GetMapping("/add")
    public String add(HttpSession session, Model model) {
        model.addAttribute("sanpham", new SanPham());
        var errorMessage = session.getAttribute(ErrorEnum.ADD.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.ADD.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        return "admin/sanpham/add";
    }

    @GetMapping("/edit")
    public String edit(HttpSession session, @RequestParam Integer id, Model model) {
        var errorMessage = session.getAttribute(ErrorEnum.EDIT.name());
        if (errorMessage != null) {
            session.removeAttribute(ErrorEnum.EDIT.name());
            model.addAttribute("errorMessage", errorMessage);
        }
        Optional<SanPham> opt = sanPhamService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("sanpham", opt.get());
            model.addAttribute("bienthes", opt.get().getBienTheList());
            return "admin/sanpham/edit";
        } else {
            throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/sanpham/");
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addSanPham(@RequestBody AddSanPhamRequest request) throws IOException {
        SanPham sanPham = request.getSanPham();
        List<SanPhamBienThe> bienThes = request.getBienThes();
        List<MultipartFile> files = request.getFiles();

        // Kiểm tra số lượng biến thể và file có khớp không
        if (bienThes == null || bienThes.isEmpty()) {
            throw new ControllerException("Phải có ít nhất 1 biến thể!", ErrorEnum.ADD, "/admin/sanpham/add");
        }

        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(true, "Sản phẩm phải có ít nhất 1 hình ảnh!"));
        }

        // Bước 1: Lưu sản phẩm
        sanPham = sanPhamService.save(sanPham);

        // Bước 2: Lưu danh sách biến thể
        for (SanPhamBienThe bienThe : bienThes) {
            bienThe.setSanPham(sanPham);
            bienThe = sanPhamBienTheService.save(bienThe);
        }

        // Bước 3: Lưu danh sách ảnh cho sản phẩm
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                AnhSanPham anh = new AnhSanPham();
                anh.setSanPham(sanPham);
                anh.setFile(file);
                anhSanPhamService.save(anh);
            }
        }

        return ResponseEntity.ok().body(new ApiResponse(true, "Thêm thành công!"));
    }

    @PostMapping("/editsanpham")
    @Transactional
    public String editSanPham(@RequestBody SanPham sanPham) {
        sanPhamService.save(sanPham); // Cập nhật SanPham
        return "redirect:/admin/sanpham/edit?id=" + sanPham.getId();
    }

    @PostMapping("/addbienthe")
    @Transactional
    public ResponseEntity<ApiResponse> addBienThe(@RequestBody AddBienTheRequest request) throws IOException {
        SanPhamBienThe bienThe = request.getBienThe();
        Integer sanPhamId = request.getSanPhamId();

        Optional<SanPham> opt = sanPhamService.findById(sanPhamId);

        if (opt.isPresent()) {
            bienThe.setSanPham(opt.get());
            bienThe = sanPhamBienTheService.save(bienThe);
            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm biến thể thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm không tồn tại!"));
        }
    }

    @PostMapping("/addanh")
    @Transactional
    public ResponseEntity<ApiResponse> addAnh(@RequestBody AddAnhSanPhamRequest request) throws IOException {
        Integer sanPhamId = request.getSanPhamId();
        List<MultipartFile> files = request.getFiles();

        Optional<SanPham> opt = sanPhamService.findById(sanPhamId);

        if (opt.isPresent()) {
            for (MultipartFile file : files) {
                AnhSanPham anh = new AnhSanPham();
                anh.setSanPham(opt.get());
                anh.setFile(file);
                anhSanPhamService.save(anh);
            }

            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm ảnh thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Biến thể không tồn tại!"));
        }
    }

    @GetMapping("/deleteanh")
    @Transactional
    public ResponseEntity<ApiResponse> deleteAnh(@RequestParam("id") Integer anhId) throws IOException {
        Optional<AnhSanPham> opt = anhSanPhamService.findById(anhId);
        if (opt.isPresent()) {
            anhSanPhamService.delete(anhId);
            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm ảnh thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Ảnh không tồn tại!"));
        }
    }

    @GetMapping("/deletebienthe")
    @Transactional
    public ResponseEntity<ApiResponse> deleteBienThe(@RequestParam("id") Integer bienTheId) throws IOException {
        Optional<SanPhamBienThe> opt = sanPhamBienTheService.findById(bienTheId);

        if (opt.isPresent()) {
            sanPhamBienTheService.delete(bienTheId);
            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm ảnh thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Biến thể không tồn tại!"));
        }

    }

    @GetMapping("/deletesanpham")
    @Transactional
    public ResponseEntity<ApiResponse> deleteSanPham(@RequestParam("id") Integer sanPhamId) throws IOException {
        Optional<SanPham> opt = sanPhamService.findById(sanPhamId);

        if (opt.isPresent()) {
            for (SanPhamBienThe bienThe : opt.get().getBienTheList()) {
                sanPhamBienTheService.delete(bienThe.getId());
            }
            for (AnhSanPham anh : opt.get().getImages()) {
                anhSanPhamService.delete(anh.getId());
            }
            sanPhamService.delete(sanPhamId);
            return ResponseEntity.ok().body(new ApiResponse(true, "Xóa sản phẩm thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm không tồn tại!"));
        }
    }

    @GetMapping("/activatesanpham")
    @Transactional
    public ResponseEntity<ApiResponse> activateSanPham(@RequestParam("id") Integer sanPhamId) throws IOException {
        sanPhamService.activate(sanPhamId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }

    @GetMapping("/deactivatesanpham")
    @Transactional
    public ResponseEntity<ApiResponse> deactivateSanPham(@RequestParam("id") Integer sanPhamId) throws IOException {
        sanPhamService.deactivate(sanPhamId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }

    @GetMapping("/activatesanphambienthe")
    @Transactional
    public ResponseEntity<ApiResponse> activateSanPhamBienThe(@RequestParam("id") Integer bienTheId) throws IOException {
        sanPhamBienTheService.activate(bienTheId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }

    @GetMapping("/deactivatesanphambienthe")
    @Transactional
    public ResponseEntity<ApiResponse> deactivateSanPhamBienThe(@RequestParam("id") Integer bienTheId) throws IOException {
        sanPhamBienTheService.deactivate(bienTheId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }
}
