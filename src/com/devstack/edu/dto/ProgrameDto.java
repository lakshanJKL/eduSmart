package com.devstack.edu.dto;

import java.util.List;

public class ProgrameDto {
    private int proId;
    private int proHours;
    private String proName;
    private double proAmout;
    private long trainerId;
    private List<String> contents;

    public ProgrameDto() {
    }

    public ProgrameDto(int proId, int proHours, String proName, double proAmout, long trainerId, List<String> contents) {
        this.proId = proId;
        this.proHours = proHours;
        this.proName = proName;
        this.proAmout = proAmout;
        this.trainerId = trainerId;
        this.contents = contents;
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

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
}
