package io.github.tubean.myspringcrud.service.impl;  

import io.github.tubean.myspringcrud.entity.Trangsuc;  
import io.github.tubean.myspringcrud.repository.TrangsucRepository;  
import io.github.tubean.myspringcrud.service.TrangsucService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class TrangsucServiceImpl implements TrangsucService {  
  @Autowired private TrangsucRepository trangsucRepository;  

  @Override  
  public List<Trangsuc> getAllTrangsuc() {  
    return (List<Trangsuc>) trangsucRepository.findAll();  
  }  

  @Override  
  public void saveTrangsuc(Trangsuc trangsuc) {  
    trangsucRepository.save(trangsuc);  
  }  

  @Override  
  public void deleteTrangsuc(Long matrangsuc) {  
    trangsucRepository.deleteById(matrangsuc);  
  }  

  @Override  
  public Optional<Trangsuc> findTrangsucById(Long matrangsuc) {  
    return trangsucRepository.findById(matrangsuc);  
  }  
}