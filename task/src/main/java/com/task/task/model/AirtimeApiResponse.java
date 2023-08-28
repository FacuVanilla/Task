package com.task.task.model;

public class AirtimeApiResponse {
    private String status;

    public AirtimeApiResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
