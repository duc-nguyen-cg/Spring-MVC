package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductService implements IProductService{
    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "Double Bass", -500f, "Wooden Cart","Toscanini" ));
        products.add(new Product(2, "Violin", 30000f, "Perfect pitch", "Twoset"));
        products.add(new Product(3, "Viola", 30f, "Sacrilegious", "Roasted"));
    }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product: products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean add(Product product) {
        products.add(product);
        return true;
    }

    @Override
    public boolean update(int id, Product product) {
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getId().equals(id)){
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        for (Product product: products){
            if (product.getId().equals(id)){
                products.remove(product);
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Product> findByName(String name) {
        List<Product> found = new ArrayList<>();
        for (Product product: products){
            if (product.getName().toLowerCase().contains(name.trim().toLowerCase())){
                found.add(product);
            }
        }
        return found;
    }
}
