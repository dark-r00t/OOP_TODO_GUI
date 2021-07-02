package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ToDoController {
    public SplitPane mainPane;
    public SplitPane splitPane;
    public TextField descriptionTaskTextBox;
    public DatePicker dataTextBox;
    public TextField titleTaskTextBox;
    public TextField updateTaskTextBox;
    public ListView taskList;

    // on open delete data in temp.txt

    // create string for listClicked
    // create a string array list of size 100

    // define a global variable for the user directory
    public void specificUserDirec(String userDirec) {
        // modify userDirec so that it's in a specific file path [i.e. To-Do saves]


    }

    public void listClicked(MouseEvent mouseEvent) {
        // change string ListClicked to list's data


    }

    public void displayAllTasksButton(ActionEvent actionEvent) {
        // call displayAll()


    }

    public void displayAll() {
        // open temp.txt
        // get data from temp.txt and store into list
        // close temp.txt
        // display list


    }

    public void displayCompletedTasksButton(ActionEvent actionEvent) {
        // call displayCompleted()


    }

    public void displayCompleted() {
        // get data from temp.txt
        // look for -#&^C at the end of the string
        // store data into the string array
        // display string array


    }

    public void displayIncompleteTasksButton(ActionEvent actionEvent) {
        // call displayIncomplete()


    }

    public void displayIncomplete() {
        // get data from temp.txt
        // look for data w/o -#&^C at the end of the string
        // store data into the string array
        // display string array


    }

    public void markAsCompleteButton(ActionEvent actionEvent) {
        // call markAsComplete()


    }

    public void markAsComplete() {
        // get listClicked data
        // convert it into index where it's stored in the string array
        // append -#&^C to the very end of the string
        // -so it's never displayed
        // display a greenBubble on the list item


    }

    public void deleteTaskButton(ActionEvent actionEvent) {
        // call deleteTask()


    }

    public void deleteTask() {
        // open temp.txt
        // search temp.txt for data that matches selected list
        // remove line


    }

    public void addNewTaskButton(ActionEvent actionEvent) {
        // call addNewTask()


    }

    public void addNewTask() {
        // open temp.txt
        // create new StringBuilder
        // append data from titleTaskTextBox
        // append data from dateTextBox
        // append data from descriptionTaskTextBox
        // place data into temp.txt + \n


    }

    public void editDescription(ActionEvent actionEvent) {
        // call editDescriptionInit()


    }

    //*********************************
    public void editDescriptionInit(){
        //


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

// TODO figure out how list view works and handles data

/*
 *
 * perhaps string builder w/ size 200
 * even index and zero contain marked complete data (y,n)
 *
 * */