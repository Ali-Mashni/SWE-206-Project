package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPage {
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
    @FXML
    private Button projects;

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
