package com.devstack.edu.entity;

import javafx.collections.ObservableList;

public class Program_content {
    private int propertyId;
    private ObservableList contentList;

    public Program_content() {
    }
    public Program_content(int propertyId, ObservableList contentList) {
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
