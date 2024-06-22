package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Dongho;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;
import java.util.List;
  

@Repository
public interface DonghoRepository extends JpaRepository<Dongho, Integer> {
  List<Dongho> findByTendonghoContains(String tendongho,Pageable pageable);
}