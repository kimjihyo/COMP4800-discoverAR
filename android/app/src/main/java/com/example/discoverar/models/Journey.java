package com.example.discoverar.models;

public class Journey {
    private String createdAt;
    private String updatedAt;
    private int id;
    private String title;
    private Discovery[] discoveries;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String newCreatedAt) {
        createdAt = newCreatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String newUpdatedAt) {
        updatedAt = newUpdatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public Discovery[] getDiscoveries() {
        return discoveries;
    }

    public void setDiscoveries(Discovery[] newDiscoveries) {
        discoveries = newDiscoveries;
    }
}
