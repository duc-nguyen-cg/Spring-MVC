package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog> {

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByContentContaining(String content, Pageable pageable);

    Page<Blog> findAllByCategory(Category category, Pageable pageable);

    Page<Blog> findAllByAuthorContainingAndCategory(String author, Category category, Pageable pageable);
}
