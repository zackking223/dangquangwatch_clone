package edu.it10.dangquangwatch.spring.service.impl;  

import edu.it10.dangquangwatch.spring.entity.Butky;  
import edu.it10.dangquangwatch.spring.repository.ButkyRepository;  
import edu.it10.dangquangwatch.spring.service.ButkyService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class ButkyServiceImpl implements ButkyService {  
  @Autowired private ButkyRepository butkyRepository;  

  @Override  
  public List<Butky> getAllButky() {  
    return (List<Butky>) butkyRepository.findAll();  
  }  

  @Override  
  public void saveButky(Butky butky) {  
    butkyRepository.save(butky);  
  }  

  @Override  
  public void deleteButky(Integer mabutky) {  
    butkyRepository.deleteById(mabutky);  
  }  

  @Override  
  public Optional<Butky> findButkyById(Integer mabutky) {  
    return butkyRepository.findById(mabutky);  
  }  
}