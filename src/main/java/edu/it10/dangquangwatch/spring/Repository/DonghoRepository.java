package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.DonHang;
import edu.it10.dangquangwatch.spring.entity.Dongho;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;  
import org.springframework.stereotype.Repository;
import java.util.List;
  

@Repository  
public interface DonghoRepository extends CrudRepository<Dongho, Integer> {
  List<Dongho> findByTendonghoContains(String tendongho);
}