package com.devstack.edu.model;

import javafx.collections.ObservableList;

public class Program_content {
    private int property_id;
    private ObservableList content_list;

    public Program_content() {
    }

    public Program_content(int property_id, ObservableList content_list) {
        this.property_id = property_id;
        this.content_list = content_list;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public ObservableList getContent_list() {
        return content_list;
    }

    public void setContent_list(ObservableList content_list) {
        this.content_list = content_list;
    }
}
