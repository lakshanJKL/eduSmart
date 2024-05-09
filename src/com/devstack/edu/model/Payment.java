package com.devstack.edu.model;

import java.time.LocalDate;

public class Payment {
    private long pay_id;
    private LocalDate date;
    private boolean isverified;
    private double amount;
    private long registration_id;

    public Payment() {
    }

    public Payment(long pay_id, LocalDate date, boolean isverified, double amount, long registration_id) {
        this.pay_id = pay_id;
        this.date = date;
        this.isverified = isverified;
        this.amount = amount;
        this.registration_id = registration_id;
    }

    public long getPay_id() {
        return pay_id;
    }

    public void setPay_id(long pay_id) {
        this.pay_id = pay_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isIsverified() {
        return isverified;
    }

    public void setIsverified(boolean isverified) {
        this.isverified = isverified;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(long registration_id) {
        this.registration_id = registration_id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "pay_id=" + pay_id +
                ", date=" + date +
                ", isverified=" + isverified +
                ", amount=" + amount +
                ", registration_id=" + registration_id +
                '}';
    }
}
