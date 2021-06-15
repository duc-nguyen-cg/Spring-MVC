package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(int id);

    boolean add(Product product);

    boolean update(int id, Product product);

    boolean remove(int id);

    List<Product> findByName(String name);
}
