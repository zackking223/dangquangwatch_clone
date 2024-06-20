package edu.it10.dangquangwatch.spring.service.impl;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  
import edu.it10.dangquangwatch.spring.repository.TrangsucRepository;  
import edu.it10.dangquangwatch.spring.service.TrangsucService;  
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
  public void deleteTrangsuc(Integer matrangsuc) {  
    trangsucRepository.deleteById(matrangsuc);  
  }  

  @Override  
  public Optional<Trangsuc> findTrangsucById(Integer matrangsuc) {  
    return trangsucRepository.findById(matrangsuc);  
  }  
}