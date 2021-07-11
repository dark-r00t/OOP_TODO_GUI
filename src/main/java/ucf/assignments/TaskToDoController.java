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
import java.io.FileWriter;
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
    }

    @FXML
    public void displayAllTasksButton() {
        // remove current display
        // call displayAll()

        removeAll();
        displayAll();
    }

    public void displayAll() {
        // for every available task call generalDisplaySetup()

        try {
            for (TaskToDoObj task : tasks) {
                generalDisplaySetup(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }
    }

    @FXML
    public void displayCompletedTasksButton() {
        // remove current display
        // call displayCompleted() for every completed task

        removeAll();

        for (TaskToDoObj task : tasks) {
            if (task.isComplete()) {
                displayCompleted(task);
            }
        }
    }

    public void displayCompleted(TaskToDoObj task) {
        // attempt to display task input

        try {
            generalDisplaySetup(task);
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }
    }

    @FXML
    public void displayIncompleteTasksButton() {
        // remove current display
        // call displayIncomplete() for every uncompleted task

        removeAll();

        for (TaskToDoObj task : tasks) {
            if (!task.isComplete()) {
                displayIncomplete(task);
            }
        }
    }

    public void displayIncomplete(TaskToDoObj task) {
        // attempt to display task input

        try {
            generalDisplaySetup(task);
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
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

    public void markAsComplete(TaskToDoObj item) {
        // take a TaskToDo object
        // check its complete status to true

        item.setComplete(true);
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

    public void markAsIncomplete(TaskToDoObj item) {
        // take a TaskToDo object
        // check its complete status to false

        item.setComplete(false);
    }

    // TODO here
    @FXML
    public void deleteTaskButton() {
        // update selected to every selected item
        // for there is a string selected
        // call deleteTask() with the selected string

        selected = taskList.getSelectionModel().getSelectedItems();

        try {
            for (String task : selected) {
                deleteTask(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to remove item...");
        }
    }

    public void deleteTask(String taskToDelete) {
        // for loop set to total task size
        // find the item w/ the same name and description inside of the collection of tasks
        // when the item is found call renewFile(tasks matched item)
        // remove the tasks from the linked list
        // remove the task from the display

        for (int i = 0; i < tasks.size(); i++) {
            if (taskToDelete.contains(tasks.get(i).getName()) && taskToDelete.contains(tasks.get(i).getDescription())) {
                try {
                    FileHandler.renewFileAfterDelete(tasks.get(i));

                    tasks.remove(i);
                    taskList.getItems().remove(taskToDelete);
                } catch (Exception e) {
                    System.out.println("Failed to remove item.");
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

    public void addNewTask(String title, String description, String date) {
        // if any text field is missing data stop and wait for correct data
        // else if all the data provided has something available
        // - check to make sure if the provided date is in the right format and the description is <= 256
        // - - if it is call generateNewTask
        // - - otherwise ask for new date input

        if (title.equalsIgnoreCase("") || date.equalsIgnoreCase("") || description.equalsIgnoreCase("")) {
            System.out.println("Missing information in text field.");
        } else {
            if (CastedUtilityGeneral.checkDateFormat(date) && description.length() <= 256) {
                generateNewTask(title, description, date, false);
            } else {
                System.out.println("Please, re-enter the data.");
            }
        }
    }

    public void generateNewTask(String title, String description, String date, boolean complete) {
        // take all necessary data
        // try to create a new TaskToDoObj with data
        // display the new item using generalDisplaySetup()

        try {
            TaskToDoObj item = new TaskToDoObj(title, date, description, complete);
            tasks.add(item);
            generalDisplaySetup(item);
        } catch (Exception e) {
            System.out.println("Failed to create a new task.");
        }
    }

    @FXML
    public void editDescription() {
        // takes all selected items
        // for every selected task
        // for every task available in the tasks linked list
        // if the task selected matches any task in the list w/ the same name and description
        // edit the description of object
        // refresh display w/ removeAll() displayALL()

        selected = taskList.getSelectionModel().getSelectedItems();

        for (String taskSelected : selected) {
            int i = 0;
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    try {
                        editDescription(taskToInspect);
                        // tasks.remove(i);
                        removeAll();
                        displayAll();
                    } catch (Exception e) {
                        System.out.println("Failed to edit description.");
                    }
                    break;
                }
                i++;
            }
            break;
        }
    }

    public void editDescription(TaskToDoObj item) {
        // take in an item
        // change the description with what's in the text field
        // - only if the provided description is <= 256

        String newDescription = updateTaskTextBox.getText();

        try {
            if (newDescription.length() <= 256) {
                item.setDescription(newDescription);
            } else {
                System.out.println("Item description is too long!");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the description.");
        }
    }

    @FXML
    public void changeDate() {
        // takes all selected items
        // for every selected task
        // for every task available in the tasks linked list
        // if the task selected matches any task in the list w/ the same name and description
        // edit the date of object
        // refresh display w/ removeAll() displayALL()

        selected = taskList.getSelectionModel().getSelectedItems();

        for (String taskSelected : selected) {
            int i = 0;
            for (TaskToDoObj taskToInspect : tasks) {
                if (taskSelected.contains(taskToInspect.getName()) && taskSelected.contains(taskToInspect.getDescription())) {
                    try {
                        changeDate(taskToInspect);
                        removeAll();
                        displayAll();
                    } catch (Exception e) {
                        System.out.println("Failed to edit description.");
                    }
                    break;
                }
                i++;
            }
            break;
        }
    }

    public void changeDate(TaskToDoObj item) {
        // take in an item
        // change the date with what's in the text field
        // - only if the provided date is in the correct format

        String newDate = updateTaskTextBox.getText();

        try {
            if (CastedUtilityGeneral.checkDateFormat(newDate)) {
                item.setDate(newDate);
            } else {
                System.out.println("Date is not in the correct format.");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the description.");
        }
    }

    @FXML
    public void saveAllToDoButton() {
        // finds the correct index to store the file
        // calls save() with found index
        // opens save popup to display name of saved file and the path
        // break to stop loop (index to save was already found at this point)

        selected = taskList.getSelectionModel().getSelectedItems();

        for (int i = 1; ; i++) {
            if (!new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt").isFile()) {
                save(i);

                SceneController.savePopUp("save_" + i + ".txt");

                break;
            }
        }

    }

    public void save(int i) {

        File newFile = new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt");

        try {
            StringBuilder content = new StringBuilder();

            for (TaskToDoObj task : tasks) {
                content.append(task.getName()).append("\n").append(task.getDate()).append("\n").append(task.getDescription()).append("\n").append(task.isComplete()).append("\n");
            }

            String output = content.toString();

            while (output.contains("\n\n")) {
                output = output.replace("\n\n", "\n");
            }

            output = output.substring(0, output.length() - 1);

            FileWriter fw = new FileWriter(newFile);
            fw.write(output);
            fw.close();

        } catch (IOException e) {
            System.out.println("Failed to add additional tasks to the save file.");
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
    public void refreshButton() {

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

        // System.out.println(items);
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
