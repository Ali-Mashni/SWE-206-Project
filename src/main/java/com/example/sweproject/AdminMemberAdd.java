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

public class AdminMemberAdd {

        @FXML
        private Button Search;

        @FXML
        private Button add;
        @FXML
        private Button addMember;
        @FXML
        private Button delete;

        @FXML
        private TextField enterusername;

        @FXML
        private TextField enterPass;

        @FXML
        private TextField enterResearch;

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
    @FXML
    public void searchPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) Search.getScene().getWindow();
        changeScene(stage,"AdminMembersPage.fxml","SearchPage");

    }
    @FXML
    public void deletePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminMemberDelete.fxml","SearchPage");

    }
    public void add(ActionEvent event) {
        Stage stage = (Stage) addMember.getScene().getWindow(); // Get the reference to the current stage

        File file = new File("./src/main/java/com/example/sweproject/User.txt");

        try (Scanner users = new Scanner(file); PrintWriter write = new PrintWriter(new FileWriter(file, true))) {
            String newUsername = enterusername.getText();
            String newResearch = enterResearch.getText();

            // Check if the fields are empty
            if (newUsername.isEmpty() || newResearch.isEmpty()) {
                wronginput.setText("All fields should be entered");
                return;
            }

            // Check if the username already exists
            while (users.hasNextLine()) {
                String[] user = users.nextLine().split(";");
                String storedUsername = user[2];

                if (newUsername.equals(storedUsername)) {
                    wronginput.setText("Username already exists");
                    wronginput.setTextFill(Paint.valueOf("red"));
                    return;
                }
            }

            // If all conditions are satisfied, add the new user to the list
            int newUserId = getNewUserId(file);
            String newPassword = newUsername + "123"; // You may want to handle password input securely
            String newRole = "member";

            // Read existing lines from the file
            List<String> lines = new ArrayList<>();
            while (users.hasNextLine()) {
                lines.add(users.nextLine());
            }

            // Add the new user information to the list
            lines.add(newUserId + ";" + newPassword + ";" + newUsername + ";" + newRole + ";" + newResearch);

            // Write the updated list back to the file
            try (PrintWriter rewrite = new PrintWriter(new FileWriter(file, true))) {
                for (String line : lines) {
                    rewrite.println(line);
                }
            }

            wronginput.setText("User added successfully");
            wronginput.setTextFill(Paint.valueOf("green"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Helper method to get a new user ID based on the existing user count
    private int getNewUserId(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            int userCount = 0;
            String lastLine="";
            while (scanner.hasNextLine()) {
                lastLine=scanner.nextLine();

            }
            if (lastLine.equals(""))
                return 0;
            String[] strings=lastLine.split(";");
            return Integer.parseInt(strings[0]) + 1; // Assuming user IDs start from 1
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
}


