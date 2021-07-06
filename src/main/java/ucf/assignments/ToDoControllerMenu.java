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

public class ToDoControllerMenu {
    public SplitPane mainPane;
    public TextField titleToDoTextBox;
    public SplitPane splitPane;
    public ListView todoList;
    ListView<ToDoList> selected;

    @FXML
    public void addNewToDoButton(ActionEvent actionEvent) {
        // call ToDoList.addItem() w/ data from txtbox

    }

    @FXML
    public void deleteToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list


    }

    @FXML
    public void editNameToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox


    }

    @FXML
    public void listClicked(MouseEvent mouseEvent) {
        // allow for multiple items
        // save selected accordingly

        // code idea?
        // todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
}

// TODO file popup browser for TODO List Load functionality
