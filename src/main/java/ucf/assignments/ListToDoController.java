/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import jdk.jshell.execution.Util;

import java.io.*;
import java.util.*;

public class ListToDoController {
    public SplitPane mainPane;
    public TextField titleToDoTextBox;
    public SplitPane splitPane;
    public ListView<ListToDoObj> todoList;
    public static ListToDoObj pass;
    public ObservableList<ListToDoObj> selected;
    public ObservableList<ListToDoObj> view;

    ArrayList<ListToDoObj> allItems = new ArrayList();

    @FXML
    public void addNewToDoButton(ActionEvent actionEvent) throws IOException {
        // call ToDoList.addItem() w/ data from txtbox

        String name = titleToDoTextBox.getText();

        addItem(name);
    }

    @FXML
    public void deleteToDoButton(ActionEvent actionEvent) throws IOException {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list

        selected = todoList.getSelectionModel().getSelectedItems();

        deleteItem(selected);
    }

    @FXML
    public void editNameToDoButton(ActionEvent actionEvent) throws IOException {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox

        String name = titleToDoTextBox.getText();

        editName(name);
    }

    @FXML
    public void listClicked(MouseEvent mouseEvent) {
        // allow for multiple items
        // save selected accordingly

        // code idea?
        todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(todoList);
    }

    @FXML
    public void selectToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // open new scene for ToDofxml w/ data from string selected list
        // close old scene


    }

    @FXML
    public void saveList(ActionEvent actionEvent) {
        // create an observable list with all selected items
        // run a loop and store each list carefully into a txt file

        // code idea?
        // String debug = "";
        // ObservableList<ToDoList> selectedToDos;
        // selectedToDos = todoList.getSelectionModel().getSelectedItems();

        // for(ToDoList x : selectedToDos){
        //     System.out.println(selectedToDos);
        // }
    }

    @FXML
    public void loadList(ActionEvent actionEvent) throws IOException {
        // take a text file w/ a setup of allowing multiple inputs
        //  FileChooser
        //  set initial direc
        //  get all extension filters
        //  make a List<File> of selected files (show open multiple dialgoue)
        //  get items and add
        // load each element into the list view and store into the object list

        loadListFile();
    }

    public void addItem(String name) throws IOException {
        // take the new item data

        ListToDoObj item = new ListToDoObj(name);
        todoList.getItems().add(item);

        allItems.add(item);

        storeData(item);

        System.out.println("Added: " + item);
    }

    public void deleteItem(ObservableList<ListToDoObj> selected) throws IOException {
        // run loop to find item w/ the same name
        // remove the item
        // decrease total amount

        int amount = 0;
        for (ListToDoObj i : selected) {

            pass = i;

            removeFile(pass.getIndex());
            updateIndex(pass.getIndex());

            allItems.remove(pass.getIndex() - 1);
            System.out.println("New list: " + allItems);

            todoList.getItems().remove(pass.getIndex() - 1);

            amount++;
        }

        //TODO doesnt delete multiple files??
        for (int i = 0; i < amount; i++) {
            removeFile(allItems.size() + i + 1);
            System.out.println(allItems.size() + i + 1);
        }

    }

    public void removeFile(int txt) {
        String path = UtilityGeneral.userDirec();

        String newPath = path + "\\list_" + txt + ".txt";

        File index = new File(newPath);

        if (index.delete()) {
            System.out.println("Removed list_" + txt + ".txt successfully.");
        }
    }

    public void updateIndex(int index) throws IOException {

        for (int i = index; i < allItems.size(); i++) {

            allItems.get(i).setIndex(-1);
            storeData(allItems.get(i));
        }
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

    public void storeData(ListToDoObj item) throws IOException {
        UtilityGeneral.createFolder();

        if (item.getIndex() == -1) {
            int index = UtilityGeneral.indexer();

            FileWriter todoWrite = new FileWriter(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");

            //todoWrite.write("&NTDL\n");
            todoWrite.write(item.toString() + "\n" + index + "\n");

            todoWrite.close();

            item.setIndex(index);

        } else {

            int index = item.getIndex();
            StringBuilder data = new StringBuilder();

            File editedDataFile = new File(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(editedDataFile));

            String oldName = reader.readLine();
            oldName = reader.readLine();

            String newName = titleToDoTextBox.getText();
            data.append(newName).append("\n").append(index);

            String line = reader.readLine();
            while (line != null) {
                data.append(line);
                line = reader.readLine();
            }

            FileWriter writer = new FileWriter(editedDataFile);

            //writer.write("&NTDL\n");
            writer.write(data.toString());

            reader.close();
            writer.close();

        }
    }

    public void loadListFile() throws IOException {

        String path = System.getProperty("user.dir");

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path));

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File: ", "*.txt"));

        List<File> selectedFile = fc.showOpenMultipleDialog(null);
        for (File file : selectedFile) {
            formatFile(file);
        }

    }

    public void formatFile(File selectedFile) throws IOException {

        Scanner file = new Scanner(selectedFile);

        String name = file.nextLine();
        file.nextLine();

        addItem(name);
        int index = allItems.size();
        int amount = 0;

        //TODO FIX INDEX
        System.out.println("index: "+ index);

        FileWriter fw = new FileWriter(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");
        fw.write(name + "\n" + index + "\n");
        while(file.hasNextLine()){
            String temp = file.nextLine();
            fw.write(temp + "\n");
        }
        fw.close();

    }

}


