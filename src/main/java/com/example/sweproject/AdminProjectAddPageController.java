package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminProjectAddPageController {
    @FXML
    private Button Search;

    @FXML
    private Button add;

    @FXML
    private Button addTeam;

    @FXML
    private TextField assignedTeam;

    @FXML
    private Button delete;

    @FXML
    private Button logout;
    @FXML
    private Button members;
    @FXML
    private TextField machines;

    @FXML
    private Button projects;

    @FXML
    private Button setting;

    @FXML
    private Button stat;

    @FXML
    private TextField projectName;

    @FXML
    private Button teams;

    @FXML
    private Label wronginput;

    @FXML
    public void UserPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout.getScene().getWindow();
        changeScene(stage,"UserPage.fxml","user page");

    }
    public void memberPage(ActionEvent event) throws IOException{
        Stage stage=(Stage) members.getScene().getWindow();
        changeScene(stage,"AdminMembersPage.fxml","Settings");
    }
    @FXML

    public void searchPage2(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminTeamPage.fxml","Teams Page");

    }

    @FXML
    public void deletePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminProjectDelete.fxml","Delete Page");

    }
    public void add(ActionEvent event) {
        Stage stage = (Stage) addTeam.getScene().getWindow(); // Get the reference to the current stage

        File file = new File("./src/main/java/com/example/sweproject/Projects.txt");

        try (Scanner projects = new Scanner(file); PrintWriter write = new PrintWriter(new FileWriter(file, true))) {
            String newProjectName = projectName.getText();
            String newAssignedTeam = assignedTeam.getText();


            // Check if the fields are empty
            if (newProjectName.isEmpty() || newAssignedTeam.isEmpty() ) {
                wronginput.setText("All fields should be entered");
                return;
            }

            // Check if the team name already exists
            while (projects.hasNextLine()) {
                String[] project = projects.nextLine().split(";");
                String storedProjectName = project[0];

                if (newProjectName.equals(storedProjectName)) {
                    wronginput.setText("Project name already exists");
                    wronginput.setTextFill(Paint.valueOf("red"));
                    return;
                }
            }

            // If all conditions are satisfied, add the new project to the file
            write.println(newProjectName + ";" + newAssignedTeam) ;

            wronginput.setText("Team added successfully");
            wronginput.setTextFill(Paint.valueOf("green"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void settingPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) setting.getScene().getWindow();
        changeScene(stage,"AdminSettings.fxml","Settings");

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
    public void projectsPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminProjectsPage.fxml","Projects Page");

    }
    @FXML
    public void MachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) stat.getScene().getWindow();
        changeScene(stage,"AdminMachinePage.fxml","Machine Page");

    }

}


