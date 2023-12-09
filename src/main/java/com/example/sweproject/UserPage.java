package com.example.sweproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPage extends Application {
    private Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(UserPage.class.getResource("UserPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 706, 468);
        stage.setTitle("User Page");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}