package com.devstack.edu.view.tm;

import javafx.scene.control.ButtonBar;

import java.sql.Date;

public class IntakeTM {
    private long intakId;
    private String programName;
    private String intakName;
    private Date startDate;
    private ButtonBar intakOps;

    public IntakeTM() {
    }

    public IntakeTM(long intakId, String programName, String intakName, Date startDate, ButtonBar intakOps) {
        this.intakId = intakId;
        this.programName = programName;
        this.intakName = intakName;
        this.startDate = startDate;
        this.intakOps = intakOps;
    }

    public long getIntakId() {
        return intakId;
    }

    public void setIntakId(long intakId) {
        this.intakId = intakId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getIntakName() {
        return intakName;
    }

    public void setIntakName(String intakName) {
        this.intakName = intakName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ButtonBar getIntakOps() {
        return intakOps;
    }

    public void setIntakOps(ButtonBar intakOps) {
        this.intakOps = intakOps;
    }
}
