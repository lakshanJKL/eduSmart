package com.devstack.edu.view.tm;

import javafx.scene.control.ButtonBar;

import java.sql.Date;

public class TrainerTM {
    private int trId;
    private String trName;
    private String trEmail;
    private String nic;
    private String trAddress;
    private boolean trStatus;
    private ButtonBar btnBars;

    public TrainerTM() {
    }

    public TrainerTM(int trId, String trName, String trEmail, String nic, String trAddress, boolean trStatus, ButtonBar btnBars) {
        this.trId = trId;
        this.trName = trName;
        this.trEmail = trEmail;
        this.nic = nic;
        this.trAddress = trAddress;
        this.trStatus = trStatus;
        this.btnBars = btnBars;
    }

    public int getTrId() {
        return trId;
    }

    public void setTrId(int trId) {
        this.trId = trId;
    }

    public String getTrName() {
        return trName;
    }

    public void setTrName(String trName) {
        this.trName = trName;
    }

    public String getTrEmail() {
        return trEmail;
    }

    public void setTrEmail(String trEmail) {
        this.trEmail = trEmail;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTrAddress() {
        return trAddress;
    }

    public void setTrAddress(String trAddress) {
        this.trAddress = trAddress;
    }

    public boolean isTrStatus() {
        return trStatus;
    }

    public void setTrStatus(boolean trStatus) {
        this.trStatus = trStatus;
    }

    public ButtonBar getBtnBars() {
        return btnBars;
    }

    public void setBtnBars(ButtonBar btnBars) {
        this.btnBars = btnBars;
    }
}
