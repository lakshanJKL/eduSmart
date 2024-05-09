package com.devstack.edu.view.tm;

import javafx.scene.control.ButtonBar;

import java.sql.Date;
import java.time.LocalDate;

public class StudentTM {
   private int stId;
   private String stName;
   private String stEmail;
   private Date stDob;
   private String stAddress;
   private boolean stStatus;
   private ButtonBar btnBar;

    public StudentTM() {
    }

    public StudentTM(int stId, String stName, String stEmail, Date stDob, String stAddress, boolean stStatus, ButtonBar btnBar) {
        this.stId = stId;
        this.stName = stName;
        this.stEmail = stEmail;
        this.stDob = stDob;
        this.stAddress = stAddress;
        this.stStatus = stStatus;
        this.btnBar = btnBar;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public Date getStDob() {
        return stDob;
    }

    public void setStDob(Date stDob) {
        this.stDob = stDob;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public boolean isStStatus() {
        return stStatus;
    }

    public void setStStatus(boolean stStatus) {
        this.stStatus = stStatus;
    }

    public ButtonBar getBtnBar() {
        return btnBar;
    }

    public void setBtnBar(ButtonBar btnBar) {
        this.btnBar = btnBar;
    }
}
