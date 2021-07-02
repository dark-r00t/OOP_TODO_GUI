package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ToDoControllerMenu {
    public SplitPane mainPane;
    public TextField titleToDoTextBox;
    public SplitPane splitPane;
    public ListView todoList;

    // create String for selected list

    public void addNewToDoButton(ActionEvent actionEvent) {
        // call ToDoList.addItem() w/ data from txtbox

    }

    public void deleteToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // call ToDoList.removeItem() w/ the string for the selected list


    }

    public void editNameToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // get data from text box
        // call ToDoList.editName() w/ data from selected string and textbox


    }

    public void listClicked(MouseEvent mouseEvent) {
        // update string for selected list


    }

    public void selectToDoButton(ActionEvent actionEvent) {
        // check to see if the selected list is not empty
        // open new scene for ToDofxml w/ data from string selected list
        // close old scene


    }


}
