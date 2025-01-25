package edu.it10.dangquangwatch.spring.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.it10.dangquangwatch.spring.entity.Dongho;

public interface DonghoRepositoryCustom {
  Page<Dongho> searchDongHo(String searchValues, Dongho searchData, String from, String to, Pageable pageable);
}
