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

import java.io.*;
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
    public static LinkedList<TaskToDoObj> tasks = new LinkedList<>();

    @FXML
    public void listClicked() {
        // allow for multiple items
        // save 'selected' accordingly

        taskList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void displayAllTasksButton() {
        // call refreshDisplay()

        refreshDisplay();
    }

    @FXML
    public void displayCompletedTasksButton() {
        // remove current display
        // run loop for every available task
        // check to see if the items complete status is true
        // save result of displayCompleted() using a selected task
        // add result to the display

        removeAll();

        for (TaskToDoObj task : tasks) {
            if (task.isComplete()) {
                String data = displayCompleted(task, task.isComplete());
                taskList.getItems().add(data);
            }
        }
    }

    @FXML
    public void displayIncompleteTasksButton() {
        // remove current display
        // run loop for every available task
        // check to see if the items complete status is false
        // save result of displayIncomplete() using a selected task
        // add result to the display

        removeAll();

        for (TaskToDoObj task : tasks) {
            if (!task.isComplete()) {
                String data = displayIncomplete(task, task.isComplete());
                taskList.getItems().add(data);
            }
        }
    }

    @FXML
    public void markAsCompleteButton() {
        // updates selected for every item selected by the user
        // for there is an item selected
        // find the task with same name and description
        // if there is a match call markAsComplete

        selected = taskList.getSelectionModel().getSelectedItems();

        for (String taskSelected : selected) {
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    markAsComplete(taskToInspect);
                }
            }
        }
    }

    @FXML
    public void markAsIncompleteButton() {
        // updates selected for every item selected by the user
        // for there is an item selected
        // find the task with same name and description
        // if there is a match call markAsIncomplete()

        selected = taskList.getSelectionModel().getSelectedItems();

        for (String taskSelected : selected) {
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    markAsIncomplete(taskToInspect);
                }
            }
        }
    }

    @FXML
    public void addNewTaskButton() {
        // gather all the user provided inputs
        // call addNewTask() with data provided

        String title = getTypedTitle();
        String description = getTypedDescription();
        String date = getTypedDate();

        addNewTask(title, description, date);
    }

    @FXML
    public void deleteTaskButton() {
        // update selected to every selected item
        // for there is a string selected
        // call deleteTaskStart() with the selected string

        selected = taskList.getSelectionModel().getSelectedItems();

        try {
            for (String task : selected) {
                deleteTaskStart(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to remove item...");
        }
    }

    @FXML
    public void editDescription() {
        // takes all selected items
        // for every selected task
        // for every task available in the tasks linked list
        // if the task selected matches any task in the list w/ the same name and description
        // edit the description of object
        // refresh display

        selected = taskList.getSelectionModel().getSelectedItems();
        String newDescription = updateTaskTextBox.getText();

        for (String taskSelected : selected) {
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    try {
                        if (!newDescription.equalsIgnoreCase("")) {
                            editDescription(taskToInspect, newDescription);
                        }

                        refreshDisplay();
                    } catch (Exception e) {
                        System.out.println("Failed to edit description.");
                    }
                    break;
                }
            }
            break;
        }
    }

    @FXML
    public void changeDate() {
        // takes all selected items
        // for every selected task
        // for every task available in the tasks linked list
        // if the task selected matches any task in the list w/ the same name and description
        // edit the date of object
        // refresh display

        selected = taskList.getSelectionModel().getSelectedItems();

        String newDate = updateTaskTextBox.getText();

        for (String taskSelected : selected) {
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    try {
                        if (!newDate.equalsIgnoreCase("")) {
                            editDate(taskToInspect, newDate);
                        }

                        refreshDisplay();
                    } catch (Exception e) {
                        System.out.println("Failed to edit description.");
                    }
                    break;
                }
            }
            break;
        }
    }

    @FXML
    public void saveAllToDoButton() throws IOException {
        // finds the correct index to store the file
        // saves result of save() using tasks list
        // opens save popup to display name of saved file and the path
        // break to stop loop (index to save was already found at this point)

        for (int i = 1; ; i++) {
            if (!new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt").isFile()) {
                File newFile = new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt");
                FileWriter fw = new FileWriter(newFile);
                String output = FileHandler.saveTaskData(tasks);

                fw.write(output);
                fw.close();

                SceneController.savePopUp("save_" + i + ".txt");

                break;
            }
        }

    }

    @FXML
    public void goBack() throws IOException {
        // call the menu scene

        SceneController.switchToMenu();
    }

    @FXML
    public void helpTask() throws IOException {
        // call help screen

        SceneController.taskHelp();
    }

    @FXML
    public void refreshButton() {
        // save the result of tasks() into a linked list
        // refresh display

        tasks = FileHandler.tasks();

        refreshDisplay();
    }

    @FXML
    public void clearAll() throws IOException {
        // calls writeHeader() to retain list title
        // removes all items from linked list
        // updates screen

        FileHandler.writeHeader();

        clearList(tasks);

        removeAll();
    }

    public void removeAll() {
        // while there is an item in the display
        // remove it

        while (!taskList.getItems().isEmpty()) {
            try {
                taskList.getItems().remove(0);
            } catch (Exception e) {
                System.out.println("Failed to remove items from display.");
            }
        }
    }

    public void displayAll() {
        // while there is a task to display
        // display it

        for (TaskToDoObj task : tasks) {
            String data = displayTask(task);
            taskList.getItems().add(data);
        }
    }

    public static String displayTask(TaskToDoObj task) {
        // return the result of generalDisplaySetup using the provided task

        try {
            return generalDisplaySetup(task);
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }

        return "";
    }

    public static String displayCompleted(TaskToDoObj task, boolean status) {
        // if the item given has a status of true
        // return the result of generalDisplaySetup using the task provided

        if (status) {
            try {
                return generalDisplaySetup(task);
            } catch (Exception e) {
                System.out.println("Failed to display all items.");
            }
        }

        return "";
    }

    public static String displayIncomplete(TaskToDoObj task, boolean status) {
        // if the item given has a status of false
        // return the result of generalDisplaySetup using the task provided

        if (!status) {
            try {
                return generalDisplaySetup(task);
            } catch (Exception e) {
                System.out.println("Failed to display all items.");
            }
        }

        return "";
    }

    public static void markAsComplete(TaskToDoObj item) {
        // take a TaskToDo object
        // make its complete status to true

        item.setComplete(true);
    }

    public static void markAsIncomplete(TaskToDoObj item) {
        // take a TaskToDo object
        // make its complete status to false

        item.setComplete(false);
    }

    public void addNewTask(String title, String description, String date) {
        // if any text field is missing data stop and wait for correct data
        // else if all the data provided has something available
        // - check to make sure if the provided date is in the right format and the description is <= 256
        // - - if it is call generateNewTask
        // - - - save result to a string
        // - - - display result
        // - - otherwise ask for new date input

        if (title.equalsIgnoreCase("") || date.equalsIgnoreCase("") || description.equalsIgnoreCase("")) {
            System.out.println("Missing information in text field.");
        } else {
            if (CastedUtilityGeneral.checkDateFormat(date) && description.length() <= 256) {
                String item = generateNewTask(tasks, title, description, date);
                taskList.getItems().add(item);
            } else {
                System.out.println("Please, re-enter the data.");
            }
        }
    }

    public static String generateNewTask(LinkedList<TaskToDoObj> list, String title, String description, String date) {
        // takes in a list of items
        // try to make a new object using the other provided data
        // add the new item to the list
        // return the result of generalDisplaySetup using the new item

        try {
            TaskToDoObj item = new TaskToDoObj(title, date, description, false);
            list.add(item);
            return generalDisplaySetup(item);
        } catch (Exception e) {
            System.out.println("Error displaying new item.");
        }
        return "";
    }

    public void deleteTaskStart(String taskToDelete) {
        // for loop set to total task size
        // find the item w/ the same name and description inside of the collection of tasks
        // when the item is found call renewFile(tasks matched item)
        // remove the tasks from the linked list
        // remove the task from the display

        for (int i = 0; i < tasks.size(); i++) {
            if (taskToDelete.contains(tasks.get(i).getName()) && taskToDelete.contains(tasks.get(i).getDescription())) {
                try {
                    FileHandler.renewFileAfterDelete(tasks.get(i));

                    deleteTask(tasks, i);
                    taskList.getItems().remove(taskToDelete);
                } catch (Exception e) {
                    System.out.println("Failed to remove item.");
                }
            }
        }
    }

    public static void deleteTask(LinkedList<TaskToDoObj> list, int i) {
        // removes a specific task from a provided list of items

        list.remove(i);
    }

    public static boolean editDescription(TaskToDoObj item, String newDescription) {
        // take in an item and a new description name
        // if the provided description is <= 256
        // change description and return true

        try {
            if (newDescription.length() <= 256) {
                item.setDescription(newDescription);
                return true;
            } else {
                System.out.println("Item description is too long!");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the description.");
        }

        return false;
    }

    public static boolean editDate(TaskToDoObj item, String newDate) {
        // take in an item and a new date
        // if the provided date fits the required format
        // change date and return true

        try {
            if (CastedUtilityGeneral.checkDateFormat(newDate)) {
                item.setDate(newDate);
                return true;
            } else {
                System.out.println("Date is not in the correct format.");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the date.");
        }

        return false;
    }

    public static void clearList(LinkedList<TaskToDoObj> list) {
        // takes in a linked list of items
        // while the list is not empty
        // remove an item

        while (!list.isEmpty()) {
            list.remove();
        }
    }

    // TODO FIX ALIGNMENT ISSUES (Note: console display is fine, listview display is off for some reason???)
    public static String generalDisplaySetup(TaskToDoObj task) {
        // add name description and date with appropriate spacing and adds the string into the view

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

        return items.toString();
    }

    public String getTypedTitle() {
        // get data from title task text box and returns the string

        return titleTaskTextBox.getText();
    }

    public String getTypedDate() {
        // get data from date task text box and returns the string

        return dateTextBox.getText();
    }

    public String getTypedDescription() {
        // get data from description task text box and returns the string

        return descriptionTaskTextBox.getText();
    }

    public void refreshDisplay(){
        // removes everything from display
        // displays everything

        removeAll();
        displayAll();
    }

}
