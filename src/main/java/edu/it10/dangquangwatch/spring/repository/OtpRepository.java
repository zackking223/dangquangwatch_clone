package edu.it10.dangquangwatch.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.it10.dangquangwatch.spring.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
  Optional<Otp> findByEmailAndPasswordAndAction(String email, String password, String action);

  Optional<Otp> findByEmailAndPasswordAndActionAndPayload(String email, String password, String action, String payload);
}
