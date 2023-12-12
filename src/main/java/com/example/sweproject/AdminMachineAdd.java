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

public class AdminMachineAdd {

    @FXML
    private Button Search;

    @FXML
    private Button add;

    @FXML
    private Button addMachine;

    @FXML
    private Button delete;

    @FXML
    private Button logout1;

    @FXML
    private TextField machineIDText;

    @FXML
    private TextField machineNameText;

    @FXML
    private Button members;

    @FXML
    private Button projects;

    @FXML
    private Button setting;

    @FXML
    private Button stat;

    @FXML
    private TextField suggestedUsageText;

    @FXML
    private Button teams;

    @FXML
    private Label wronginput;



    @FXML
    void add(ActionEvent event) {
        Stage stage = (Stage) addMachine.getScene().getWindow(); // Get the reference to the current stage

        File file = new File("./src/main/java/com/example/sweproject/Machnies");

        try (Scanner machines = new Scanner(file); PrintWriter write = new PrintWriter(new FileWriter(file, true))) {
            String newMachineID = machineIDText.getText();
            String newMachineName = machineNameText.getText();
            String newSuggestedUsage = suggestedUsageText.getText();

            // Check if the fields are empty
            if (newMachineID.isEmpty() || newMachineName.isEmpty() || newSuggestedUsage.isEmpty()) {
                wronginput.setText("All fields should be entered");
                return;
            }

            // Check if the machine ID already exists
            while (machines.hasNextLine()) {
                String[] machine = machines.nextLine().split(";");
                String storedMachineID = machine[0];

                if (newMachineID.equals(storedMachineID)) {
                    wronginput.setText("Machine ID already exists");
                    wronginput.setTextFill(Paint.valueOf("red"));
                    return;
                }
            }

            // If all conditions are satisfied, add the new machine to the file
            write.println(newMachineID + ";" + newMachineName + ";" + newSuggestedUsage);

            wronginput.setText("Machine added successfully");
            wronginput.setTextFill(Paint.valueOf("green"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void deletePage(ActionEvent event) {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminMachineDeletePage.fxml","Delete Machine Page");
    }

    public void UserPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout1.getScene().getWindow();
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
    @FXML
    public void MachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) stat.getScene().getWindow();
        changeScene(stage,"AdminMachinePage.fxml","Machine Page");

    }
    @FXML
    public void searchPage2(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminTeamPage.fxml","Teams Page");

    }
    public void projectsPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) projects.getScene().getWindow();
        changeScene(stage,"AdminProjectsPage.fxml","Projects Page");

    }

}

