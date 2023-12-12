package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamInfoPageController {
    @FXML
    private Button logout;
    @FXML
    private Button settings;
    @FXML
    private Button myTeams;
    @FXML
    private Button stat;
    @FXML
    private Text teamName;
    @FXML
    private Text assignedProjects;
    @FXML
    private Text teamLeader;
    @FXML
    private Text teamMachines;
    @FXML
    private Text members;

    @FXML
    private TextField selectedTeam;
    @FXML
    private Button search;

    public void UserPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout.getScene().getWindow();
        changeScene(stage,"UserPage.fxml","userpage");
    }
    private void changeScene(Stage stage, String fxml, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));

        try {
            stage.setTitle(title);
            stage.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void settingPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) settings.getScene().getWindow();
        changeScene(stage,"MemberSettings.fxml","Settings");
    }
    public void myTeamsPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) myTeams.getScene().getWindow();
        changeScene(stage,"myTeamsPage.fxml","my teams");
    }
    public void showInfo(ActionEvent event) throws IOException {
        String team = selectedTeam.getText();
        boolean isFound = false;
        User loggedInUser = Login.getLoggedInUser();
        Member member = (Member) loggedInUser;
        String memberName = member.getUsername();
        String name = null;
        String project = null;
        String membersInTeam = null;
        String leader = null;
        String machines = null;
        File file = new File("./src/main/java/com/example/sweproject/Teams.txt");
        try (Scanner teams = new Scanner(file)) {
            while (teams.hasNextLine() & !isFound) {
                String[] user = teams.nextLine().split(";");
                // 0=name, 1=project, 2=members, 3=machines
                name = user[0];
                if (name.equals(team)){
                    membersInTeam = user[2];
                    if (membersInTeam.contains(memberName)){
                        project = user[1];
                        int leaderIndex = membersInTeam.indexOf(',');
                        leader = membersInTeam.substring(0, leaderIndex);
                        machines = user[3];
                        isFound = true;
                    }
                }
            }
            if (!isFound){
                teamName.setText("No team with such name");
            }
            else if(isFound){
                teamName.setText("Team name: "+ name);
                assignedProjects.setText("project assigned: "+ project);
                teamLeader.setText("Team leader: "+ leader);
                members.setText("Team members: "+ membersInTeam);
                teamMachines.setText("Team machines: "+ machines);
            }
        }
    }
    public void statPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) stat.getScene().getWindow();
        changeScene(stage, "statisticsPage.fxml", "statistics");
    }
}
