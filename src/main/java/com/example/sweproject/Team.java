package com.example.sweproject;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String membersNames; //csv
    private String assignedProjectName;
//    private Project assignedProject;
    private String leaderName;
    private String machinesUsedNames; //csv

    // Constructors

    public Team(String name, String assignedProjectName, String leaderName,String members,String machinesUsedNames) {
        this.name = name;
        this.membersNames = members;
        this.assignedProjectName = assignedProjectName;
        this.leaderName = leaderName;
        this.machinesUsedNames = machinesUsedNames;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return membersNames;
    }

    public void setMembers(String members) {
        this.membersNames = members;
    }

    public String getAssignedProjectName() {
        return assignedProjectName;
    }

    public void setAssignedProject(String assignedProject) {
        this.assignedProjectName = assignedProject;
    }

    public String getLeaderName() {
        return this.leaderName;
    }

    public void setLeaderName(String leader) {
        this.leaderName = leader;
    }

    public String getMachinesUsedNames() {
        return machinesUsedNames;
    }

    public void setMachinesUsedNames(String machinesUsedNames) {
        this.machinesUsedNames = machinesUsedNames;
    }

    // Team methods

    public void reserveMachine(String machineID, String startDate, String endDate) {
        // Implement the logic to reserve a machine with the specified details
        // Add the reservation to the UsageSchedule of the machine
        // You might want to check if the machine exists in the list of machinesUsed
    }

    public void addMember(Member member) {
        // Implement the logic to add a member to the team
        // Check if the member is not already in the team
    }

    public void removeMember(String memberID) {
        // Implement the logic to remove a member from the team based on memberID
        // Check if the memberID exists in the list of members
    }

    public void editMember(String memberID) {
        // Implement the logic to edit the details of a member based on memberID
        // Check if the memberID exists in the list of members
    }

    public void viewTeamMembers() {
        // Implement the logic to display information about team members
    }

    public void viewMachines() {
        // Implement the logic to display information about machines used by the team
    }
}

