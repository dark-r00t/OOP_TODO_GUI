/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TaskToDoController {
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

        taskList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(taskList);
    }

    @FXML
    public void displayAllTasksButton(ActionEvent actionEvent) {
        // call displayAll()

        displayAll();
    }

    public void displayAll() {
        // take data from linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // reload display


    }

    @FXML
    public void displayCompletedTasksButton(ActionEvent actionEvent) {
        // call displayCompleted()


    }

    public String displayCompleted() {
        // make a new linked list
        // run loop adding every item marked as complete into new linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // display the new linked list
        // save all new data into a string and return (for testing)

        return "";
    }

    @FXML
    public void displayIncompleteTasksButton(ActionEvent actionEvent) {
        // call displayIncomplete()


    }

    public String displayIncomplete() {
        // make a new linked list
        // run loop adding every item marked as incomplete into new linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // display the new linked list
        // save all new data into a string and return (for testing)

        return "";
    }

    @FXML
    public void markAsCompleteButton(ActionEvent actionEvent) {
        // call markAsComplete()


    }

    public void markAsComplete() {
        // get item from taskClicked
        // look for that item in the linked list
        // change that index's boolean complete to true


    }

    @FXML
    public void deleteTaskButton(ActionEvent actionEvent) {
        // call deleteTask()


    }

    public void deleteTask() {
        // search for selected task in linked list
        // remove item


    }

    @FXML
    public void addNewTaskButton(ActionEvent actionEvent) {
        // call addNewTask()


    }

    public void addNewTask() {
        // get data from textbox
        // add all data into an ToDoItemobj
        // add obj to linked list
        // update display

    }

    @FXML
    public void editDescription(ActionEvent actionEvent) {
        // call editDescriptionInit()


    }

    public void editDescriptionInit() {
        // look for listClicked in the linked list
        // take that index and change the .description of the obj w/ what's in the text field
        // update display


    }

    @FXML
    public void changeDate(ActionEvent actionEvent) {
        // call changeDateInit()


    }

    public void changeDateInit() {
        // get text from dateTextBox
        // get userSelectedTask
        // check to see if format is correct
        // call modifyDateText()


    }

    public void modifyDateText(int location, String newDate) {
        // run loop to get selected list location in file
        // find the date
        // remove old date
        // append newDate


    }

    @FXML
    public void saveAllToDoButton(ActionEvent actionEvent) {
        // call save()


    }

    public void save() {
        // call saveList from ToDoControllerMenu w/ the currently selected list


    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        // prompt to see if user wants to save
        // - call save()
        // close any open files
        // load ToDoMenu scene when clicked

        SceneController.switchToMenu();
    }

    @FXML
    public void helpTask() throws IOException {

        SceneController.taskHelp();
    }

}
