package com.morris;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name="User")
public class User {
    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isManager;

    // insert data through the constructor
    public User(int id, String username, String email, String password, boolean isManager) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }
    public User(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean getManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
