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
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ListToDoController {

    public SplitPane mainPane;
    public TextField titleToDoTextBox;
    public SplitPane splitPane;
    public ListView<ListToDoObj> todoList;
    public static ListToDoObj pass;
    public static ListToDoObj temp;
    public ObservableList<ListToDoObj> selected;

    LinkedList<ListToDoObj> allItems = new LinkedList<>();

    @FXML
    public void addNewToDoButton() throws IOException {
        // call ToDoList.addItem() w/ data from txtbox

        String name = titleToDoTextBox.getText();

        addItem(name);
    }

    public void addItem(String name) throws IOException {
        // take the new item data

        ListToDoObj item = new ListToDoObj(name);
        todoList.getItems().add(item);

        allItems.add(item);

        storeData(item);

        System.out.println("Added: " + item);
    }

    @FXML
    public void deleteToDoButton() {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list

        selected = todoList.getSelectionModel().getSelectedItems();

        deleteItem(selected);
    }

    public void deleteItem(ObservableList<ListToDoObj> selected) {
        // run loop to find item w/ the same name
        // remove the item
        // decrease total amount

        for (ListToDoObj i : selected) {
            pass = i;

            removeFile(pass.getIndex());

            if (pass.getIndex() != allItems.size()) {
                updateIndex(pass.getIndex());
                pass.setIndex(pass.getIndex() - 1);
            }

            allItems.remove(pass.getIndex() - 1);
            todoList.getItems().remove(pass.getIndex() - 1);
        }

    }

    public void removeFile(int txt) {
        String path = CastedUtilityGeneral.tempDirec();

        String newPath = path + "\\list_" + txt + ".txt";

        File index = new File(newPath);

        if (index.delete()) {
            System.out.println("Removed list_" + txt + ".txt successfully.");
        }
    }

    @FXML
    public void editNameToDoButton() throws IOException {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox

        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj item : selected) {

            editName(item);
        }
    }

    public void editName(ListToDoObj item) throws IOException {
        // search for the selected list in the LinkedList
        // change the name w/ string from newName

        temp = item;

        storeData(temp);
        temp.setName(titleToDoTextBox.getText());
        todoList.getItems().add(temp);
        todoList.getItems().remove(item);
    }

    @FXML
    public void listClicked() {
        // allow for multiple items
        // save selected accordingly

        // code idea?
        todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void selectToDoButton() throws IOException {
        // check to see if the selected list is not empty
        // open new scene for ToDofxml w/ data from string selected list
        // close old scene

        selected = todoList.getSelectionModel().getSelectedItems();

        selectToDo();
        SceneController.switchToTask();

    }

    public void selectToDo() {

        for (ListToDoObj i : selected) {
            pass = i;

            File newFile = new File(CastedUtilityGeneral.tempDirec() + "\\selected.txt");
            File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + pass.getIndex() + ".txt");

            try {
                Files.copy(oldFile.toPath(), newFile.toPath());
            } catch (Exception e) {
                System.out.println("Failed to update index.");
            }

            break;
        }

    }

    @FXML
    public void saveList() {
        // create an observable list with all selected items
        // run a loop and store each list carefully into a txt file

        selected = todoList.getSelectionModel().getSelectedItems();

        saveSelectedList();
    }

    public void saveSelectedList() {
        // code idea?
        // String debug = "";
        // ObservableList<ToDoList> selectedToDos;
        // selectedToDos = todoList.getSelectionModel().getSelectedItems();

        // for(ToDoList x : selectedToDos){
        //     System.out.println(selectedToDos);
        // }

        for (ListToDoObj item : selected) {

            for (int i = 1; ; i++) {
                if (!new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt").isFile()) {

                    System.out.println(item.getIndex());

                    File newFile = new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt");
                    File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + item.getIndex() + ".txt");

                    try {
                        Files.copy(oldFile.toPath(), newFile.toPath());
                    } catch (Exception e) {
                        System.out.println("Failed to copy files.");
                    }

                    SceneController.savePopUp("save_" + item.getIndex() + ".txt");

                    break;
                }
            }
        }
    }

    @FXML
    public void loadList() throws IOException {
        // take a text file w/ a setup of allowing multiple inputs
        //  FileChooser
        //  set initial direc
        //  get all extension filters
        //  make a List<File> of selected files (show open multiple dialgoue)
        //  get items and add
        // load each element into the list view and store into the object list

        loadListFile();
    }

    public void loadListFile() throws IOException {

        String path = System.getProperty("user.dir");

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path + "\\ToDo_Files"));

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File: ", "*.txt"));

        List<File> selectedFile = fc.showOpenMultipleDialog(null);
        for (File file : selectedFile) {
            formatFile(file);
        }

    }

    public void formatFile(File selectedFile) throws IOException {

        Scanner file = new Scanner(selectedFile);

        String name = file.nextLine();

        addItem(name);
        int index = allItems.size();

        FileWriter fw = new FileWriter(CastedUtilityGeneral.tempDirec() + "\\list_" + index + ".txt");
        fw.write(name + "\n");
        while (file.hasNextLine()) {
            String temp = file.nextLine();
            fw.write(temp + "\n");
        }
        fw.close();

        allItems.get(index - 1).setIndex(index);

    }

    public void storeData(ListToDoObj item) throws IOException {
        CastedUtilityGeneral.createTempFolder();

        if (item.getIndex() == -1) {

            int index = CastedUtilityGeneral.indexer();

            FileWriter todoWrite = new FileWriter(CastedUtilityGeneral.tempDirec() + "\\list_" + index + ".txt");

            todoWrite.write(item + "\n");

            todoWrite.close();

            item.setIndex(index);

        } else {

            int index = item.getIndex();
            StringBuilder data = new StringBuilder();

            File editedDataFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + index + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(editedDataFile));

            reader.readLine();

            String newName = titleToDoTextBox.getText();
            data.append(newName).append("\n");

            String line = reader.readLine();
            while (line != null) {
                data.append(line).append("\n");
                line = reader.readLine();
            }

            FileWriter writer = new FileWriter(editedDataFile);
            writer.write(data.toString());

            reader.close();
            writer.close();

            item.setIndex(index);

        }
    }

    public void updateIndex(int index) {
        // loop
        // set temp index = i + 1
        // take temp index data put it into index file

        for (int i = index; i < allItems.size(); i++) {

            File newFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + (i + 1) + ".txt");
            File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + (i) + ".txt");

            try {
                if (newFile.renameTo(oldFile)) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("FAILED TO UPDATE INDEX");
            }

        }
    }

    @FXML
    public void helpMenu() throws IOException {

        SceneController.menuHelp();
    }

}


