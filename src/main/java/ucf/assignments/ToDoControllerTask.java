/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ToDoControllerTask {
    public SplitPane mainPane;
    public SplitPane splitPane;
    public TextField descriptionTaskTextBox;
    public DatePicker dataTextBox;
    public TextField titleTaskTextBox;
    public TextField updateTaskTextBox;
    public ListView taskList;

    // on open delete data in temp.txt
    // create linked list of ToDoItem objects
    // define a global variable for the user directory

    @FXML
    public void listClicked(MouseEvent mouseEvent) {
        // allow for multiple items
        // save 'selected' accordingly

        // code idea?
        // todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void displayAllTasksButton(ActionEvent actionEvent) {
        // call displayAll()


    }

    @FXML
    public void displayCompletedTasksButton(ActionEvent actionEvent) {
        // call displayCompleted()


    }

    @FXML
    public void displayIncompleteTasksButton(ActionEvent actionEvent) {
        // call displayIncomplete()


    }

    @FXML
    public void markAsCompleteButton(ActionEvent actionEvent) {
        // call markAsComplete()


    }

    @FXML
    public void deleteTaskButton(ActionEvent actionEvent) {
        // call deleteTask()


    }

    @FXML
    public void addNewTaskButton(ActionEvent actionEvent) {
        // call addNewTask()


    }

    @FXML
    public void editDescription(ActionEvent actionEvent) {
        // call editDescriptionInit()


    }

    @FXML
    public void changeDate(ActionEvent actionEvent) {
        // call changeDateInit()


    }

    @FXML
    public void saveAllToDoButton(ActionEvent actionEvent) {
        // call save()


    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        // prompt to see if user wants to save
        // - call save()
        // close any open files
        // load ToDoMenu scene when clicked


    }

}
