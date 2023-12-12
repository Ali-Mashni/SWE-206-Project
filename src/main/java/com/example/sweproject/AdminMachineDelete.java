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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMachineDelete {
    @FXML
    private Button Search;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button deleteMachiine;

    @FXML
    private Button logout1;

    @FXML
    private TextField machineIDText;

    @FXML
    private Button members;

    @FXML
    private Button projects;

    @FXML
    private Button setting;

    @FXML
    private Button stat;

    @FXML
    private Button teams;

    @FXML
    private Label wronginput;



    @FXML
    public void addMachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) add.getScene().getWindow();
        changeScene(stage,"AdminMachineAddPage.fxml","Add Machine Page");

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
    @FXML
    void delete(ActionEvent event) {
        String machineIDToDelete = machineIDText.getText().trim();

        if (machineIDToDelete.isEmpty()) {
            // No machine ID entered, show an error message
            wronginput.setText("Enter a machine ID to delete");
            return;
        }

        File inputFile = new File("./src/main/java/com/example/sweproject/machnies");

        try (Scanner scanner = new Scanner(inputFile)) {
            boolean machineFound = false;
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] machineData = line.split(";");
                String machineID = machineData[0];
                String machineName = machineData[1];

                if (machineID.equals(machineIDToDelete) || machineName.equals(machineIDToDelete)) {
                    machineFound = true;
                    File tfile=new File("./src/main/java/com/example/sweproject/Teams.txt");
                    removeWordFromFile(tfile,machineName);
                } else {
                    lines.add(line);
                }
            }

            if (!machineFound) {
                // Machine with the provided ID not found, show an error message
                wronginput.setText("Machine with ID/name " + machineIDToDelete + " not found");
                wronginput.setTextFill(Paint.valueOf("red"));
                return;
            }
            try (PrintWriter rewrite = new PrintWriter(new FileWriter(inputFile, false))) {
                for (String line : lines) {
                    rewrite.println(line);
                }
            }

            wronginput.setText("Machine deleted successfully");
            wronginput.setTextFill(Paint.valueOf("green"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static void removeWordFromFile(File file, String wordToRemove) {
        try {
            // Read existing lines from the file
            StringBuilder content = new StringBuilder();
            boolean wordFound = false;

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // Replace the word in the line
                    String updatedLine = line.replaceAll("\\b" + wordToRemove + "\\b,?", "").replaceAll(",{2,}", ",").replaceAll(",$", "");

                    // Check if the word was found in this line
                    if (!line.equals(updatedLine)) {
                        wordFound = true;
                    }
                    // Append the updated line to the content
                    content.append(updatedLine).append("\n");
                }
            }

            if (!wordFound) {
//                System.out.println("Word '" + wordToRemove + "' not found in the file");
                return;
            }

            // Write the updated content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
                writer.print(content.toString());
            }

//            System.out.println("Word '" + wordToRemove + "' removed successfully from the file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
