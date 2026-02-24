package com.example.santa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    @Column (name = "password_hash", nullable = false)
    private String password;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    public Account(){}

    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }


}
