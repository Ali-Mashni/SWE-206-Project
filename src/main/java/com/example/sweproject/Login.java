package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Login {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label wronginput;
    @FXML
    private Button login;
    private static User loggedInUser;

    public void UserLogIn(ActionEvent event) {
        Stage stage = (Stage) login.getScene().getWindow(); // Get the reference to the current stage

        File file = new File("./src/main/java/com/example/sweproject/User.txt");
        try (Scanner users = new Scanner(file)) {
            while (users.hasNextLine()) {
                String[] user = users.nextLine().split(";");
                // 0=id, 1=pass, 2=username, 3=role, 4= research interest(csv)
                String storedPassword = user[1];
                String storedUsername = user[2];
                String role = user[3];


                // Read these values only once, and then compare
                if (password.getText().equals(storedPassword) && username.getText().equals(storedUsername)) {
                    if ("Admin".equals(role)) {
                        loggedInUser = new Admin(storedUsername, storedPassword, role);
                        changeScene(stage, "adminPage.fxml", "Admin Page");
                        return;
                    } else {
                        String researchInterests = user[4];

                        loggedInUser = new Member(storedUsername, storedPassword, role,researchInterests);
                        changeScene(stage, "member page.fxml", "Member Page");
                        return;
                    }
                } else if (password.getText().isEmpty() || username.getText().isEmpty()) {
                    wronginput.setText("Please enter your username and password.");
                    return;
                }
            }
            wronginput.setText("Wrong input ");
        } catch (IOException e) {
            System.out.println(e);
        }
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
    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
