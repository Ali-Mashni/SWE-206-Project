package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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
    private Label teamID;
    @FXML
    private Label teamName;
    @FXML
    private Label assignedProjects;
    @FXML
    private Label teamLeader;


    public void initialize() {
        // Perform initial setup here, e.g., set labels based on user information
        User loggedInUser = Login.getLoggedInUser();
        Member member = (Member) loggedInUser;

//        usertext.setText(loggedInUser.getUsername());
//        passwordtext.setText(loggedInUser.getPassword()); // Replace with the actual password
//        roletext.setText(loggedInUser.getRole());
    }

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

    public Label getTeamID() {
        return teamID;
    }

    public void setTeamID(Label teamID) {
        this.teamID = teamID;
    }

    public Label getTeamName() {
        return teamName;
    }

    public void setTeamName(Label teamName) {
        this.teamName = teamName;
    }

    public Label getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(Label assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public Label getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Label teamLeader) {
        this.teamLeader = teamLeader;
    }
}
