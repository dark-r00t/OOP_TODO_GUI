package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class SceneController {

    private static Scene scene;
    private static Parent root;

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

}
