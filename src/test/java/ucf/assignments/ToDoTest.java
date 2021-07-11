/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void over_hundred_items() {

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

        String title = "title";
        String description = "this_description_has_a_length_of_257_IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        boolean result = TaskToDoController.editDescription(item, description);

        assertFalse(result);
    }

    @Test
    void due_date_format_1() {

        String date = "1999-01-01";

        assertFalse(CastedUtilityGeneral.checkDateFormat(date));
    }

    @Test
    void due_date_format_2() {

        String date = "2021-01-01";

        assertTrue(CastedUtilityGeneral.checkDateFormat(date));
    }

    @Test
    void due_date_display() {

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

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, description, date, false);

        TaskToDoController.markAsComplete(item);

        assertTrue(item.isComplete());
    }

    @Test
    void mark_incomplete() {

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

        String title = "title";
        String description = "desc";
        String date = "2021-11-11";

        TaskToDoObj item = new TaskToDoObj(title, date, description, false);

        String actual = TaskToDoController.displayAll(item);

        String expected = item.getName() + "                                                       " + item.getDescription() + "                                                                                                                   " + item.getDate();

        if(actual.length() >= 189){
            assertEquals(actual, expected);
        } else {
            fail();
        }
    }

    @Test
    void complete_items_display() {

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