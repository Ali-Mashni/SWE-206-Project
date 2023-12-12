package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberPage {
    @FXML
    private Button logout;
    @FXML
    private Button settings;
    @FXML
    private Button myTeams;
    @FXML
    private Button stat;


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
        Stage stage=(Stage) settings.getScene().getWindow();
        changeScene(stage,"MemberSettings.fxml","Settings");
    }
    public void myTeamsPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) myTeams.getScene().getWindow();
        changeScene(stage,"myTeamsPage.fxml","my teams");
    }
    public void statPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) stat.getScene().getWindow();
        changeScene(stage, "statisticsPage.fxml", "statistics");
    }
}
