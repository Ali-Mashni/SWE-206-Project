package com.example.sweproject;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminMachinePage implements Initializable {
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
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private TableView<Machine> machineTableView;

    @FXML
    private TableColumn<Machine, String> machineNameTableColumn;

    @FXML
    private TableColumn<Machine, String> machineIDColumn;
    @FXML
    private TableColumn<Machine, String> suggestedUsageColumn;

    @FXML
    private TextField searchbar;

    @FXML
    private Button search;
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
    public void teamsPage(ActionEvent event) throws IOException{
        Stage stage=(Stage) teams.getScene().getWindow();
        changeScene(stage,"AdminTeamPage.fxml","Teams Page");
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
    public void MachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) stat.getScene().getWindow();
        changeScene(stage,"AdminMachinePage.fxml","Machine Page");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            search(new ActionEvent());
//            machineTableView.setEditable(true);
            machineIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            machineNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            suggestedUsageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }catch (IOException e){

        }
    }
    public void search(ActionEvent event) throws IOException {
        String searchText = searchbar.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
        ObservableList<Machine> matchingMachines = FXCollections.observableArrayList();

        File file = new File("./src/main/java/com/example/sweproject/Machnies");
        try (Scanner machines = new Scanner(file)) {
            while (machines.hasNextLine()) {
                // Adjust the splitting logic based on your Machines.txt file structure
                var machineData = machines.nextLine().split(";"); // Adjust this based on your file structure
                SimpleStringProperty machineID = new SimpleStringProperty(machineData[0]); // Adjust this index based on your file structure
                SimpleStringProperty machineName = new SimpleStringProperty(machineData[1]); // Adjust this index based on your file structure
                SimpleStringProperty suggestedUsage = new SimpleStringProperty(machineData[2]); // Adjust this index based on your file structure

                if (machineName.getValue().toLowerCase().contains(searchText) || searchText.isEmpty()) {
                    matchingMachines.add(new Machine(machineID, machineName, suggestedUsage));
                }
            }
        }

        machineIDColumn.setCellValueFactory(
                new PropertyValueFactory<Machine,String>("machineID")
        );
        machineNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<Machine,String>("name")
        );
        suggestedUsageColumn.setCellValueFactory(
                new PropertyValueFactory<Machine,String>("suggestedUsage")
        );

        // Set the matching machines to the table
        machineTableView.setItems(matchingMachines);
    }
    @FXML
    public void addMachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) add.getScene().getWindow();
        changeScene(stage,"AdminMachineAddPage.fxml","Add Machine Page");

    }
    @FXML
    public void deleteMachinePage(ActionEvent event) throws IOException {
        Stage stage=(Stage) delete.getScene().getWindow();
        changeScene(stage,"AdminMachineDeletePage.fxml","Delete Machine Page");

    }
    public void changeMachineIDCellEvent(TableColumn.CellEditEvent editedCell) {
        Machine machineSelected = machineTableView.getSelectionModel().getSelectedItem();
        String edited = editedCell.getNewValue().toString();
        File file = new File("./src/main/java/com/example/sweproject/Machnies");
        String info = "";

        try (Scanner machines = new Scanner(file)) {
            while (machines.hasNextLine()) {
                // Adjust the splitting logic based on your Machines.txt file structure
                var machineData = machines.nextLine().split(";"); // Adjust this based on your file structure
                String machineID = machineData[0]; // Adjust this index based on your file structure
                String machineName = machineData[1]; // Adjust this index based on your file structure
                String suggestedUsage = machineData[2]; // Adjust this index based on your file structure

                if (machineID.equals(machineSelected.getMachineID())) {
                    machineID = edited;
                }

                // Rewrite the line back to the file
                info = info + (machineID + ";" + machineName + ";" + suggestedUsage + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(info);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void changeMachineNameCellEvent(TableColumn.CellEditEvent editedCell)
    {
        Machine machineSelected=machineTableView.getSelectionModel().getSelectedItem();
        String edited=editedCell.getNewValue().toString();
        File file = new File("./src/main/java/com/example/sweproject/Machnies");
        String info="";
        try (Scanner machines = new Scanner(file)) {
            while (machines.hasNextLine()) {
                // Adjust the splitting logic based on your Machines.txt file structure
                var machineData = machines.nextLine().split(";"); // Adjust this based on your file structure
                String machineID = machineData[0]; // Adjust this index based on your file structure
                String machineName = machineData[1]; // Adjust this index based on your file structure
                String suggestedUsage = machineData[2]; // Adjust this index based on your file structure

                if (machineName.equals(machineSelected.getName())) {
                    machineName = edited;
                }

                // Rewrite the line back to the file
                info=info+(machineID + ";" + machineName + ";" + suggestedUsage+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        try (PrintWriter printWriter=new PrintWriter(file)){
            printWriter.print(info);
        }catch (FileNotFoundException e){
            System.out.println(e);
        }

    }
    public void changeMachineSuggestedUsageCellEvent(TableColumn.CellEditEvent editedCell) {
        Machine machineSelected = machineTableView.getSelectionModel().getSelectedItem();
        String edited = editedCell.getNewValue().toString();
        File file = new File("./src/main/java/com/example/sweproject/Machnies");
        String info = "";

        try (Scanner machines = new Scanner(file)) {
            while (machines.hasNextLine()) {
                // Adjust the splitting logic based on your Machines.txt file structure
                var machineData = machines.nextLine().split(";"); // Adjust this based on your file structure
                String machineID = machineData[0]; // Adjust this index based on your file structure
                String machineName = machineData[1]; // Adjust this index based on your file structure
                String suggestedUsage = machineData[2]; // Adjust this index based on your file structure

                if (machineID.equals(machineSelected.getMachineID())||machineName.equals(machineSelected.getName())) {
                    suggestedUsage = edited;
                }

                // Rewrite the line back to the file
                info = info + (machineID + ";" + machineName + ";" + suggestedUsage + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(info);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
