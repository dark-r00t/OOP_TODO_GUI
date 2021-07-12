/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public LinkedList<ListToDoObj> allItems = new LinkedList<>();
    public Button helpMenuButton;

    @FXML
    public void addNewToDoButton() throws IOException {
        // call ToDoList.addItem() w/ data from txtbox

        String name = titleToDoTextBox.getText();

        addItem(name);
    }

    @FXML
    public void deleteToDoButton() {
        // gets every selected item
        // run loop for every selected item
        // call deleteItem(index of selected item)

        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj i : selected) {
            deleteItem(i);
        }
    }

    @FXML
    public void editNameToDoButton() throws IOException {
        // get every selected item
        // for every item selected call edit name with the selected object

        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj item : selected) {
            editName(item);
        }
    }

    @FXML
    public void listClicked() {
        // allow for multiple items
        // save selected accordingly

        todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void selectToDoButton() throws IOException {
        // get the selected item
        // call selectToDo()
        // open new scene
        // break loop as to only allow for the first clicked item

        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj i : selected) {
            selectToDo(i);
            SceneController.switchToTask();

            break;
        }

    }

    @FXML
    public void saveList() throws IOException {
        // create an observable list with all selected items
        // run a loop and store each list carefully into a txt file

        selected = todoList.getSelectionModel().getSelectedItems();

        for (ListToDoObj item : selected) {
            saveSelectedList(item);
        }
    }

    @FXML
    public void loadList() {
        // calls loadListFile()

        loadListFile();
    }

    @FXML
    public void helpMenu() throws IOException {
        // opens help scene

        SceneController.menuHelp();
    }

    public void addItem(String name) throws IOException {
        // take the new item data
        // add name into a list file
        // add name into a linked list of every item available
        // write the list name into it's own file (list_#.txt)

        ListToDoObj item = new ListToDoObj(name);

        todoList.getItems().add(item);
        allItems.add(item);

        storeData(item);
    }

    public void deleteItem(ListToDoObj item) {
        // update pass (ListTodoObj)
        // remove that items file from the list
        // check to see if the deleted item was the last item available
        // if it wasnt -> update list file index name
        // reduce index size
        // remove item from the linked list of objects
        // remove item from the display listView

        pass = item;

        removeFile(pass.getIndex());

        try {
            if (pass.getIndex() != allItems.size()) {
                ListToDoHandler.updateFileName(pass.getIndex(), allItems.size());
            }
        } catch (Exception e) {
            System.out.println("Failed to update file names.");
        }

        try {
            allItems.remove(pass.getIndex() - 1);
            todoList.getItems().remove(pass.getIndex() - 1);
            ListToDoHandler.updateIndex(allItems);
        } catch (Exception e) {
            System.out.println("Failed to remove item from list.");
        }
    }

    public void removeFile(int txt) {
        // takes the index of the deleted file
        // removes the specific file from the .temp direc

        String path = CastedUtilityGeneral.tempDirec();
        String newPath = path + "\\list_" + txt + ".txt";

        File index = new File(newPath);

        if (index.delete()) {
            System.out.println("Removed list_" + txt + ".txt successfully.");
        }
    }

    public void editName(ListToDoObj item) throws IOException {
        // set the temp object equal to the item object
        // store temp w/ the new name
        // update the objects in the display

        temp = item;

        storeData(temp);

        temp.setName(titleToDoTextBox.getText());

        todoList.getItems().add(temp);
        todoList.getItems().remove(item);
    }

    public void selectToDo(ListToDoObj item) {
        // takes the selected item
        // copies data of the selected item into selected.txt

        pass = item;

        File newFile = new File(CastedUtilityGeneral.tempDirec() + "\\selected.txt");
        File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + pass.getIndex() + ".txt");

        try {
            Files.copy(oldFile.toPath(), newFile.toPath());
        } catch (Exception e) {
            System.out.println("Failed copying files..");
        }
    }

    public void saveSelectedList(ListToDoObj item) throws IOException {
        // takes selected item
        // looks for a valid location to store the list
        //  - save_available#.txt
        // copies selected items data into the correct save file
        // opens popup to show display path

        for (int i = 1; ; i++) {
            if (!new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt").isFile()) {

                String path = System.getProperty("user.dir") + "\\ToDo_Files\\save_" + i + ".txt";

                File newFile = new File(path);
                File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + item.getIndex() + ".txt");

                try {
                    Files.copy(oldFile.toPath(), newFile.toPath());
                } catch (Exception e) {
                    System.out.println("Failed to copy files.");
                }

                FileHandler.removeExtraNewLine(newFile, path);

                SceneController.savePopUp("save_" + i + ".txt");

                break;
            }
        }
    }

    public void loadListFile() {
        // sets up initial file direc
        // takes in multiple files if need be
        // for every file selected format file using formatFile()
        // - set an index var to the size of allItems linked list + 1
        // save result of formatfile using the index and selected file to a string
        // add a new item using formatfile result
        // update the new items index

        String path = System.getProperty("user.dir");
        FileChooser fc = new FileChooser();

        try{
            fc.setInitialDirectory(new File(path + "\\ToDo_Files"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File: ", "*.txt"));
            List<File> selectedFile = fc.showOpenMultipleDialog(null);

            for (File file : selectedFile) {
                int index = allItems.size() + 1;
                String file_name = ListToDoHandler.formatFile(file, index);
                addItem(file_name);
                allItems.get(index - 1).setIndex(index);
            }
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public void storeData(ListToDoObj item) throws IOException {
        // takes in an item
        // sets up a .temp folder if one is not already available
        // if the item is new (index is a -1)
        // - create new file
        // if the item is not new (available index)
        // - takes the index of file and prepares to rewrite it
        // - replaces item's file data with new data

        CastedUtilityGeneral.createTempFolder();

        int index;

        if (item.getIndex() == -1) {

            index = CastedUtilityGeneral.indexer();

            FileWriter todoWrite = new FileWriter(CastedUtilityGeneral.tempDirec() + "\\list_" + index + ".txt");

            todoWrite.write(item + "\n");
            todoWrite.close();

        } else {

            index = item.getIndex();
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
        }

        item.setIndex(index);
    }

}


