package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String author;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {
    }

    public Blog(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Blog(String author, String content, Category category) {
        this.author = author;
        this.content = content;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }
}
