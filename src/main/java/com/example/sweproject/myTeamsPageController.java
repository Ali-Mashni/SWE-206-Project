package com.example.sweproject;
import com.example.sweproject.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class myTeamsPageController implements Initializable {
    @FXML
    private Button logout;

    @FXML
    private Button myTeams;
    @FXML
    private Button stat;
    @FXML
    private Button settings;
    @FXML
    Button currentTeam;
    @FXML
    private ListView<String> teams;

    private String s;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teams.getItems().addAll(getTeamsInvolved());
        teams.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedTeam = teams.getSelectionModel().getSelectedItem();
                currentTeam.setText("show " + selectedTeam+ " information");
            }
        });
    }
    public ArrayList<String> getTeamsInvolved() {
        User loggedInUser = Login.getLoggedInUser();
        ArrayList<String> teamsNames = new ArrayList<>();
        Member member = (Member) loggedInUser;
        ArrayList<Team> teamsInvolved = member.getTeamsInvolved();
        for (int i = 0; i < teamsInvolved.size();i++){
            teamsNames.add(teamsInvolved.get(i).getName());
        }
        return teamsNames;
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
    public void settingPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) settings.getScene().getWindow();
        changeScene(stage,"MemberSettings.fxml","Settings");
    }
    public void TeamInfoPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) currentTeam.getScene().getWindow();
        changeScene(stage,"MemberSettings.fxml","Settings");
    }

    public ListView<String> getTeams() {
        return teams;
    }
    public String selectedTeam(){
        return currentTeam.getText();
    }
}

