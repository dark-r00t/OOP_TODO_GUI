/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */


package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

    public static void switchToMenu() throws IOException {
        // gets the ToDoMenu.fxml
        // displays the scene

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoMenu.fxml")));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("To-Do List Menu");
        stage.show();
    }

    public static void switchToTask() throws IOException {
        // gets the ToDoTask.fxml
        // displays the scene

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoTask.fxml")));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("To-Do List Task");
        stage.show();

    }

    public static void menuHelp() throws IOException {
        // gets the ToDoMenuHelp.fxml
        // displays the scene

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoMenuHelp.fxml")));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        // ALL PRAISE REY
        stage.setTitle("To-Do List Menu Helper");
        stage.setScene(scene);
        stage.show();
    }

    public static void taskHelp() throws IOException {
        // gets the ToDoTaskHelp.fxml
        // displays the scene

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoTaskHelp.fxml")));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        // ALL PRAISE REY
        stage.setTitle("To-Do List Task Helper");
        stage.setScene(scene);
        stage.show();

    }

    public static void savePopUp(String name) {
        // create a new mini scene
        // display file saved name and file path
        // display scene

        System.out.println("Save Notification Opened");

        Stage stage = new Stage();

        String path = System.getProperty("user.dir") + "\\ToDo_Files\\" + name;
        Label label = new Label("\n   Saved file: " + name + "\n\n   Path: " + path);

        VBox popup = new VBox(20);
        popup.getChildren().addAll(label);

        Scene scene = new Scene(popup, 500, 100);

        stage.setScene(scene);
        stage.show();
    }

}
