package io.github.tubean.myspringcrud.repository;  

import io.github.tubean.myspringcrud.entity.Anhtrangsuc;  
import org.springframework.data.repository.CrudRepository;  
import org.springframework.stereotype.Repository;  

@Repository  
public interface AnhtrangsucRepository extends CrudRepository<Anhtrangsuc, Long> {}