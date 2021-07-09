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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class ToDo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            String path = UtilityGeneral.userDirec();

            for (int i = 0; i < 100; i++) {
                int item = i + 1;
                String newPath = path + "\\list_" + item + ".txt";

                File index = new File(newPath);

                if(index.delete()){
                    System.out.println("Deleted list_" + item + ".txt successfully.");
                }
            }

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
