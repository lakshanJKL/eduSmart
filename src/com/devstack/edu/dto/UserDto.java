package com.devstack.edu.dto;

public class UserDto {
    private String email;
    private String password;
    private String lastName;

    public UserDto() {
    }

    public UserDto(String email, String password, String lastName) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
