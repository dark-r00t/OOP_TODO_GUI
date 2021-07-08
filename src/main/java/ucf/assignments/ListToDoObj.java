/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import java.util.LinkedList;

public class ListToDoObj {
    // create a private string for name
    // create a private linkedlist of todoitems
    // create a private integer for list total amount
    // getters and setters
    // create a constructor
    // addItem function
    // remove item function

    private String name;
    private LinkedList<TaskToDoObj> list;
    private int amount;
    public int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<TaskToDoObj> getList() {
        return list;
    }

    public void setList(LinkedList<TaskToDoObj> list) {
        this.list = list;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ListToDoObj(String name) {
        this.name = name;
        this.list = new LinkedList<>();
        this.amount = 0;
        this.index = -1;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
