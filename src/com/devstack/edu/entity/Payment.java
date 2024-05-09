package com.devstack.edu.entity;

import java.time.LocalDate;

public class Payment {
    private long payId;
    private LocalDate date;
    private boolean isVerified;
    private double amount;
    private long registrationId;

    public Payment() {
    }

    public Payment(long payId, LocalDate date, boolean isVerified, double amount, long registrationId) {
        this.payId = payId;
        this.date = date;
        this.isVerified = isVerified;
        this.amount = amount;
        this.registrationId = registrationId;
    }
    public Payment(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }
    public long getPayId() {
        return payId;
    }

    public void setPayId(long payId) {
        this.payId = payId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(long registrationId) {
        this.registrationId = registrationId;
    }
}
