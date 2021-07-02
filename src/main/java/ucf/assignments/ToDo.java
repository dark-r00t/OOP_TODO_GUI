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
            // create temp.txt file

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoMenu.fxml")));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Todo List");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
