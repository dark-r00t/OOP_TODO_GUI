/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */

package ucf.assignments;

import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

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

        String display = "testing";

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
    void change_date(){


    }

    @Test
    void mark_complete(){


    }

    @Test
    void mark_incomplete(){


    }

    @Test
    void all_items_display(){


    }

    @Test
    void complete_items_display(){


    }

    @Test
    void incomplete_items_display(){


    }

    @Test
    void save(){


    }

    @Test
    void load_list(){


    }

    @Test
    void Rey_tribute(){


    }

}

//TODO UPDATE PUML