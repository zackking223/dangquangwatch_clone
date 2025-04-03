package edu.it10.vuquangdung.spring.repository;  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.it10.vuquangdung.spring.entity.ThongKe;
  

@Repository  
public interface ThongKeRepository extends JpaRepository<ThongKe, String> {
    @Query("SELECT tk FROM ThongKe tk where tk.NGAYTHEM >= :tu and tk.NGAYTHEM <= :den")
    Page<ThongKe> searchThongKe(@Param("tu") String tu, @Param("den") String den, Pageable pageable);

}