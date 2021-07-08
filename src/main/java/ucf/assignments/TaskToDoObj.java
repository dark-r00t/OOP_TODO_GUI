/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

public class TaskToDoObj {
    // make private variables
    // - name
    // - date
    // - description
    // boolean for completed

    private String name;
    private String date;
    private String description;
    boolean complete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public TaskToDoObj(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.complete = false;
    }
}
