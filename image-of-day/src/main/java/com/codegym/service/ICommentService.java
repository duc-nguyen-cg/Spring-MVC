package com.codegym.service;

import com.codegym.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAllWithinDay();

    Comment findById(Long id);

    Comment save(Comment comment);

}