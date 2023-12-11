package com.example.sweproject;

import java.util.ArrayList;

public class Member extends User{
    private ArrayList<Team> teamsInvolved;
    private ArrayList<Project> projectInvolved;
    private String researchInterests; // csv "aaa,s,sd,s,sd"
    public Member(String username, String password, String role,String researchInterest) {
        super(username, password, role);
        projectInvolved=new ArrayList<>();
        teamsInvolved=new ArrayList<>();
        this.researchInterests = researchInterest;
    }

    public void setResearchInterests(String researchInterests) {
        this.researchInterests = researchInterests;
    }
    public void addTeam(Team team){
        teamsInvolved.add(team);
    }
    public void addProject(Project project){
        projectInvolved.add(project);
    }

    public String getResearchInterests() {
        return researchInterests;
    }
    public ArrayList<Team> getTeamsInvolved() {
        return teamsInvolved;
    }

}
