package edu.it10.dangquangwatch.spring.service.taikhoan.impl;

import edu.it10.dangquangwatch.spring.AppCustomException.ErrorEnum;
import edu.it10.dangquangwatch.spring.AppCustomException.SaveAccountException;
import edu.it10.dangquangwatch.spring.AppCustomException.ServiceException;
import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.helper.DateStringHelper;
import edu.it10.dangquangwatch.spring.repository.TaiKhoanRepository;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanManagerImpl implements TaiKhoanManager {
    private final TaiKhoanRepository taiKhoanRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public TaiKhoanManagerImpl(
            TaiKhoanRepository taiKhoanRepository,
            EntityManager entityManager) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.entityManager = entityManager;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    private static String removeWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", "");
    }

    private boolean accountExisted(String username) {
        return taiKhoanRepository.findById(username).isPresent();
    }
    private boolean numberExisted(String sodienthoai) {
        return !taiKhoanRepository.findBySodienthoai(sodienthoai).isEmpty();
    }


    @Override
    @Transactional
    public void dangKyKhachHang(TaiKhoan taiKhoan, String path) {
        String plainText = taiKhoan.getPassword();
        taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
        taiKhoan.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());
        taiKhoan.setLoai_tai_khoan("ROLE_KHACHHANG");
        taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

        if (accountExisted(taiKhoan.getUsername())) {
            String message = "Email đã tồn tại!";
            SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
            exception.setPath(path);
            exception.setTaiKhoan(taiKhoan);
            throw exception;
        }

        if (taiKhoan.getSodienthoai() != null) {
            if (numberExisted(taiKhoan.getSodienthoai())) {
                String message = "Số điện thoại đã tồn tại!";
                SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
                exception.setPath(path);
                exception.setTaiKhoan(taiKhoan);
                throw exception;
            }
        }

        entityManager.persist(taiKhoan);
    }

    @Override
    @Transactional
    public void dangKyQuanTri(TaiKhoan taiKhoan) {
        String plainText = taiKhoan.getPassword();
        taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));
        taiKhoan.setNGAYTHEM(DateStringHelper.getCurrentDateFormatted());
        taiKhoan.setLoai_tai_khoan("ROLE_QUANTRI");
        taiKhoan.setDiachi("QUANTRI ko can dia chi");
        taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));

        if (accountExisted(taiKhoan.getUsername())) {
            String message = "Email đã tồn tại!";
            SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
            exception.setPath("/admin/accounts/add");
            exception.setTaiKhoan(taiKhoan);
            throw exception;
        }

        if (taiKhoan.getSodienthoai() != null) {
            if (numberExisted(taiKhoan.getSodienthoai())) {
                String message = "Số điện thoại đã tồn tại!";
                SaveAccountException exception = new SaveAccountException(message, ErrorEnum.REGISTER_ERROR);
                exception.setPath("/admin/accounts/add");
                exception.setTaiKhoan(taiKhoan);
                throw exception;
            }
        }
        entityManager.persist(taiKhoan);
    }

    @Override
    @Transactional
    public void updateTaiKhoan(TaiKhoan taiKhoan, String path) {
        String plainText = taiKhoan.getPassword();
        TaiKhoan original = taiKhoanRepository.findById(taiKhoan.getUsername())
                .orElseThrow(() -> new RuntimeException("Không thể tìm thấy người dùng với email: " + taiKhoan.getHoten()));

        taiKhoan.setUsername(removeWhitespace(taiKhoan.getUsername()));

        if (plainText != null && !plainText.contains("{bcrypt}")) {
            taiKhoan.setPassword("{bcrypt}" + passwordEncoder.encode(plainText));
        } else if (plainText == null) {
            taiKhoan.setPassword(original.getPassword());
        }

        if (!taiKhoan.getSodienthoai().equals(original.getSodienthoai())) {
            if (numberExisted(taiKhoan.getSodienthoai())) {
                String message = "Số điện thoại đã tồn tại!";
                SaveAccountException exception = new SaveAccountException(message, ErrorEnum.UPDATE_PROFILE_ERROR);
                exception.setPath(path);
                exception.setTaiKhoan(taiKhoan);
                throw exception;
            }
        }
        entityManager.merge(taiKhoan);
    }

    @Override
    @Transactional
    public void deleteById(String username) {
        TaiKhoan target = entityManager.find(TaiKhoan.class, username);
        entityManager.remove(target);
    }

    @Override
    public TaiKhoan getTaiKhoan(String username) {
        TaiKhoan taiKhoan = entityManager.find(TaiKhoan.class, username);
        if (taiKhoan == null) {
            throw new ServiceException("Không thể tìm thấy người dùng với email: " + username);
        }
        return taiKhoan;
    }

    @Override
    @Transactional
    public void activate(String username) {
        TaiKhoan target = entityManager.find(TaiKhoan.class, username);
        target.setEnabled(1);
        entityManager.merge(target);
    }

    @Override
    @Transactional
    public void deactivate(String username) {
        TaiKhoan target = entityManager.find(TaiKhoan.class, username);
        target.setEnabled(0);
        entityManager.merge(target);
    }
}
