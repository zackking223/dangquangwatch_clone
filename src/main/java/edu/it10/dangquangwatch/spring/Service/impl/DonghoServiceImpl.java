package edu.it10.dangquangwatch.spring.service.impl;  

import edu.it10.dangquangwatch.spring.entity.Dongho;  
import edu.it10.dangquangwatch.spring.repository.DonghoRepository;  
import edu.it10.dangquangwatch.spring.service.DonghoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class DonghoServiceImpl implements DonghoService {  
  @Autowired private DonghoRepository donghoRepository;  

  @Override  
  public List<Dongho> getAllDongho() {  
    return donghoRepository.findAll();  
  }  

  @Override  
  public Dongho saveDongho(Dongho dongho) {
    Optional<Dongho> opt = donghoRepository.findByTendongho(dongho.getTendongho());

    if (opt.isPresent()) {
      Dongho existed = opt.get();
      existed.setSoluong(existed.getSoluong() + dongho.getSoluong());

      return donghoRepository.save(existed);
    } else {
      return donghoRepository.save(dongho);  
    }
    
  }  

  @Override  
  public void deleteDongho(Integer madongho) {  
    donghoRepository.deleteById(madongho);  
  }  

  @Override  
  public Optional<Dongho> findDonghoById(Integer madongho) {  
    return donghoRepository.findById(madongho);  
  }

  @Override
  public Page<Dongho> getAllDonghoByTendongho(String tendongho, String from, String to, int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    return donghoRepository.findByTendonghoContains(tendongho, from, to, pageable);
  }

  @Override
  public Page<Dongho> searchDongho(String searchStr, Dongho searchData, String from, String to, int pageNum) {
    return donghoRepository.searchDongHo(searchStr, searchData, from, to, PageRequest.of(pageNum, 10));
  }  
}