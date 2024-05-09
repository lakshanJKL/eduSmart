package com.devstack.edu.dto;

import javafx.collections.ObservableList;

public class ProgramContentDto {

    private int propertyId;
    private ObservableList contentList;

    public ProgramContentDto() {
    }

    public ProgramContentDto(int propertyId, ObservableList contentList) {
        this.propertyId = propertyId;
        this.contentList = contentList;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public ObservableList getContentList() {
        return contentList;
    }

    public void setContentList(ObservableList contentList) {
        this.contentList = contentList;
    }
}
