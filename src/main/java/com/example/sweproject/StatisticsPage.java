package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StatisticsPage {
    @FXML
    private Button logout;
    @FXML
    private Button settings;
    @FXML
    private Button myTeams;
    @FXML
    private Text mostActiveMemberText;
    @FXML
    private Text mostUsedMachinesText;
    @FXML
    private Text memberInvolvedInMostProjectsText;
    @FXML
    private Text mostTimeReserved;
    @FXML
    private Button showInfo;


    public void UserPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        changeScene(stage, "UserPage.fxml", "userpage");

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
        Stage stage = (Stage) settings.getScene().getWindow();
        changeScene(stage, "MemberSettings.fxml", "Settings");
    }

    public void myTeamsPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) myTeams.getScene().getWindow();
        changeScene(stage, "myTeamsPage.fxml", "my teams");
    }

    public void stat() {
        File file = new File("./src/main/java/com/example/sweproject/Teams.txt");
        ArrayList<String> activeMembers = new ArrayList<>();
        ArrayList<String> mostUsedMachines = new ArrayList<>();
        ArrayList<String> membersInvolvedInMostProjects = new ArrayList<>();
        try (Scanner teams = new Scanner(file)) {
            while (teams.hasNextLine()) {
                String[] user = teams.nextLine().split(";");
                // 0=name, 1=project, 2=members, 3=machine
                String members = user[2];
                String machine = user[3];
                mostUsedMachines.add(machine);
                String[] membersInTeam = members.split(",");
                for (int i = 0; i < membersInTeam.length; i++) {
                    activeMembers.add(membersInTeam[i]);
                }
            }
            mostActiveMemberText.setText("Most Active member: " + mostFrequentElement(activeMembers));
            mostUsedMachinesText.setText("Most Active machines: " + mostFrequentElement(mostUsedMachines));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        File file1 = new File("./src/main/java/com/example/sweproject/Projects.txt");
        try (Scanner projects = new Scanner(file1)) {
            while (projects.hasNextLine()) {
                String[] project = projects.nextLine().split(";");
                String members = project[2];
                String[] membersInTeam = members.split(",");
                for (int i = 0; i < membersInTeam.length; i++) {
                    membersInvolvedInMostProjects.add(membersInTeam[i]);
                }
            }
            memberInvolvedInMostProjectsText.setText("Member in most projects is: " + mostFrequentElement(membersInvolvedInMostProjects));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String mostFrequentElement(ArrayList<String> array) {
        int max_count = 0;
        String maxFreq = new String();
        for (int i = 0; i < array.size(); i++) {
            int count = 0;
            for (int j = 0; j < array.size(); j++) {
                if (array.get(i).equals(array.get(j))) {
                    count++;
                }
            }
            if (count > max_count) {
                max_count = count;
                maxFreq = array.get(i);
            }
        }
        return maxFreq;
    }
}

