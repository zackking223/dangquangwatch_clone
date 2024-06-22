package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Butky;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
  

@Repository  
public interface ButkyRepository extends JpaRepository<Butky, Integer> {
  List<Butky> findByTenbutkyContains(String tenbutky);
}