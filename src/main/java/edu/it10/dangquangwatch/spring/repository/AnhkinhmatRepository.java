package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Anhkinhmat;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;  

@Repository  
public interface AnhkinhmatRepository extends JpaRepository<Anhkinhmat, Integer> {}