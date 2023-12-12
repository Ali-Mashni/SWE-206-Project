package com.example.sweproject;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private SimpleStringProperty machineID;
    private SimpleStringProperty name;
    private List<Reservation> usageSchedule;
    private SimpleStringProperty suggestedUsage;

    // Constructors

    public Machine(SimpleStringProperty machineID, SimpleStringProperty name, SimpleStringProperty suggestedUsage) {
        this.machineID = machineID;
        this.name = name;
        this.usageSchedule = new ArrayList<>();
        this.suggestedUsage = suggestedUsage;
    }

    // Getters and Setters

    public String getMachineID() {
        return machineID.getValue();
    }

    public void setMachineID(String machineID) {
        this.machineID = new SimpleStringProperty(machineID);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name =new SimpleStringProperty(name) ;
    }

    public List<Reservation> getUsageSchedule() {
        return usageSchedule;
    }

    public void setUsageSchedule(List<Reservation> usageSchedule) {
        this.usageSchedule = usageSchedule;
    }

    public String  getSuggestedUsage() {
        return suggestedUsage.getValue();
    }

    public void setSuggestedUsage(SimpleStringProperty suggestedUsage) {
        this.suggestedUsage = suggestedUsage;
    }
}

