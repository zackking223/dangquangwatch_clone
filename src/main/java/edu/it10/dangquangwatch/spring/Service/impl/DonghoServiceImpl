package io.github.tubean.myspringcrud.service.impl;  

import io.github.tubean.myspringcrud.entity.Dongho;  
import io.github.tubean.myspringcrud.repository.DonghoRepository;  
import io.github.tubean.myspringcrud.service.DonghoService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class DonghoServiceImpl implements DonghoService {  
  @Autowired private DonghoRepository donghoRepository;  

  @Override  
  public List<Dongho> getAllDongho() {  
    return (List<Dongho>) donghoRepository.findAll();  
  }  

  @Override  
  public void saveDongho(Dongho dongho) {  
    donghoRepository.save(dongho);  
  }  

  @Override  
  public void deleteDongho(Long madongho) {  
    donghoRepository.deleteById(madongho);  
  }  

  @Override  
  public Optional<Dongho> findDonghoById(Long madongho) {  
    return donghoRepository.findById(madongho);  
  }  
}