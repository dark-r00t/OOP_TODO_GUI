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
    public void deleteToDoButton() throws IOException {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list

        selected = todoList.getSelectionModel().getSelectedItems();

        deleteItem(selected);
    }

    public void deleteItem(ObservableList<ListToDoObj> selected) throws IOException {
        // run loop to find item w/ the same name
        // remove the item
        // decrease total amount

        for (ListToDoObj i : selected) {
            pass = i;
        }

        removeFile(pass.getIndex());
        updateIndex(pass.getIndex());

        allItems.remove(pass.getIndex() - 1);

        todoList.getItems().remove(pass.getIndex() - 1);

        removeFile(allItems.size() + 1);
    }

    public void removeFile(int txt) {
        String path = UtilityGeneral.userDirec();

        String newPath = path + "\\list_" + txt + ".txt";

        File index = new File(newPath);

        if (index.delete()) {
            System.out.println("Removed list_" + txt + ".txt successfully.");
        }
    }

    //TODO FIX
    @FXML
    public void editNameToDoButton() throws IOException {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox

        String name = titleToDoTextBox.getText();

        editName(name);
    }

    public void editName(String name) throws IOException {
        // search for the selected list in the LinkedList
        // change the name w/ string from newName

        ObservableList<ListToDoObj> selected;
        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj i : selected) {
            pass = i;
            pass.setName(name);
            storeData(pass);
        }
    }

    @FXML
    public void listClicked() {
        // allow for multiple items
        // save selected accordingly

        // code idea?
        todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(todoList);
    }

    //TODO ADD
    @FXML
    public void selectToDoButton() {
        // check to see if the selected list is not empty
        // open new scene for ToDofxml w/ data from string selected list
        // close old scene


    }

    public void selectToDo(){


    }

    //TODO ADD
    @FXML
    public void saveList() {
        // create an observable list with all selected items
        // run a loop and store each list carefully into a txt file

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

        todoList.getSelectionModel().getSelectedItems();
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
        int index = allItems.size() + 1;

        addItem(name);

        FileWriter fw = new FileWriter(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");
        fw.write(name + "\n");
        while (file.hasNextLine()) {
            String temp = file.nextLine();
            fw.write(temp + "\n");
        }
        fw.close();

    }

    public void storeData(ListToDoObj item) throws IOException {
        UtilityGeneral.createTempFolder();

        if (item.getIndex() == -1) {
            int index = UtilityGeneral.indexer();

            FileWriter todoWrite = new FileWriter(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");

            //todoWrite.write("&NTDL\n");
            todoWrite.write(item + "\n");

            todoWrite.close();

            item.setIndex(index);

        } else {

            int index = item.getIndex();
            StringBuilder data = new StringBuilder();

            File editedDataFile = new File(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(editedDataFile));

            reader.readLine();
            reader.readLine();

            String newName = titleToDoTextBox.getText();
            data.append(newName).append("\n").append(index);

            String line = reader.readLine();
            while (line != null) {
                data.append(line);
                line = reader.readLine();
            }

            FileWriter writer = new FileWriter(editedDataFile);
            writer.write(data.toString());

            reader.close();
            writer.close();

        }
    }

    public void updateIndex(int index) throws IOException {
        // loop
        // set temp index = i + 1
        // take temp index data put it into index file

        for (int i = index; i < allItems.size(); i++) {

            if(i + 1 == allItems.size()){
                break;
            }

            File newFile = new File(UtilityGeneral.userDirec() + "\\list_" + (i + 1) + ".txt");
            File oldFile = new File(UtilityGeneral.userDirec() + "\\list_" + (i) + ".txt");

            try {
                newFile.renameTo(oldFile);
            } catch (Exception e) {
                System.out.println("FAILED TO UPDATE INDEX");
            }

        }
    }



}


