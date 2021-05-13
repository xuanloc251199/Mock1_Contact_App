package com.xuanloc.mock1.model;

public class CallLog {

    private String phoneNumber;
    private String duration;
    private String date;

    public CallLog(String phoneNumber, String duration, String date) {
        this.phoneNumber = phoneNumber;
        this.duration = duration;
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
