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
  public List<Trangsuc> getAllTrangsuc() {  
    return (List<Trangsuc>) trangsucRepository.findAll();  
  }  

  @Override  
  public Trangsuc saveTrangsuc(Trangsuc trangsuc) {
    Optional<Trangsuc> opt = trangsucRepository.findByTentrangsuc(trangsuc.getTentrangsuc());

    if (opt.isPresent()) {
      Trangsuc existed = opt.get();

      existed.setSoluong(existed.getSoluong() + trangsuc.getSoluong());

      return trangsucRepository.save(existed);
    } else {
      return trangsucRepository.save(trangsuc);  
    }
  }  

  @Override  
  public void deleteTrangsuc(Integer matrangsuc) {  
    trangsucRepository.deleteById(matrangsuc);  
  }  

  @Override  
  public Optional<Trangsuc> findTrangsucById(Integer matrangsuc) {  
    return trangsucRepository.findById(matrangsuc);  
  }  

  @Override
  public Page<Trangsuc> searchTrangsuc(String searchStr, String from, String to, Integer pageNum) {
    return trangsucRepository.searchTrangsuc(searchStr, from, to, PageRequest.of(pageNum, 10));
  }
}