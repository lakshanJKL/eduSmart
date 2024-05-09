package com.devstack.edu.dto;

import java.time.LocalDate;

public class IntakeDto {
    private long intkId;
    private String intkName;
    private LocalDate intkStdate;
    private int programId;

    public IntakeDto() {
    }

    public IntakeDto(long intkId, String intkName, LocalDate intkStdate, int programId) {
        this.intkId = intkId;
        this.intkName = intkName;
        this.intkStdate = intkStdate;
        this.programId = programId;
    }

    public long getIntkId() {
        return intkId;
    }

    public void setIntkId(long intkId) {
        this.intkId = intkId;
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

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }
}
