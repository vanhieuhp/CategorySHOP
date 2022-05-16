package com.vanhieu.service;

import com.vanhieu.dto.BlogDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {

    int count();
    List<BlogDto> findAll(int status);
    List<BlogDto> findAllByPageable(Pageable pageable, int status);
    BlogDto save(BlogDto blogDto);
    void delete(Long[] ids);
    BlogDto getOne(Long id);
    List<BlogDto> getRecentBlogs(int amount);
}
