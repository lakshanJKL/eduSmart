package com.devstack.edu.model;

import java.util.List;

public class Program {

    private int pro_id;
    private int pro_hours;
    private String pro_name;
    private double pro_amout;
    private long trainer_id;
    private List<String> contents;

    public Program() {
    }

    public Program(int pro_id, int pro_hours, String pro_name, double pro_amout, long trainer_id, List<String> contents) {
        this.pro_id = pro_id;
        this.pro_hours = pro_hours;
        this.pro_name = pro_name;
        this.pro_amout = pro_amout;
        this.trainer_id = trainer_id;
        this.contents = contents;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getPro_hours() {
        return pro_hours;
    }

    public void setPro_hours(int pro_hours) {
        this.pro_hours = pro_hours;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public double getPro_amout() {
        return pro_amout;
    }

    public void setPro_amout(double pro_amout) {
        this.pro_amout = pro_amout;
    }

    public long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(long trainer_id) {
        this.trainer_id = trainer_id;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
}
