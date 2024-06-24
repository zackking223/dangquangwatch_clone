package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Butky;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
  

@Repository  
public interface ButkyRepository extends JpaRepository<Butky, Integer> {
  Page<Butky> findByTenbutkyContains(String tenbutky, Pageable pageable);
}