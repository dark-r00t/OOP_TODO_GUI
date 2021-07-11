/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class TaskToDoController {
    public SplitPane mainPane;
    public SplitPane splitPane;
    public TextField descriptionTaskTextBox;
    public TextField dateTextBox;
    public TextField titleTaskTextBox;
    public TextField updateTaskTextBox;
    public ListView<String> taskList;
    public ObservableList<String> selected;
    public static LinkedList<TaskToDoObj> tasks;

    @FXML
    public void listClicked() {
        // allow for multiple items
        // save 'selected' accordingly

        taskList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(taskList);
    }

    @FXML
    public void displayAllTasksButton() {
        // call displayAll()

        removeAll();
        displayAll();
    }

    public void displayAll() {
        // take data from linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // reload display

        try {
            for (TaskToDoObj task : tasks) {
                generalDisplaySetup(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }
        System.out.println("Displayed all items in list.");
    }

    @FXML
    public void displayCompletedTasksButton() {
        // call displayCompleted()

        removeAll();
        displayCompleted();
    }

    public void displayCompleted() {
        // make a new linked list
        // run loop adding every item marked as complete into new linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // display the new linked list
        // save all new data into a string and return (for testing)

        try {
            for (TaskToDoObj task : tasks) {

                if (task.isComplete()) {
                    generalDisplaySetup(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }
        System.out.println("Displayed all of the completed items in list.");
    }

    @FXML
    public void displayIncompleteTasksButton() {
        // call displayIncomplete()

        removeAll();
        displayIncomplete();
    }

    public void displayIncomplete() {
        // make a new linked list
        // run loop adding every item marked as incomplete into new linked list
        // modify for linked list display compatibility and general reformatting
        // take all data and place into an output string (for testing)
        // display the new linked list
        // save all new data into a string and return (for testing)

        try {
            for (TaskToDoObj task : tasks) {

                if (!task.isComplete()) {
                    generalDisplaySetup(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }
        System.out.println("Displayed all of the incompleted items in list.");
    }

    @FXML
    public void markAsCompleteButton() {
        // call markAsComplete()

        selected = taskList.getSelectionModel().getSelectedItems();


        for (int i = 0; i < selected.size(); i++) {

            String task = selected.get(i);
            if (task.contains(tasks.get(i).getName())) {
                markAsComplete(tasks.get(i));
            }
        }
    }

    public void markAsComplete(TaskToDoObj item) {
        // get item from taskClicked
        // look for that item in the linked list
        // change that index's boolean complete to true

        item.setComplete(true);
    }

    @FXML
    public void markAsIncompleteButton() {

        selected = taskList.getSelectionModel().getSelectedItems();


        for (int i = 0; i < selected.size(); i++) {

            String task = selected.get(i);
            if (task.contains(tasks.get(i).getName())) {
                markAsIncomplete(tasks.get(i));
            }
        }
    }

    public void markAsIncomplete(TaskToDoObj item) {
        // get item from taskClicked
        // look for that item in the linked list
        // change that index's boolean complete to true

        item.setComplete(false);
    }

    @FXML
    public void deleteTaskButton() {
        // call deleteTask()

        selected = taskList.getSelectionModel().getSelectedItems();

        try {
            for (String task : selected) {

                deleteTask(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to remove item...");
        }
    }

    public void deleteTask(String task) {
        // search for selected task in linked list
        // remove item

        for (int i = 0; i < tasks.size(); i++) {
            if (task.contains(tasks.get(i).getName())) {
                try {
                    FileHandler.renewFileAfterDelete(tasks.get(i));

                    tasks.remove(i);
                    taskList.getItems().remove(task);

                    System.out.println("Item " + i + " was removed.");
                } catch (Exception e) {
                    System.out.println("Failed to remove item.");
                }
            }
        }
    }

    @FXML
    public void addNewTaskButton() {
        // call addNewTask()

        addNewTask();
    }

    public void addNewTask() {
        // get data from textbox
        // add all data into an ToDoItemobj
        // add obj to linked list
        // update display

        String title = getTypedTitle();
        String description = getTypedDescription();
        String date = getTypedDate();

        if (title.equalsIgnoreCase("") || date.equalsIgnoreCase("") || description.equalsIgnoreCase("")) {
            System.out.println("Missing information in text field.");
        } else {
            generateNewTask(title, description, date);
        }
    }

    public void generateNewTask(String title, String description, String date) {

        try {
            TaskToDoObj item = new TaskToDoObj(title, description, date, false);
            tasks.add(item);
            generalDisplaySetup(item);
        } catch (Exception e) {
            System.out.println("Failed to create a new task.");
        }
    }

    //TODO ADD
    @FXML
    public void editDescription() {
        // call editDescriptionInit()


    }

    public void editDescriptionInit() {
        // look for listClicked in the linked list
        // take that index and change the .description of the obj w/ what's in the text field
        // update display


    }

    //TODO ADD
    @FXML
    public void changeDate() {
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
    public void saveAllToDoButton() throws IOException {
        // call save()

        selected = taskList.getSelectionModel().getSelectedItems();

        save();
    }

    public void save() {
        // call saveList from ToDoControllerMenu w/ the currently selected list

        for (int i = 1; ; i++) {

            System.out.println(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt");
            if (!new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt").isFile()) {

                File newFile = new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt");
                File oldFile = new File(UtilityGeneral.tempDirec() + "\\selected.txt");

                try {
                    Files.copy(oldFile.toPath(), newFile.toPath());
                    System.out.println("File copied.");
                } catch (Exception e) {
                    System.out.println("Failed to copy files.");
                }

                SceneController.savePopUp("save_" + i + ".txt");

                break;
            }
        }
    }

    @FXML
    public void goBack() throws IOException {
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

    @FXML
    public void refreshButton() throws FileNotFoundException {

        tasks = FileHandler.tasks();
        removeAll();
        displayAll();
    }

    @FXML
    public void clearAll() throws IOException {

        FileHandler.writeHeader();
        removeAll();
    }

    public void removeAll() {

        while (!taskList.getItems().isEmpty()) {
            try {
                taskList.getItems().remove(0);
            } catch (Exception e) {
                System.out.println("Failed to remove items from display.");
            }
        }
    }

    //TODO FIX ALIGNMENT ISSUES
    public void generalDisplaySetup(TaskToDoObj task) {
        StringBuilder items = new StringBuilder();

        items.append(task.getName());
        while (items.toString().length() != 50 + 10) {
            items.append(" ");
        }

        items.append(task.getDescription());
        while (items.toString().length() != 60 + 119) {
            items.append(" ");
        }

        items.append((task.getDate()));

        taskList.getItems().add(items.toString());
        System.out.println(items);
    }

    public String getTypedTitle() {
        return titleTaskTextBox.getText();
    }

    public String getTypedDate() {
        return dateTextBox.getText();
    }

    public String getTypedDescription() {
        return descriptionTaskTextBox.getText();
    }

}
