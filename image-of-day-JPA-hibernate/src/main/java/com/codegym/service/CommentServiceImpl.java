package com.codegym.service;

import com.codegym.model.Comment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class CommentServiceImpl implements ICommentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAllWithinDay() {
        String queryStr = "SELECT c FROM Comment AS c WHERE c.timestamp >= current_date";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment findById(Long id) {
        String queryStr = "SELECT c FROM Comment AS c WHERE c.id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null){
            entityManager.merge(comment);
        } else {
            entityManager.persist(comment);
        }
        return comment;
    }
}
