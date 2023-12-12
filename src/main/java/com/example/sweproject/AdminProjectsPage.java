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
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminProjectsPage implements Initializable {
    @FXML
    private TableView<Project> projectsTable;

    @FXML
    private TableColumn<Project, String> projectNameColumn;

    @FXML
    private TableColumn<Project, String> assignedTeamColumn;
    @FXML
    private TableColumn<Project, String> machinesColumn;

    @FXML
    private TextField searchbar;

    @FXML
    private Button search;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            search(new ActionEvent());
        }catch (IOException e){

        }
    }
    public void addProjectPage(ActionEvent event){
        Stage stage=(Stage) add.getScene().getWindow();
        changeScene(stage,"AdminProjectAdd.fxml","Add Project Page");
    }
    public void deleteProjectPage(ActionEvent event) throws IOException {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminProjectDelete.fxml","Delete Project Page");

    }
    public void search(ActionEvent event) throws IOException {
        String searchText = searchbar.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
        ObservableList<Project> matchingTeam = FXCollections.observableArrayList();

        File file = new File("./src/main/java/com/example/sweproject/Projects.txt");
        try (Scanner projects = new Scanner(file)) {
            while (projects.hasNextLine()) {
                // 0= name; 1= assigned team; 2= machines(csv)
                var projectData = projects.nextLine().split(";");
                String projName = projectData[0];
                String assignedTeam = projectData[1];
                String machines = "";

                if (projectData.length>2){
                    machines = projectData[2];
                }

                if (projName.toLowerCase().contains(searchText.toLowerCase()) ||
                        searchText.isEmpty()) {
                    matchingTeam.add(new Project(projName,assignedTeam,machines));
                }
            }
        }

        projectNameColumn.setCellValueFactory(
                new PropertyValueFactory<Project,String>("name")
        );
        assignedTeamColumn.setCellValueFactory(
                new PropertyValueFactory<Project,String>("assignedTeam")
        );

        // Set the matching users to the table
        projectsTable.setItems(matchingTeam);
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


}
