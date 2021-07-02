/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.event.ActionEvent;
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
    // create string for taskClicked
    // create linked list of ToDoItem objects
    // define a global variable for the user directory

    public void specificUserDirec(String userDirec) {
        // modify userDirec so that it's in a specific file path [i.e. To-Do saves]


    }

    public void listClicked(MouseEvent mouseEvent) {
        // change string taskClicked to list's data


    }

    public void displayAllTasksButton(ActionEvent actionEvent) {
        // call displayAll()


    }

    public void displayAll() {
        // display data from linked list


    }

    public void displayCompletedTasksButton(ActionEvent actionEvent) {
        // call displayCompleted()


    }

    public void displayCompleted() {
        // make a new linked list
        // run loop adding every item marked as complete into new linked list
        // display linked list


    }

    public void displayIncompleteTasksButton(ActionEvent actionEvent) {
        // call displayIncomplete()


    }

    public void displayIncomplete() {
        // make a new linked list
        // run loop adding every item marked as incomplete into new linked list
        // display linked list


    }

    public void markAsCompleteButton(ActionEvent actionEvent) {
        // call markAsComplete()


    }

    public void markAsComplete() {
        // get item from taskClicked
        // look for that item in the linked list
        // change that index's boolean complete to true


    }

    public void deleteTaskButton(ActionEvent actionEvent) {
        // call deleteTask()


    }

    public void deleteTask() {
        // search for selected task in linked list
        // remove item


    }

    public void addNewTaskButton(ActionEvent actionEvent) {
        // call addNewTask()


    }

    public void addNewTask() {
        // get data from textbox
        // add all data into an ToDoItemobj
        // add obj to linked list
        // update display

    }

    public void editDescription(ActionEvent actionEvent) {
        // call editDescriptionInit()


    }

    public void editDescriptionInit(){
        // look for listClicked in the linked list
        // take that index and change the .description of the obj w/ what's in the text field
        // update display


    }

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

    public void saveAllToDoButton(ActionEvent actionEvent) {
        // call save()


    }

    public void goBack(ActionEvent actionEvent) {
        // prompt to see if user wants to save
        // - call save()
        // close any open files
        // load ToDoMenu scene when clicked


    }

    public void save() {
        // create new txt
        // open txt
        // call getUserData()
        // save all user specified data into a txt
        //close saved txt


    }

    public StringBuilder getUserData() {
        // get current todo list
        // get every single task in the todo list
        // create one large stringBuilder and return

        return new StringBuilder();// temp so there's no crash
    }

}
