package com.devstack.edu.model;

import java.time.LocalDate;
import java.util.Date;

public class Trainer {
private int tr_id;
private String tr_name;
private String tr_email;
private String tr_address;
private String nic;
private boolean tr_state;

    public Trainer() {
    }

    public Trainer(int tr_id, String tr_name, String tr_email, String tr_address, String nic, boolean tr_state) {
        this.tr_id = tr_id;
        this.tr_name = tr_name;
        this.tr_email = tr_email;
        this.tr_address = tr_address;
        this.nic = nic;
        this.tr_state = tr_state;
    }

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    public String getTr_name() {
        return tr_name;
    }

    public void setTr_name(String tr_name) {
        this.tr_name = tr_name;
    }

    public String getTr_email() {
        return tr_email;
    }

    public void setTr_email(String tr_email) {
        this.tr_email = tr_email;
    }

    public String getTr_address() {
        return tr_address;
    }

    public void setTr_address(String tr_address) {
        this.tr_address = tr_address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isTr_state() {
        return tr_state;
    }

    public void setTr_state(boolean tr_state) {
        this.tr_state = tr_state;
    }
}
