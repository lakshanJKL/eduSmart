package com.devstack.edu.dto;

public class TrainerDto {
    private int trId;
    private String trName;
    private String trEmail;
    private String trAddress;
    private String nic;
    private boolean trState;

    public TrainerDto() {
    }

    public TrainerDto(int trId, String trName, String trEmail, String trAddress, String nic, boolean trState) {
        this.trId = trId;
        this.trName = trName;
        this.trEmail = trEmail;
        this.trAddress = trAddress;
        this.nic = nic;
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

    public String getTrAddress() {
        return trAddress;
    }

    public void setTrAddress(String trAddress) {
        this.trAddress = trAddress;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isTrState() {
        return trState;
    }

    public void setTrState(boolean trState) {
        this.trState = trState;
    }
}
