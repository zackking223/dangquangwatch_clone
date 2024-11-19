package edu.it10.dangquangwatch.spring.service.impl;  

import edu.it10.dangquangwatch.spring.entity.Trangsuc;  
import edu.it10.dangquangwatch.spring.repository.TrangsucRepository;  
import edu.it10.dangquangwatch.spring.service.TrangsucService;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class TrangsucServiceImpl implements TrangsucService {  
  @Autowired private TrangsucRepository trangsucRepository;  

  @Override  
  public List<Trangsuc> getAll() {  
    return (List<Trangsuc>) trangsucRepository.findAll();  
  }  

  @Override  
  public Trangsuc save(Trangsuc trangsuc) {
    return trangsucRepository.save(trangsuc);  
  }  

  @Override  
  public void activate(Integer matrangsuc) {  
    Optional<Trangsuc> opt = trangsucRepository.findById(matrangsuc);  

    if (opt.isPresent()) {  
      Trangsuc trangsuc = opt.get();  
      trangsuc.setKichhoat(1);  
      save(trangsuc);  
    }
  }  

  @Override  
  public void deactivate(Integer matrangsuc) {  
    Optional<Trangsuc> opt = trangsucRepository.findById(matrangsuc);  

    if (opt.isPresent()) {  
      Trangsuc trangsuc = opt.get();  
      trangsuc.setKichhoat(0);  
      save(trangsuc);  
    }
  }  

  @Override  
  public Optional<Trangsuc> findById(Integer matrangsuc) {  
    return trangsucRepository.findById(matrangsuc);  
  }  

  @Override
  public Page<Trangsuc> search(String searchStr, String from, String to, Integer pageNum) {
    return trangsucRepository.searchTrangsuc(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<Trangsuc> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return trangsucRepository.searchActiveTrangsuc(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Trangsuc> opt = trangsucRepository.findById(id);

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();
      trangsuc.setSoluong(trangsuc.getSoluong() + amount);
      save(trangsuc);
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Trangsuc> opt = trangsucRepository.findById(id);

    if (opt.isPresent()) {
      Trangsuc trangsuc = opt.get();
      trangsuc.setSoluong(trangsuc.getSoluong() - amount);
      save(trangsuc);
    }
  }
}