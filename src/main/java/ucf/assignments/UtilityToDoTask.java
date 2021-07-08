/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

public class UtilityToDoTask {

    public StringBuilder getUserData() {
        // get current todoList
        // get every single task in the todoList
        // create one large stringBuilder and return

        return new StringBuilder();// temp so there's no crash
    }

    public String displayAll() {
        // take data from linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // reload display

        return "";
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

    public String displayIncomplete() {
        // make a new linked list
        // run loop adding every item marked as incomplete into new linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // display the new linked list
        // save all new data into a string and return (for testing)

        return "";
    }

    public void markAsComplete() {
        // get item from taskClicked
        // look for that item in the linked list
        // change that index's boolean complete to true


    }

    public void deleteTask() {
        // search for selected task in linked list
        // remove item


    }

    public void addNewTask() {
        // get data from textbox
        // add all data into an ToDoItemobj
        // add obj to linked list
        // update display

    }

    public void editDescriptionInit() {
        // look for listClicked in the linked list
        // take that index and change the .description of the obj w/ what's in the text field
        // update display


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

    public void save() {
        // call saveList from ToDoControllerMenu w/ the currently selected list


    }
}
