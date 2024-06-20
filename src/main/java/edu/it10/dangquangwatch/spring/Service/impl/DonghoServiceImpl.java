package edu.it10.dangquangwatch.spring.service.impl;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  
import edu.it10.dangquangwatch.spring.repository.DonghoRepository;  
import edu.it10.dangquangwatch.spring.service.DonghoService;  
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
  public void deleteDongho(Integer madongho) {  
    donghoRepository.deleteById(madongho);  
  }  

  @Override  
  public Optional<Dongho> findDonghoById(Integer madongho) {  
    return donghoRepository.findById(madongho);  
  }  
}