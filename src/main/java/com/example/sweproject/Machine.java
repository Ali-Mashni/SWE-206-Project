package com.example.sweproject;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private String machineID;
    private String name;
    private List<Reservation> usageSchedule;
    private String suggestedUsage;

    // Constructors

    public Machine(String machineID, String name, String suggestedUsage) {
        this.machineID = machineID;
        this.name = name;
        this.usageSchedule = new ArrayList<>();
        this.suggestedUsage = suggestedUsage;
    }

    // Getters and Setters

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getUsageSchedule() {
        return usageSchedule;
    }

    public void setUsageSchedule(List<Reservation> usageSchedule) {
        this.usageSchedule = usageSchedule;
    }

    public String getSuggestedUsage() {
        return suggestedUsage;
    }

    public void setSuggestedUsage(String suggestedUsage) {
        this.suggestedUsage = suggestedUsage;
    }
}

