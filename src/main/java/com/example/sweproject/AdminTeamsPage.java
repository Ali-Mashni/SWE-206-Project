package com.example.sweproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminTeamsPage {
    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableColumn<Team, String> teamNameColumn;

    @FXML
    private TableColumn<Team, String> assignedProjectColumn;
    @FXML
    private TableColumn<Team, String> teamLeaderColumn;
    @FXML
    private TableColumn<Team, String> machinesColumn;
    @FXML
    private Button submit;
    @FXML
    private TextField searchbar;

    @FXML
    private Button search;

    @FXML
    private Button logout;
    @FXML
    private Button setting;
    @FXML
    private Button teams;
    @FXML
    private Button stat;
    @FXML
    private Button members;

    public void search(ActionEvent event) throws IOException {
        String searchText = searchbar.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
        ObservableList<Team> matchingTeam = FXCollections.observableArrayList();

        File file = new File("./src/main/java/com/example/sweproject/Teams.txt");
        try (Scanner teams = new Scanner(file)) {
            while (teams.hasNextLine()) {
                // 0= teamname; 1= assigned project; 2= members(first is the leader), 3=machines
                var teamData = teams.nextLine().split(";");
                String teamName = teamData[0];
                String assignedProject = teamData[1];
                List<String> membersData = Arrays.asList(teamData[2].split(","));
                String leader = "";
                String members = "";
                String machines = "";

                if(membersData.size()>=1){
                    leader = membersData.get(0);
                    members = String.join(",",membersData.subList(1,membersData.size()));
                }
                if (teamData.length>3){
                    machines = teamData[3];
                }

                if (teamName.toLowerCase().contains(searchText.toLowerCase()) ||
                        searchText.isEmpty()) {
                    matchingTeam.add(new Team(teamName,assignedProject,leader,members,machines));
                }
            }
        }

        teamNameColumn.setCellValueFactory(
                new PropertyValueFactory<Team,String>("name")
        );
        assignedProjectColumn.setCellValueFactory(
                new PropertyValueFactory<Team,String>("assignedProjectName")
        );
        teamLeaderColumn.setCellValueFactory(
                new PropertyValueFactory<Team,String>("leaderName")
        );
        machinesColumn.setCellValueFactory(
                new PropertyValueFactory<Team,String>("machinesUsedNames")
        );
        // Set the matching users to the table
        teamsTable.setItems(matchingTeam);
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
        Stage stage=(Stage) setting.getScene().getWindow();
        changeScene(stage,"AdminSettings.fxml","Settings");

    }
    public void memberPage(ActionEvent event) throws IOException{
        Stage stage=(Stage) members.getScene().getWindow();
        changeScene(stage,"AdminMembersPage.fxml","Settings");
    }
}
