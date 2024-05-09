package com.devstack.edu.dto;

import java.time.LocalDate;

public class StudentDto {
    private int stid;
    private String stName;
    private String stEmail;
    private LocalDate stDob;
    private String stAddress;
    private boolean status;

    public StudentDto() {
    }

    public StudentDto(int stid, String stName, String stEmail, LocalDate stDob, String stAddress, boolean status) {
        this.stid = stid;
        this.stName = stName;
        this.stEmail = stEmail;
        this.stDob = stDob;
        this.stAddress = stAddress;
        this.status = status;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public LocalDate getStDob() {
        return stDob;
    }

    public void setStDob(LocalDate stDob) {
        this.stDob = stDob;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
