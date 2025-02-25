package com.REST_Project.API.Models;

import jakarta.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String hash_password;

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getHash_password(){
        return this.hash_password;
    }
    public void setHash_password(String hash_password){
        this.hash_password = hash_password;
    }
}
