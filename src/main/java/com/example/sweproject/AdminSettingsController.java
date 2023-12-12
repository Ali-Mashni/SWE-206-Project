package com.example.sweproject;
import com.example.sweproject.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSettingsController {
    @FXML
    private Button logout;

    @FXML
    private Button teams;
    @FXML
    private Button stat;
    @FXML
    private Button members;
    @FXML
    private Button projects;

    @FXML
    private Label usertext;
    @FXML
    private Label roletext;
    @FXML
    private Label passwordtext;

    public void initialize() {
        // Perform initial setup here, e.g., set labels based on user information
        User loggedInUser = Login.getLoggedInUser();
        usertext.setText(loggedInUser.getUsername());
        passwordtext.setText(loggedInUser.getPassword()); // Replace with the actual password
        roletext.setText(loggedInUser.getRole());
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
    public void MachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) stat.getScene().getWindow();
        changeScene(stage,"AdminMachinePage.fxml","Machine Page");

    }
    public void projectsPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminProjectsPage.fxml","Projects Page");

    }
}
