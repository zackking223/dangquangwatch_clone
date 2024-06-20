package io.github.tubean.myspringcrud.repository;  

import io.github.tubean.myspringcrud.entity.Trangsuc;  
import org.springframework.data.repository.CrudRepository;  
import org.springframework.stereotype.Repository;  

@Repository  
public interface TrangsucRepository extends CrudRepository<Trangsuc, Long> {}