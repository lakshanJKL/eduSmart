package com.devstack.edu.entity;

import javax.persistence.*;
import java.time.LocalDate;


public class Intake {

    private long intkID;

    private String intkName;

    private LocalDate intkStdate;

    private long programId;

    public Intake() {
    }

    public Intake(long intkID, String intkName, LocalDate intkStdate, long programId) {
        this.intkID = intkID;
        this.intkName = intkName;
        this.intkStdate = intkStdate;
        this.programId = programId;
    }

    public long getIntkID() {
        return intkID;
    }

    public void setIntkID(long intkID) {
        this.intkID = intkID;
    }

    public String getIntkName() {
        return intkName;
    }

    public void setIntkName(String intkName) {
        this.intkName = intkName;
    }

    public LocalDate getIntkStdate() {
        return intkStdate;
    }

    public void setIntkStdate(LocalDate intkStdate) {
        this.intkStdate = intkStdate;
    }

    public long getprogramId() {
        return programId;
    }

    public void setProgram(long programId) {
        this.programId = programId;
    }
}
