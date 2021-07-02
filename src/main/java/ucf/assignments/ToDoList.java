package ucf.assignments;

import java.util.LinkedList;

public class ToDoList {
    // create a private string for name
    // create a private linkedlist of todoitems
    // create a private integer for list total amount
    // getters and setters
    // create a constructor
    // addItem function
    // remove item function

    private String name;
    private LinkedList<ToDoItem> list;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<ToDoItem> getList() {
        return list;
    }

    public void setList(LinkedList<ToDoItem> list) {
        this.list = list;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ToDoList(String name) {
        this.name = name;
        this.list = new LinkedList<>();
        this.amount = 0;
    }

    public void addItem(ToDoItem newItem) {
        // take the new item data

        // this.list.add(newItem);
        // this.amount++;
    }

    public void removeItem(String name) {
        // run loop to find item w/ the same name
        // remove the item
        // decrease total amount

        // this.list.removeIf(item -> item.getName().equalsIgnoreCase(name));
        // this.amount--;
    }

    public void editName(String newName, String selectedList) {
        // search for the selected list in the LinkedList
        // change the name w/ string from newName


    }

}
