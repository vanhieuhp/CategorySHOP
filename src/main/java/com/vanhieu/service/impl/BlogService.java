package com.vanhieu.service.impl;

import com.vanhieu.converter.BlogConverter;
import com.vanhieu.dto.BlogDto;
import com.vanhieu.entity.BlogEntity;
import com.vanhieu.repository.BlogRepository;
import com.vanhieu.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogConverter blogConverter;

    @Override
    public int count() {
        return (int) blogRepository.count();
    }

    @Override
    public List<BlogDto> findAll(int status) {
        List<BlogEntity> entities = blogRepository.findAll(status);
        List<BlogDto> results = new ArrayList<>();
        for (BlogEntity entity : entities) {
            results.add(blogConverter.toDto(entity));
        }
        return results;
    }

    @Override
    public List<BlogDto> findAllByPageable(Pageable pageable, int status) {
        List<BlogEntity> entities = blogRepository.findAllByPageable(pageable, status);
        List<BlogDto> results = new ArrayList<>();
        for (BlogEntity entity : entities) {
            results.add(blogConverter.toDto(entity));
        }
        return results;
    }

    @Override
    @Transactional
    public BlogDto save(BlogDto blogDto) {
        BlogEntity entity = null;
        if (blogDto.getId() != null) {
            entity = blogRepository.getById(blogDto.getId());
            entity = blogConverter.toEntity(entity, blogDto);
        } else {
            entity = blogConverter.toEntity(blogDto);
            entity.setStatus(1);
        }
        entity = blogRepository.save(entity);
        return blogConverter.toDto(entity);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            BlogEntity entity = blogRepository.getOne(id);
            entity.setStatus(0);
            blogRepository.save(entity);
        }
    }

    @Override
    public BlogDto getOne(Long id) {
        return blogConverter.toDto(blogRepository.getOne(id));
    }

    @Override
    public List<BlogDto> getRecentBlogs(int amount) {
        List<BlogEntity> blogs = blogRepository.findAll(1);
        List<BlogDto> results = new ArrayList<>();
        Collections.reverse(blogs);
        for (int i = 0; i < amount; i++) {
            results.add(blogConverter.toDto(blogs.get(i)));
        }
        return results;
    }
}
