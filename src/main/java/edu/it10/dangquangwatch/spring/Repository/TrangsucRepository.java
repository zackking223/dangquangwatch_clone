package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;
import java.util.List;
  

@Repository  
public interface TrangsucRepository extends JpaRepository<Trangsuc, Integer> {
  List<Trangsuc> findByTentrangsucContains(String tentrangsuc);
}