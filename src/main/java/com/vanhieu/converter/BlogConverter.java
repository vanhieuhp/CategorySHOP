package com.vanhieu.converter;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.entity.BlogEntity;
import com.vanhieu.entity.CategoryEntity;
import com.vanhieu.entity.UserEntity;
import com.vanhieu.repository.CategoryRepository;
import com.vanhieu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    ModelMapper mapper = new ModelMapper();

    public BlogDto toDto(BlogEntity blogEntity) {
        BlogDto blogDto = mapper.map(blogEntity, BlogDto.class);
        UserEntity user = blogEntity.getAuthorBlog();
        CategoryEntity category = blogEntity.getBlogCategory();
        blogDto.setAuthor(userConverter.toDto(user));
        blogDto.setAuthorId(user.getId());
        blogDto.setCategory(categoryConverter.toDto(category));
        blogDto.setCategoryId(category.getId());
        return blogDto;
    }

    public BlogEntity toEntity(BlogDto blogDto) {
        BlogEntity entity = mapper.map(blogDto, BlogEntity.class);
        entity = mapper.map(blogDto, BlogEntity.class);
        entity.setAuthorBlog(userRepository.getOne(blogDto.getAuthorId()));
        entity.setBlogCategory(categoryRepository.getOne(blogDto.getCategoryId()));
        return entity;
    }

    public BlogEntity toEntity(BlogEntity blogEntity, BlogDto blogDto) {
        blogEntity.setTitle(blogDto.getTitle());
        blogEntity.setShortDescription(blogDto.getShortDescription());
        blogEntity.setContent(blogDto.getContent());
        blogEntity.setImage(blogDto.getImage());
        blogEntity.setBlogCategory(categoryRepository.getOne(blogDto.getCategoryId()));

        return blogEntity;
    }
}
