package com.codegym.model;

public class Product {
    private Integer id;
    private String name;
    private Float price;
    private String description;
    private String producer;

    public Product() {
    }

    public Product(Integer id, String name, Float price, String description, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.producer = producer;
    }

//    public Product(String name, Float price, String description, String producer) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.producer = producer;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
