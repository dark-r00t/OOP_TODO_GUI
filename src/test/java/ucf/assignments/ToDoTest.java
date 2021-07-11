/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void over_hundred_items() {
        // populate a linked list with items
        // use the generateNewTask() method to do so
        // check to see if the list size is > 100

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        for (int i = 0; i < 125; i++) {
            String title = "title_" + i;
            String description = "description";
            String date = "2021-11-11";

            try {
                TaskToDoController.generateNewTask(tasks, title, description, date);
            } catch (Exception e) {
                System.out.println("Failed.");
            }
        }

        assertTrue(tasks.size() > 100);
    }

    @Test
    void description_length_less_than_256() {
        // create a string w/ a length greater than 256
        // try to create the description using editDescription
        // if it returns false, the description will not be changed

        String new_description = "this_description_has_a_length_of_257_IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII";

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, date, description,false);

        boolean result = TaskToDoController.editDescription(item, new_description);

        if(item.getDescription().equalsIgnoreCase(description)){
            assertFalse(result);
        } else {
            fail();
        }
    }

    @Test
    void due_date_format_1() {
        // make year before the current system year
        // check year

        String date = "1999-01-01";

        assertFalse(CastedUtilityGeneral.checkDateFormat(date));
    }

    @Test
    void due_date_format_2() {
        // make a year in the current year or higher and is in the correct format
        // check year

        String date = "2021-01-01";

        assertTrue(CastedUtilityGeneral.checkDateFormat(date));
    }

    @Test
    void due_date_display() {
        // only the correct format will have an effect
        // due to the boolean nature of the method

        String display;

        String date = "2021-01-01";

        if (CastedUtilityGeneral.checkDateFormat(date)) {
            display = date;
        } else {
            display = "failed";
        }

        assertEquals(display, date);
    }

    @Test
    void add_new_item() {
        // create necessary strings to make a task object
        // create a new task object using generateNewTask()
        // check to see if the data was processed correctly

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        String title = "title";
        String description = "description";
        String date = "2021-11-11";

        try {
            TaskToDoController.generateNewTask(tasks, title, description, date);
        } catch (Exception e) {
            System.out.println("Failed.");
        }

        assertEquals(tasks.get(0).getName(), "title");
    }

    @Test
    void remove_item() {
        // create necessary strings to make a task object
        // create a new task object using generateNewTask()
        // check to see if the data was processed correctly by checking the size
        // call deleteTask()
        // check to see if the size drops

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        String title = "title";
        String description = "description";
        String date = "2021-11-11";

        try {
            TaskToDoController.generateNewTask(tasks, title, description, date);
            TaskToDoController.generateNewTask(tasks, title, description, date);
        } catch (Exception e) {
            System.out.println("Failed.");
        }

        if (tasks.size() == 2) {
            TaskToDoController.deleteTask(tasks, 1);
        } else {
            fail();
        }

        assertEquals(tasks.size(), 1);
    }

    @Test
    void clear() {
        // fill a linked list with random data
        // check to see if there are items in the linked list, if not fail the test
        // call clearList()
        // check to see if the linked list is empty

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        for (int i = 0; i < 125; i++) {
            String title = "title_" + i;
            String description = "description";
            String date = "2021-11-11";

            try {
                TaskToDoController.generateNewTask(tasks, title, description, date);
            } catch (Exception e) {
                System.out.println("Failed.");
            }
        }

        if (tasks.isEmpty()) {
            fail();
        }

        TaskToDoController.clearList(tasks);

        assertTrue(tasks.isEmpty());
    }

    @Test
    void change_description() {
        // create an item with all necessary data
        // try to change the description
        // check to see if the description was changed

        String title = "title";
        String description = "yes";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        boolean change = TaskToDoController.editDescription(item, "no");

        if (change) {
            assertEquals("no", item.getDescription());
        } else {
            fail();
        }
    }

    @Test
    void change_date() {
        // create an item with all necessary data
        // call editDate() with a valid date
        // check boolean result, if false fail
        // assert that the items date was changed to the date provided

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        boolean change = TaskToDoController.editDate(item, "9999-01-01");

        if (change) {
            assertEquals("9999-01-01", item.getDate());
        } else {
            fail();
        }
    }

    @Test
    void mark_complete() {
        // create an item with all necessary data
        // the item has a complete status of false
        // try to change the complete status by using markAsComplete()
        // check to see if the items complete status is now true

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        TaskToDoController.markAsComplete(item);

        assertTrue(item.isComplete());
    }

    @Test
    void mark_incomplete() {
        // create an item with all necessary data
        // the item has a complete status of false
        // set the items complete status to true
        // check to see if the items complete status is true, if not fail
        // try to change the status using markAsIncomplete()
        // check to see if the result is false

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        item.setComplete(true);

        if (item.isComplete()) {
            TaskToDoController.markAsIncomplete(item);
        } else {
            fail();
        }

        assertFalse(item.isComplete());
    }

    @Test
    void all_items_display() {
        // create an item with all necessary data
        // save the result of displayAll() w/ the created item
        // check to see if the string is equivalent to what was expected

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, date, description, false);

        String actual = TaskToDoController.displayTask(item);

        String expected = item.getName() + "                                                       " + item.getDescription() + "                                                                                                                   " + item.getDate();

        if(actual.length() >= 189){
            assertEquals(actual, expected);
        } else {
            fail();
        }
    }

    @Test
    void complete_items_display() {
        // create an item with all necessary data
        // set items complete status to true
        // save the result of displayCompleted
        // check to see if the actual result matches the expected result

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, date, description, false);

        item.setComplete(true);

        String actual = TaskToDoController.displayCompleted(item, item.isComplete());

        String expected = item.getName() + "                                                       " + item.getDescription() + "                                                                                                                   " + item.getDate();

        if(item.isComplete()){
            assertEquals(actual, expected);
        } else {
            fail();
        }
    }

    @Test
    void incomplete_items_display() {
        // create an item with all necessary data
        // set items complete status to false
        // save the result of displayCompleted
        // check to see if the actual result matches the expected result

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, date, description, false);

        item.setComplete(false);

        String actual = TaskToDoController.displayIncomplete(item, item.isComplete());

        String expected = item.getName() + "                                                       " + item.getDescription() + "                                                                                                                   " + item.getDate();

        if(actual.length() >= 189){
            assertEquals(actual, expected);
        } else {
            fail();
        }
    }

    @Test
    void save() throws IOException {
        // create all necessary folders
        // create a new save file at index 999 -> ToDo_Files/save_999.txt
        // fill a linked list with items
        // call save() w/ the list of items
        // write the result of save() to the save file
        // check to see if the file exists

        CastedUtilityGeneral.addToDoSaveFolder();
        CastedUtilityGeneral.createTempFolder();

        File savedOutput = new File(System.getProperty("user.dir") + "\\ToDo_Files\\save_999.txt");

        FileWriter fw = new FileWriter(savedOutput);

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        for (int i = 1; i <= 125; i++) {
            String title = "title_" + i;
            String description = "description";
            String date = "2021-11-11";

            try {
                TaskToDoController.generateNewTask(tasks, title, description, date);
            } catch (Exception e) {
                System.out.println("Failed.");
            }
        }

        String output = TaskToDoController.save(tasks);

        fw.write(output);
        fw.close();

        assertTrue(savedOutput.exists() && tasks.size() == 125);
    }

    @Test
    void load_list() {


    }

}

//TODO UPDATE PUML