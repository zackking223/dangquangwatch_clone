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
  public List<Butky> getAll() {
    return (List<Butky>) butkyRepository.findAll();
  }

  @Override
  public Butky save(Butky butky) {
    return butkyRepository.save(butky);
  }

  @Override
  public void activate(Integer mabutky) {
    Optional<Butky> opt = butkyRepository.findById(mabutky);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setKichhoat(1);
      save(butky);
    }
  }

  @Override
  public void deactivate(Integer mabutky) {
    Optional<Butky> opt = butkyRepository.findById(mabutky);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setKichhoat(0);
      save(butky);
    }
  }

  @Override
  public Optional<Butky> findById(Integer mabutky) {
    return butkyRepository.findById(mabutky);
  }

  @Override
  public Page<Butky> search(String searchStr, String from, String to, Integer pageNum) {
    return butkyRepository.searchButKy(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public Page<Butky> searchAvaiable(String searchStr, String from, String to, Integer pageNum) {
    return butkyRepository.searchActiveButKy(searchStr, from, to, PageRequest.of(pageNum, 10));
  }

  @Override
  public void incAmount(Integer amount, Integer id) {
    Optional<Butky> opt = butkyRepository.findById(id);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setSoluong(butky.getSoluong() + amount);
      save(butky);
    }
  }

  @Override
  public void decAmount(Integer amount, Integer id) {
    Optional<Butky> opt = butkyRepository.findById(id);

    if (opt.isPresent()) {
      Butky butky = opt.get();
      butky.setSoluong(butky.getSoluong() - amount);
      save(butky);
    }
  }
}