package com.example.sweproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminMemberSearchController implements Initializable {
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
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private Button projects;
    @FXML
    private TableView<Member> userTable;

    @FXML
    private TableColumn<Member, String> usernameColumn;

    @FXML
    private TableColumn<Member, String> roleColumn;

    @FXML
    private TableColumn<Member, String> reaserchColumn;
    @FXML
    private Button submit;
    @FXML
    private TextField searchbar;



    public void submit(ActionEvent event) throws IOException {
        String searchText = searchbar.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
        ObservableList<Member> matchingUsers = FXCollections.observableArrayList();

        File file = new File("./src/main/java/com/example/sweproject/User.txt");
        try (Scanner users = new Scanner(file)) {
            while (users.hasNextLine()) {
                // 0=id, 1=pass, 2=username, 3=role, 4= research interest(csv)
                var userData = users.nextLine().split(";");
                String storedPassword = userData[1];
                String storedUsername = userData[2];
                String role = userData[3];
                String researchInterests ="";

                if(userData.length >4 && !"Admin".equals(role))
                    researchInterests = userData[4];

                // Check if the storedUsername contains the searchText
                if (storedUsername.toLowerCase().contains(searchText.toLowerCase()) ||
                        role.toLowerCase().contains(searchText.toLowerCase()) ||
                        searchText.isEmpty()) {
                    matchingUsers.add(new Member(storedUsername, storedPassword, role,researchInterests));
                }
            }
        }

        usernameColumn.setCellValueFactory(
                new PropertyValueFactory<Member,String>("username")
        );
        roleColumn.setCellValueFactory(
                new PropertyValueFactory<Member,String>("role")
        );
        reaserchColumn.setCellValueFactory(
                new PropertyValueFactory<Member,String>("researchInterests")
        );

        // Set the matching users to the table
        userTable.setItems(matchingUsers);
    }
    public void UserPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout.getScene().getWindow();
        changeScene(stage,"UserPage.fxml","userpage");

    }
    public void addMemberPage(ActionEvent event){
        Stage stage=(Stage) add.getScene().getWindow();
        changeScene(stage,"AdminMembersAdd.fxml","userpage");
    }
    @FXML
    public void deletePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminMemberDelete.fxml","SearchPage");

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            submit(new ActionEvent());
        }catch (IOException e){

        }
    }
    @FXML
    public void searchPage2(ActionEvent event) throws IOException {
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminTeamPage.fxml","Teams Page");

    }
}
