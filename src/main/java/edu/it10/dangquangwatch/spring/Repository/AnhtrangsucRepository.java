package edu.it10.dangquangwatch.spring.repository;  

import edu.it10.dangquangwatch.spring.entity.Anhtrangsuc;  
import org.springframework.data.repository.CrudRepository;  
import org.springframework.stereotype.Repository;  

@Repository  
public interface AnhtrangsucRepository extends CrudRepository<Anhtrangsuc, Integer> {}