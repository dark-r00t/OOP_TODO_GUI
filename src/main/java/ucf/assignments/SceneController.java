/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */


package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

    private static Scene scene;

    public static void switchToMenu() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoMenu.fxml")));
        Stage stage = new Stage();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void switchToTask() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoTask.fxml")));
        Stage stage = new Stage();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void menuHelp() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoMenuHelp.fxml")));
        Stage stage = new Stage();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void taskHelp() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("ToDoTaskHelp.fxml")));
        Stage stage = new Stage();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void closeCurrentScene() {


    }

}
