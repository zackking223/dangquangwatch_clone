package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Anhdongho;
import edu.it10.dangquangwatch.spring.entity.Dongho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository  
public interface AnhdonghoRepository extends JpaRepository<Anhdongho, Integer> {
  List<Anhdongho> findByDongho(Dongho dongho);
}