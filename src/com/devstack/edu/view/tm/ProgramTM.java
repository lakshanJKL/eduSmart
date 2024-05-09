package com.devstack.edu.view.tm;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

public class ProgramTM {

   private long programId;
   private long trainerId;
   private String program;
   private int hours;
   private double amount;
   private ButtonBar operations;

    public ProgramTM() {
    }

    public ProgramTM(long programId, long trainerId, String program, int hours, double amount, ButtonBar operations) {
        this.programId = programId;
        this.trainerId = trainerId;
        this.program = program;
        this.hours = hours;
        this.amount = amount;
        this.operations = operations;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ButtonBar getOperations() {
        return operations;
    }

    public void setOperations(ButtonBar operations) {
        this.operations = operations;
    }
}
