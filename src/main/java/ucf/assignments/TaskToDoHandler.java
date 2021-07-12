package ucf.assignments;

import java.util.LinkedList;

public class TaskToDoHandler {

    public static String displayTask(TaskToDoObj task) {
        // return the result of generalDisplaySetup using the provided task

        try {
            return generalDisplaySetup(task);
        } catch (Exception e) {
            System.out.println("Failed to display all items.");
        }

        return "";
    }

    public static String displayCompleted(TaskToDoObj task, boolean status) {
        // if the item given has a status of true
        // return the result of generalDisplaySetup using the task provided

        if (status) {
            try {
                return generalDisplaySetup(task);
            } catch (Exception e) {
                System.out.println("Failed to display all items.");
            }
        }

        return "";
    }

    public static String displayIncomplete(TaskToDoObj task, boolean status) {
        // if the item given has a status of false
        // return the result of generalDisplaySetup using the task provided

        if (!status) {
            try {
                return generalDisplaySetup(task);
            } catch (Exception e) {
                System.out.println("Failed to display all items.");
            }
        }

        return "";
    }

    public static void markAsComplete(TaskToDoObj item) {
        // take a TaskToDo object
        // make its complete status to true

        item.setComplete(true);
    }

    public static void markAsIncomplete(TaskToDoObj item) {
        // take a TaskToDo object
        // make its complete status to false

        item.setComplete(false);
    }

    public static String generateNewTask(LinkedList<TaskToDoObj> list, String title, String description, String date) {
        // takes in a list of items
        // make a new object using the other provided data
        // add the new item to the list
        // return the result of generalDisplaySetup using the new item

        TaskToDoObj item = new TaskToDoObj(title, date, description, false);
        list.add(item);
        return generalDisplaySetup(item);
    }

    public static void deleteTask(LinkedList<TaskToDoObj> list, int i) {
        // removes a specific task from a provided list of items

        list.remove(i);
    }

    public static boolean editDescription(TaskToDoObj item, String newDescription) {
        // take in an item and a new description name
        // if the provided description is <= 256
        // change description and return true

        try {
            if (newDescription.length() <= 256) {
                item.setDescription(newDescription);
                return true;
            } else {
                System.out.println("Item description is too long!");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the description.");
        }

        return false;
    }

    public static boolean editDate(TaskToDoObj item, String newDate) {
        // take in an item and a new date
        // if the provided date fits the required format
        // change date and return true

        try {
            if (CastedUtilityGeneral.checkDateFormat(newDate)) {
                item.setDate(newDate);
                return true;
            } else {
                System.out.println("Date is not in the correct format.");
            }
        } catch (Exception e) {
            System.out.println("Invalid item selected, cannot change the date.");
        }

        return false;
    }

    public static void clearList(LinkedList<TaskToDoObj> list) {
        // takes in a linked list of items
        // while the list is not empty
        // remove an item

        while (!list.isEmpty()) {
            list.remove();
        }
    }

    // TODO FIX ALIGNMENT ISSUES (Note: console display is fine, listview display is off for some reason???)
    public static String generalDisplaySetup(TaskToDoObj task) {
        // add name description and date with appropriate spacing and adds the string into the view

        StringBuilder items = new StringBuilder();

        items.append(task.getName());
        while (items.toString().length() != 50 + 10) {
            items.append(" ");
        }

        items.append(task.getDescription());
        while (items.toString().length() != 60 + 119) {
            items.append(" ");
        }

        items.append((task.getDate()));

        return items.toString();
    }
}
