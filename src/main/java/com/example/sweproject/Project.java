package com.example.sweproject;

public class Project {
    private String name;
    private String assignedTeam;
    private String machinesNames; //csv

    public Project(String name, String assignedTeam,String machinesNames){
        this.name = name;
        this.assignedTeam = assignedTeam;
        this.machinesNames = machinesNames;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
    public String getMachinesNames() {
        return machinesNames;
    }

    public void setMachinesNames(String machinesNames) {
        this.machinesNames = machinesNames;
    }
}
