package edu.it10.dangquangwatch.spring.service.impl;

import edu.it10.dangquangwatch.spring.entity.Butky;
import edu.it10.dangquangwatch.spring.repository.ButkyRepository;
import edu.it10.dangquangwatch.spring.service.ButkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ButkyServiceImpl implements ButkyService {
  @Autowired
  private ButkyRepository butkyRepository;

  @Override
  public List<Butky> getAllButky() {
    return (List<Butky>) butkyRepository.findAll();
  }

  @Override
  public Butky saveButky(Butky butky) {
    Optional<Butky> opt = butkyRepository.findByTenbutky(butky.getTenbutky());

    if (opt.isPresent()) {
      Butky existed = opt.get();
      existed.setSoluong(existed.getSoluong() + butky.getSoluong());

      return butkyRepository.save(existed);
    } else {
      return butkyRepository.save(butky);
    }
  }

  @Override
  public void deleteButky(Integer mabutky) {
    butkyRepository.deleteById(mabutky);
  }

  @Override
  public Optional<Butky> findButkyById(Integer mabutky) {
    return butkyRepository.findById(mabutky);
  }

  @Override
  public Page<Butky> searchButky(String searchStr, String from, String to, Integer pageNum) {
    return butkyRepository.searchButKy(searchStr, from, to, PageRequest.of(pageNum, 10));
  }
}