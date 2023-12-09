package com.example.sweproject;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamID;
    private String name;
    private List<Member> members;
    private Project assignedProject;
    private Member leader;
    private List<Machine> machinesUsed;

    // Constructors

    public Team(String teamID, String name, Project assignedProject, Member leader) {
        this.teamID = teamID;
        this.name = name;
        this.members = new ArrayList<>();
        this.assignedProject = assignedProject;
        this.leader = leader;
        this.machinesUsed = new ArrayList<>();
    }

    // Getters and Setters

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }

    public Member getLeader() {
        return leader;
    }

    public void setLeader(Member leader) {
        this.leader = leader;
    }

    public List<Machine> getMachinesUsed() {
        return machinesUsed;
    }

    public void setMachinesUsed(List<Machine> machinesUsed) {
        this.machinesUsed = machinesUsed;
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

