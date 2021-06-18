package com.codegym.repository;

import com.codegym.model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepository implements IBlogRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Blog> findAll() {
        String queryStr = "select b from Blog b";
        TypedQuery<Blog> query = entityManager.createQuery(queryStr, Blog.class);
        return query.getResultList();
    }


    @Override
    public Blog findById(Long id) {
        String queryStr = "select b from Blog b where b.id = :id";
        TypedQuery<Blog> query = entityManager.createQuery(queryStr, Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean save(Blog blog) {
        if (blog.getId() != null){
            entityManager.merge(blog);
        } else {
            entityManager.persist(blog);
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        Blog blog = findById(id);
        if (blog.getId() != null){
            entityManager.remove(blog);
            return true;
        }
        return false;
    }
}
