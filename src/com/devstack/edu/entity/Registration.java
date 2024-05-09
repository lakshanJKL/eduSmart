package com.devstack.edu.entity;

import java.time.LocalDate;

public class Registration {
    private long regId;
    private LocalDate regDate;
    private double proAmount;
    private String proName;
    private String studentName;
    private long intkId;
    private long stdntId;

    public Registration() {
    }

    public Registration(long regId, LocalDate regDate, double proAmount, long intkId, long stdntId) {
        this.regId = regId;
        this.regDate = regDate;
        this.proAmount = proAmount;
        this.intkId = intkId;
        this.stdntId = stdntId;
    }

    public Registration(long regId, LocalDate regDate, String proName, String studentName) {
        this.regId = regId;
        this.regDate = regDate;
        this.setProName(proName);
        this.setStudentName(studentName);
    }
    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public double getProAmount() {
        return proAmount;
    }

    public void setProAmount(double proAmount) {
        this.proAmount = proAmount;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getIntkId() {
        return intkId;
    }

    public void setIntkId(long intkId) {
        this.intkId = intkId;
    }

    public long getStdntId() {
        return stdntId;
    }

    public void setStdntId(long stdntId) {
        this.stdntId = stdntId;
    }
}
