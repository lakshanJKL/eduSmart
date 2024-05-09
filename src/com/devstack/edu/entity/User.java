package com.devstack.edu.entity;

import javax.persistence.*;
import java.util.Set;



public class User {

    private String email;

    private String fristName;

    private String lastName;

    private String password;

    private boolean state;


    public User() {
    }
    public User(String email, String fristName, String lastName, String password, boolean state) {
        this.email = email;
        this.fristName = fristName;
        this.lastName = lastName;
        this.password = password;
        this.state = state;
    }
    public User(String email, String password, String lastName) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
