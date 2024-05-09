package com.devstack.edu.model;

import java.time.LocalDate;

public class Registration {
    private long reg_id;
    private LocalDate reg_date;
    private double pro_amount;
    private long intk_id;
    private long stdnt_id;

    public Registration() {
    }

    public Registration(long reg_id, LocalDate reg_date, double pro_amount, long intk_id, long stdnt_id) {
        this.reg_id = reg_id;
        this.reg_date = reg_date;
        this.pro_amount = pro_amount;
        this.intk_id = intk_id;
        this.stdnt_id = stdnt_id;
    }

    public long getReg_id() {
        return reg_id;
    }

    public void setReg_id(long reg_id) {
        this.reg_id = reg_id;
    }

    public LocalDate getReg_date() {
        return reg_date;
    }

    public void setReg_date(LocalDate reg_date) {
        this.reg_date = reg_date;
    }

    public double getPro_amount() {
        return pro_amount;
    }

    public void setPro_amount(double pro_amount) {
        this.pro_amount = pro_amount;
    }

    public long getIntk_id() {
        return intk_id;
    }

    public void setIntk_id(long intk_id) {
        this.intk_id = intk_id;
    }

    public long getStdnt_id() {
        return stdnt_id;
    }

    public void setStdnt_id(long stdnt_id) {
        this.stdnt_id = stdnt_id;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "reg_id=" + reg_id +
                ", reg_date=" + reg_date +
                ", pro_amount=" + pro_amount +
                ", intk_id=" + intk_id +
                ", stdnt_id=" + stdnt_id +
                '}';
    }
}
