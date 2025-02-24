package com.REST_Project.API.Models;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)

    private String author;

    @Column
    private String genre;

    @Column(nullable = false)
    private BigDecimal price;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title =  title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author =  author;
    }


    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre =  genre;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(BigDecimal price){
        this.price =  price;
    }
}
