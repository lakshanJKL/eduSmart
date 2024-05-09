package com.devstack.edu.model;

import java.time.LocalDate;

public class Intake {
    private long intk_id;
    private String intk_name;
    private LocalDate intk_stdate;
    private int program_id;

    public Intake() {
    }

    public Intake(long intk_id, String intk_name, LocalDate intk_stdate, int program_id) {
        this.intk_id = intk_id;
        this.intk_name = intk_name;
        this.intk_stdate = intk_stdate;
        this.program_id = program_id;
    }

    public long getIntk_id() {
        return intk_id;
    }

    public void setIntk_id(long intk_id) {
        this.intk_id = intk_id;
    }

    public String getIntk_name() {
        return intk_name;
    }

    public void setIntk_name(String intk_name) {
        this.intk_name = intk_name;
    }

    public LocalDate getIntk_stdate() {
        return intk_stdate;
    }

    public void setIntk_stdate(LocalDate intk_stdate) {
        this.intk_stdate = intk_stdate;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }
}
