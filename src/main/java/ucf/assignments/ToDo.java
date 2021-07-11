/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ToDo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // creates needed folders
            // removes all temp files if they are there
            // initial scene set up
            // set the initial scene to ToDoMenu.fxml

            CastedUtilityGeneral.addToDoSaveFolder();
            CastedUtilityGeneral.removeTempFiles();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoMenu.fxml")));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("To-Do List Menu");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//TODO update puml
//TODO create test cases
