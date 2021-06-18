package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rate;
    private String author;
    private String feedback;
    @Column(name = "likes", nullable = false, columnDefinition = "integer default '0'")
    private int like;

    @Column(name = "startTime", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date timestamp = new Date();

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Comment(int rate, String author, String feedback) {
        this.rate = rate;
        this.author = author;
        this.feedback = feedback;
    }

    public Comment() {
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public Comment clone() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setAuthor(author);
        comment.setFeedback(feedback);
        comment.setRate(rate);
        return comment;
    }
}
