package com.devstack.edu.entity;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.Set;

public class Trainer {

    private int trId;

    private String trName;

    private String trEmail;

    private String nic;

    private String trAddress;

    private boolean trState;

    private Set<Program> programs;

    public Trainer() {
    }

    public Trainer(int trId, String trName, String trEmail, String nic, String trAddress, boolean trState) {
        this.trId = trId;
        this.trName = trName;
        this.trEmail = trEmail;
        this.nic = nic;
        this.trAddress = trAddress;
        this.trState = trState;
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

    public boolean isTrState() {
        return trState;
    }

    public void setTrState(boolean trState) {
        this.trState = trState;
    }
}
