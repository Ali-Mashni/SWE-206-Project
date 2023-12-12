package com.example.sweproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReserveMachinePage {
    @FXML
    private Button logout;
    @FXML
    private Button settings;
    @FXML
    private Button myTeams;
    @FXML
    private Button stat;
    @FXML
    private TextField selectedTeam;
    @FXML
    private Button reserveMachine;
    @FXML
    private Text message;
    @FXML
    private Text reservedMachine;
    @FXML
    private TextField startDateLabel;
    @FXML
    private TextField endDateLabel;
    @FXML
    private TextField day;

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
    public void showInfo(ActionEvent event) throws IOException {
        String team = selectedTeam.getText();
        boolean isFound = false;
        boolean isPassed = false;
        User loggedInUser = Login.getLoggedInUser();
        Member member = (Member) loggedInUser;
        String memberName = member.getUsername();
        String name = null;
        String membersInTeam;
        String usedMachine = null;
        File file = new File("./src/main/java/com/example/sweproject/Teams.txt");
        try (Scanner teams = new Scanner(file)) {
            while (teams.hasNextLine() & !isFound) {
                String[] user = teams.nextLine().split(";");
                // 0=name, 1=project, 2=project, 3=machine
                name = user[0];
                System.out.println(name);
                if (name.equals(team)){
                    isFound = true;
                    membersInTeam = user[2];
                    if (membersInTeam.contains(memberName)){
                        isPassed = true;
                        usedMachine = user[3];
                        reservedMachine.setText("Machine to reserve is: " + usedMachine);
                    }
                    else {
                        message.setText("you are not a member of this team");
                    }
                }else {
                    message.setText("there is no team with such a name");
                }
            }
            if(isPassed) {
                ArrayList<String> machines = new ArrayList<>();
                try (Scanner teams2 = new Scanner(file)) {
                    while (teams2.hasNextLine()) {
                        String[] user = teams2.nextLine().split(";");
                        // 0=name, 1=project, 2=project, 3=machine
                        String machine = user[3];
                        machines.add(machine);
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                int teamsWithSameMachine = 0;
                for (int i = 0; i < machines.size(); i++) {
                    if (machines.get(i).equals(usedMachine)) {
                        teamsWithSameMachine++;
                    }
                }
                File file1 = new File("./src/main/java/com/example/sweproject/Machnies.txt");
                try (Scanner machine = new Scanner(file1); PrintWriter write = new PrintWriter(new FileWriter(file1, true));BufferedWriter file3 = new BufferedWriter(write);) {
                    while (machine.hasNextLine()) {
                        if (teamsWithSameMachine == 1) {
                            String machineProfile = machine.nextLine();
                            String[] machine1 = machineProfile.split(";");
                            // 0=machineID, 1=machineName, 2=suggestedUsage,
                            String machineName = machine1[1];
                            String[] arrayDays = {"saturday", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday"};
                            ArrayList<String> days = new ArrayList<>(List.of(arrayDays));
                            if (machineName.equals(usedMachine)) {
                                String startDate = startDateLabel.getText(); // reservation start from 13 until 23
                                String endDate = endDateLabel.getText(); // end time should be also between 13 and 23
                                if (Integer.parseInt(startDate) < Integer.parseInt(endDate) & Integer.parseInt(startDate) >= 13 & Integer.parseInt(startDate) <= 23 & Integer.parseInt(endDate) >= 13 & Integer.parseInt(endDate) <= 23 & days.contains(day.getText())) {
                                    machineProfile += ";" + name + "," + day.getText() + "," + startDate + "," + endDate + "/";
                                    file3.write(machineProfile);
                                    message.setText("saved successfully");
                                } else {
                                    message.setText("invalid hour or day!");
                                }
                            }
                        }
                        else {
                                String machineProfile = machine.nextLine();
                                String[] machine1 = machineProfile.split(";");
                                // 0=machineID, 1=machineName, 2=suggestedUsage, 3 = usageSchedule
                                String machineName = machine1[1];
                                String usageSchedule = machine1[3];
                                String[] usageScheduleEach = usageSchedule.split("/");

                                String[] arrayDays = {"saturday", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday"};
                                ArrayList<String> days = new ArrayList<>(List.of(arrayDays));
                                if (machineName.equals(usedMachine)) {
                                    String startDate = startDateLabel.getText(); // reservation start from 13 until 23
                                    String endDate = endDateLabel.getText(); // end time should be also between 13 and 23
                                    if (Integer.parseInt(startDate) < Integer.parseInt(endDate) & Integer.parseInt(startDate) >= 13 & Integer.parseInt(startDate) <= 23 & Integer.parseInt(endDate) >= 13 & Integer.parseInt(endDate) <= 23 & days.contains(day.getText())) {
                                        for (int i = 0;i < usageScheduleEach.length;i++){
                                            // 0 = teamName, 1 = day, 2 = startDate, 3 = endDate
                                            if (usageScheduleEach[i].split(",")[1].equals(day.getText())){
                                                if (Integer.parseInt(startDate) >= Integer.parseInt(usageScheduleEach[i].split(",")[2]) & Integer.parseInt(startDate) <= Integer.parseInt(usageScheduleEach[i].split(",")[3]) & Integer.parseInt(endDate) >= Integer.parseInt(usageScheduleEach[i].split(",")[2]) & Integer.parseInt(endDate) <= Integer.parseInt(usageScheduleEach[i].split(",")[3])){
                                                    message.setText("this time is reserved by another team");
                                                }
                                                else {
                                                    machineProfile += "/" + name + "," + day.getText() + "," + startDate + "," + endDate;

                                                }
                                            }
                                            else {
                                                machineProfile += "/" + name + "," + day.getText() + "," + startDate + "," + endDate;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                    } catch(FileNotFoundException e){
                        throw new RuntimeException(e);
                    }
    }
    public void statPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) stat.getScene().getWindow();
        changeScene(stage, "statisticsPage.fxml", "statistics");
    }
}

