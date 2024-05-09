package com.devstack.edu.entity;

import javax.persistence.*;
import java.util.Set;

public class Program {

    private int proId;

    private int proHours;

    private String proName;

    private double proAmout;

    private String email;

    private long trainerId;


    public Program() {
    }

    public Program(int proId, int proHours, String proName, double proAmout, String email, long trainerId) {
        this.proId = proId;
        this.proHours = proHours;
        this.proName = proName;
        this.proAmout = proAmout;
        this.email = email;
        this.trainerId = trainerId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProHours() {
        return proHours;
    }

    public void setProHours(int proHours) {
        this.proHours = proHours;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProAmout() {
        return proAmout;
    }

    public void setProAmout(double proAmout) {
        this.proAmout = proAmout;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }
}
