package edu.it10.dangquangwatch.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
  // Tìm kiếm Otp bằng email và password
    Optional<Otp> findByEmailAndPassword(String email, String password);
}
