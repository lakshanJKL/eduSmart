package com.devstack.edu.view.tm;

import java.time.LocalDate;

public class IncomeTM {

    private LocalDate date;
    private Double amount;

    public IncomeTM() {
    }

    public IncomeTM(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
