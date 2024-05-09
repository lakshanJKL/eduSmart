package com.devstack.edu.view.tm;

import javafx.scene.control.ButtonBar;

import java.util.Date;

public class RegistrationTM {

    private long regId;
    private String programName;
    private String stdntName;
    private Date regDate;
    private ButtonBar regOps;

    public RegistrationTM() {
    }

    public RegistrationTM(long regId, String programName, String stdntName, Date regDate, ButtonBar regOps) {
        this.regId = regId;
        this.programName = programName;
        this.stdntName = stdntName;
        this.regDate = regDate;
        this.regOps = regOps;
    }

    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getStdntName() {
        return stdntName;
    }

    public void setStdntName(String stdntName) {
        this.stdntName = stdntName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public ButtonBar getRegOps() {
        return regOps;
    }

    public void setRegOps(ButtonBar regOps) {
        this.regOps = regOps;
    }
}
