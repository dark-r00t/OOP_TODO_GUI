/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;

public class ListToDoController {
    public SplitPane mainPane;
    public TextField titleToDoTextBox;
    public SplitPane splitPane;
    public ListView<ListToDoObj> todoList;
    public ListToDoObj pass;

    ArrayList<ListToDoObj> allItems = new ArrayList();

    @FXML
    public void addNewToDoButton(ActionEvent actionEvent) throws IOException {
        // call ToDoList.addItem() w/ data from txtbox

        addItem();
    }

    @FXML
    public void deleteToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list


    }

    @FXML
    public void editNameToDoButton(ActionEvent actionEvent) throws IOException {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox

        editName();
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
    public void loadList(ActionEvent actionEvent) {
        // take a text file w/ a setup of allowing multiple inputs
        //  FileChooser
        //  set initial direc
        //  get all extension filters
        //  make a List<File> of selected files (show open multiple dialgoue)
        //  get items and add
        // load each element into the list view and store into the object list


    }

    public void addItem() throws IOException {
        // take the new item data

        String name = titleToDoTextBox.getText();

        ListToDoObj item = new ListToDoObj(name);
        todoList.getItems().add(item);

        allItems.add(item);

        storeData(item);

        System.out.println("Added: " + item);
    }

    public void removeItem(String name) {
        // run loop to find item w/ the same name
        // remove the item
        // decrease total amount

         // this.list.removeIf(item -> item.getName().equalsIgnoreCase(name));
         // this.amount--;
    }

    public void editName() throws IOException {
        // search for the selected list in the LinkedList
        // change the name w/ string from newName

        String name = titleToDoTextBox.getText();

        ObservableList<ListToDoObj> selected;
        selected = todoList.getSelectionModel().getSelectedItems();

        for(ListToDoObj i: selected){
            pass = i;
            pass.setName(name);
            storeData(pass);
        }
    }

    public void storeData(ListToDoObj item) throws IOException {
        UtilityGeneral.createFolder();

        if(item.getIndex() == -1){
            int index = UtilityGeneral.indexer();

            FileWriter todoWrite = new FileWriter(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");

            todoWrite.write("&NTDL\n");
            todoWrite.write(item.toString() + "\n" + index + "\n");

            todoWrite.close();

            item.setIndex(index);

        }

        else {

            int index = item.getIndex();
            StringBuilder data = new StringBuilder();

            File editedDataFile = new File(UtilityGeneral.userDirec() + "\\list_" + index + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(editedDataFile));

            String oldName = reader.readLine();
            oldName = reader.readLine();

            String newName = titleToDoTextBox.getText();
            data.append(newName).append("\n").append(index);

            String line = reader.readLine();
            while(line != null){
                data.append(line);
                line = reader.readLine();
            }

            FileWriter writer = new FileWriter(editedDataFile);

            writer.write("&NTDL\n");
            writer.write(data.toString());

            reader.close();
            writer.close();

        }
    }

}
