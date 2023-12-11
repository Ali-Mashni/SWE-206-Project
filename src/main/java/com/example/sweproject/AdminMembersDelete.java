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

public class AdminMembersDelete {
    @FXML
    private Button Search;

    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button deleteMember;

    @FXML
    private TextField enterusername;


    @FXML
    private Button logout;
    @FXML
    private Label wronginput;

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
    public void UserPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout.getScene().getWindow();
        changeScene(stage,"UserPage.fxml","userpage");

    }
    public void addMemberPage(ActionEvent event){
        Stage stage=(Stage) add.getScene().getWindow();
        changeScene(stage,"AdminMembersAdd.fxml","userpage");
    }    @FXML
    public void searchPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) Search.getScene().getWindow();
        changeScene(stage,"AdminMembersPage.fxml","SearchPage");

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
    public void delete(ActionEvent event) {
        Stage stage = (Stage) delete.getScene().getWindow(); // Get the reference to the current stage

        File file = new File("./src/main/java/com/example/sweproject/User.txt");

        try {
            // Read existing lines from the file
            List<String> lines = new ArrayList<>();
            boolean userFound = false;
            String deleteUsername=enterusername.getText();

            try (Scanner users = new Scanner(file)) {
                while (users.hasNextLine()) {
                    String line = users.nextLine();
                    String[] user = line.split(";");
                    String storedUsername = user[2];

                    if (deleteUsername.equals(storedUsername)) {
                        userFound = true;
                    } else {
                        lines.add(line);
                    }
                }
            }

            if (!userFound) {
                wronginput.setText("Username not found");
                wronginput.setTextFill(Paint.valueOf("red"));
                return;
            }

            // Write the updated list back to the file
            try (PrintWriter rewrite = new PrintWriter(new FileWriter(file, false))) {
                for (String line : lines) {
                    rewrite.println(line);
                }
            }

            wronginput.setText("User deleted successfully");
            wronginput.setTextFill(Paint.valueOf("green"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @FXML
    public void searchPage2(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminTeamPage.fxml","Teams Page");

    }
}
