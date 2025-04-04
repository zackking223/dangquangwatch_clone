package edu.it10.vuquangdung.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.it10.vuquangdung.spring.AppCustomException.ControllerException;
import edu.it10.vuquangdung.spring.AppCustomException.ErrorEnum;
import edu.it10.vuquangdung.spring.entity.AnhSanPham;
import edu.it10.vuquangdung.spring.entity.KichThuoc; 
import edu.it10.vuquangdung.spring.entity.MauSac;
import edu.it10.vuquangdung.spring.entity.SanPham;
import edu.it10.vuquangdung.spring.entity.SanPhamBienThe;
import edu.it10.vuquangdung.spring.entity.request.EditBienTheRequest;
import edu.it10.vuquangdung.spring.entity.response.ApiResponse;
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
        String fromStr = null;
        String toStr = null;
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

        Page<SanPham> data = sanPhamService.search(searchStr, kichThuocStr, mauSacStr, loaiStr, giaTienNum, 0, fromStr,
                toStr, PageRequest.of(pageNum, 10));

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
        model.addAttribute("loaiList", loaiService.getAllId());
        model.addAttribute("kichThuocList", kichThuocService.getAllId());
        model.addAttribute("mauSacList", mauSacService.getAll());
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
            model.addAttribute("sanPham", opt.get());
            model.addAttribute("bienTheList", opt.get().getBienTheList());
            model.addAttribute("uploadedImages", opt.get().getImages());
            model.addAttribute("loaiList", loaiService.getAllId());
            model.addAttribute("kichThuocList", kichThuocService.getAllId());
            model.addAttribute("mauSacList", mauSacService.getAll());
            return "admin/sanpham/edit";
        } else {
            throw new ControllerException("Không tìm thấy sản phẩm", ErrorEnum.INDEX, "/admin/sanpham/");
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addSanPham(
            @RequestParam("sanPham") String sanPhamJson, // Nhận đối tượng sanPham dưới dạng JSON
            @RequestParam("bienTheList") String bienTheListJson, // Nhận đối tượng bienTheList dưới dạng JSON
            @RequestParam List<MultipartFile> images, // Nhận các tệp hình ảnh
            @RequestParam List<String> mauSacIds // Nhận danh sách mauSacId
    ) throws IOException {
        try {
            // Chuyển đổi JSON thành đối tượng Java
            ObjectMapper objectMapper = new ObjectMapper();
            SanPham sanPham = objectMapper.readValue(sanPhamJson, SanPham.class);
            List<SanPhamBienThe> bienTheList = objectMapper.readValue(bienTheListJson,
                    new TypeReference<List<SanPhamBienThe>>() {
                    });

            // Kiểm tra số lượng biến thể
            if (bienTheList == null || bienTheList.isEmpty()) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Phải có ít nhất 1 biến thể!"));
            }

            if (images == null || images.isEmpty()) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm phải có ít nhất 1 hình ảnh!"));
            }

            // Bước 1: Lưu sản phẩm
            sanPham.setKichHoat(1); // Đảm bảo sản phẩm được kích hoạt
            sanPham = sanPhamService.save(sanPham);

            // Bước 2: Lưu danh sách biến thể
            for (SanPhamBienThe bienThe : bienTheList) {
                bienThe.setSanPham(sanPham);
                bienThe.setSoLuongDatMua(0);
                bienThe.setKichHoat(1);
                sanPhamBienTheService.save(bienThe);
            }

            // Bước 3: Lưu danh sách ảnh cho sản phẩm
            for (int i = 0; i < images.size(); i++) {
                MultipartFile imageFile = images.get(i);
                String mauSacId = mauSacIds.get(i); // Lấy mauSacId tương ứng với mỗi ảnh

                if (imageFile != null && !mauSacId.isEmpty()) {
                    AnhSanPham anhSanPham = new AnhSanPham();
                    anhSanPham.setSanPham(sanPham);
                    anhSanPham.setMauSacId(mauSacId);
                    anhSanPham.setFile(imageFile); // Lưu tệp tin ảnh

                    // Lưu ảnh vào cơ sở dữ liệu
                    anhSanPhamService.save(anhSanPham);
                } else {
                    return ResponseEntity.badRequest()
                            .body(new ApiResponse(false, "MauSacId và File không được để trống!"));
                }
            }

            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm thành công! Mã: " + sanPham.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Có lỗi xảy ra: " + e.getMessage()));
        }
    }

    @PostMapping("/editsanpham")
    @Transactional
    public ResponseEntity<ApiResponse> editSanPham(@RequestBody SanPham sanPham) {
        if (sanPham.getId() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu mã sản phẩm!"));
        } else {
            sanPhamService.save(sanPham); // Cập nhật SanPham
            return ResponseEntity.ok().body(new ApiResponse(true, "Sửa thành công!"));
        }
    }

    @PostMapping("/addbienthe")
    @Transactional
    public ResponseEntity<ApiResponse> addBienThe(
            @RequestParam Integer sanPhamId, // Nhận đối tượng sanPham dưới dạng JSON
            @RequestParam("bienTheList") String bienTheListJson, // Nhận đối tượng bienTheList dưới dạng JSON
            @RequestParam List<MultipartFile> images, // Nhận các tệp hình ảnh
            @RequestParam List<String> mauSacIds // Nhận danh sách mauSacId
    )
            throws IOException {
        // Chuyển đổi JSON thành đối tượng Java
        ObjectMapper objectMapper = new ObjectMapper();
        List<SanPhamBienThe> bienTheList = objectMapper.readValue(bienTheListJson,
                new TypeReference<List<SanPhamBienThe>>() {
                });
        // Kiểm tra số lượng biến thể
        if (bienTheList == null || bienTheList.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Phải có ít nhất 1 biến thể!"));
        }
        if (images == null || images.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm phải có ít nhất 1 hình ảnh!"));
        }

        if (sanPhamId == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu mã sản phẩm!"));
        }
        Optional<SanPham> sp_opt = sanPhamService.findById(sanPhamId);
        if (!sp_opt.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm không tồn tại!"));
        }
        for (SanPhamBienThe bienThe : bienTheList) {
            String mauSacId = bienThe.getMauSac().getMauSacId();
            if (mauSacId == null) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu mã màu sắc!"));
            }
            Optional<MauSac> ms_opt = mauSacService.findById(mauSacId);
            if (!ms_opt.isPresent()) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Màu không tồn tại!"));
            }
            String kichThuocId = bienThe.getKichThuoc().getKichThuocId();
            if (kichThuocId == null) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu kích thước!"));
            }
            Optional<KichThuoc> kt_opt = kichThuocService.findById(kichThuocId);
            if (!kt_opt.isPresent()) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Kích thước không tồn tại!"));
            }
            Optional<SanPhamBienThe> exist_opt = sanPhamBienTheService.findBySanPhamAndKichThuocAndMauSac(sp_opt.get(),
                    kt_opt.get(), ms_opt.get());

            if (exist_opt.isPresent()) {
                SanPhamBienThe exist = exist_opt.get();
                exist.setSoLuong(exist.getSoLuong() + bienThe.getSoLuong());
                if (bienThe.getGiaTien() != null)
                    exist.setGiaTien(bienThe.getGiaTien());
                exist.setSanPham(sp_opt.get());
                sanPhamBienTheService.save(exist);
                return ResponseEntity.ok().body(new ApiResponse(true, "Thêm biến thể thành công!"));
            } else {
                bienThe.setSanPham(sp_opt.get());
                bienThe.setKichHoat(1);
                bienThe.setSoLuongDatMua(0);
                bienThe = sanPhamBienTheService.save(bienThe);
            }
        }
        // Bước 3: Lưu danh sách ảnh cho sản phẩm
        for (int i = 0; i < images.size(); i++) {
            MultipartFile imageFile = images.get(i);
            String mauSacId = mauSacIds.get(i); // Lấy mauSacId tương ứng với mỗi ảnh

            if (imageFile != null && !mauSacId.isEmpty()) {
                AnhSanPham anhSanPham = new AnhSanPham();
                anhSanPham.setSanPham(sp_opt.get());
                anhSanPham.setMauSacId(mauSacId);
                anhSanPham.setFile(imageFile); // Lưu tệp tin ảnh

                // Lưu ảnh vào cơ sở dữ liệu
                anhSanPhamService.save(anhSanPham);
            } else {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "MauSacId và File không được để trống!"));
            }
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "Thêm biến thể thành công!"));
    }

    @PostMapping("/editbienthe")
    @Transactional
    public ResponseEntity<ApiResponse> editBienThe(@RequestBody EditBienTheRequest request) throws IOException {
        SanPhamBienThe bienThe = request.getBienThe();
        Integer sanPhamId = request.getSanPhamId();

        if (bienThe.getId() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu mã biến thể!"));
        }
        if (sanPhamId == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Thiếu mã sản phẩm!"));
        }
        Optional<SanPham> sp_opt = sanPhamService.findById(sanPhamId);
        if (!sp_opt.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm không tồn tại!"));
        }
        bienThe.setSanPham(sp_opt.get());
        Optional<SanPhamBienThe> opt = sanPhamBienTheService.findById(bienThe.getId());
        if (opt.isPresent()) {
            sanPhamBienTheService.save(bienThe);
            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm biến thể thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Sản phẩm không tồn tại!"));
        }
    }

    @PostMapping("/addanh")
    @Transactional
    public ResponseEntity<ApiResponse> addAnh(
        @RequestParam Integer sanPhamId, 
        @RequestParam String mauSacId, 
        @RequestParam List<MultipartFile> files
    ) throws IOException {
        Optional<SanPham> opt = sanPhamService.findById(sanPhamId);

        if (opt.isPresent()) {
            for (MultipartFile file : files) {
                AnhSanPham anh = new AnhSanPham();
                anh.setMauSacId(mauSacId);
                anh.setSanPham(opt.get());
                anh.setFile(file);
                anhSanPhamService.save(anh);
            }
            return ResponseEntity.ok().body(new ApiResponse(true, "Thêm ảnh thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Biến thể không tồn tại!"));
        }
    }

    @PostMapping("/deleteanh")
    @Transactional
    public ResponseEntity<ApiResponse> deleteAnh(@RequestParam("id") Integer anhId) throws IOException {
        Optional<AnhSanPham> opt = anhSanPhamService.findById(anhId);
        if (opt.isPresent()) {
            AnhSanPham anhSanPham = opt.get();
            Long count = anhSanPhamService.countByMauSacAndSanPham(anhSanPham.getMauSacId(), anhSanPham.getSanPham());

            if (count > 1) {
                anhSanPhamService.delete(anhId);
                return ResponseEntity.ok().body(new ApiResponse(true, "Xóa ảnh thành công!"));
            } else {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Mỗi biến thể cần ít nhất 1 ảnh!"));
            }
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Ảnh không tồn tại!"));
        }
    }

    @PostMapping("/deletebienthe")
    @Transactional
    public ResponseEntity<ApiResponse> deleteBienThe(
        @RequestParam("id") Integer bienTheId
    ) throws IOException {
        Optional<SanPhamBienThe> opt = sanPhamBienTheService.findById(bienTheId);
        
        if (opt.isPresent()) {
            SanPhamBienThe bienThe = opt.get();
            SanPham sanPham = opt.get().getSanPham();
            Long count = sanPhamBienTheService.countByMauSacAndSanPham(bienThe.getMauSac().getMauSacId(), sanPham);
            // Vi con co 1 bien the nen se xoa het anh roi xoa san pham di
            if (count == 1) {
                anhSanPhamService.deleteBySanPham(sanPham);
                sanPhamBienTheService.delete(bienTheId);
                sanPhamService.delete(sanPham.getId());
            } else {
                sanPhamBienTheService.delete(bienTheId);
            }
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
    public ResponseEntity<ApiResponse> activateSanPhamBienThe(@RequestParam("id") Integer bienTheId)
            throws IOException {
        sanPhamBienTheService.activate(bienTheId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }

    @GetMapping("/deactivatesanphambienthe")
    @Transactional
    public ResponseEntity<ApiResponse> deactivateSanPhamBienThe(@RequestParam("id") Integer bienTheId)
            throws IOException {
        sanPhamBienTheService.deactivate(bienTheId);
        return ResponseEntity.ok().body(new ApiResponse(true, "bật sản phẩm thành công!"));
    }
}
