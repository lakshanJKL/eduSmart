package com.devstack.edu.model;

public class User {
    private String frist_name;
    private String last_name;
    private String emai;
    private String password;

    public User() {
    }

    public User(String frist_name, String last_name, String emai, String password) {
        this.frist_name = frist_name;
        this.last_name = last_name;
        this.emai = emai;
        this.password = password;
    }

    public String getFrist_name() {
        return frist_name;
    }

    public void setFrist_name(String frist_name) {
        this.frist_name = frist_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "frist_name='" + frist_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", emai='" + emai + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
