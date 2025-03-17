package com.atoz.e_commerce.Dao;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID
    private Long id;

    private String name;
    private String email;

    public UserDao() {
    }

    public UserDao(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters

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
}
