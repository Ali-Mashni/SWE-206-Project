package com.example.sweproject;


public class Reservation {
    private String machineID;
    private String startDate;
    private String endDate;

    // Constructors

    public Reservation(String machineID, String startDate, String endDate) {
        this.machineID = machineID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
