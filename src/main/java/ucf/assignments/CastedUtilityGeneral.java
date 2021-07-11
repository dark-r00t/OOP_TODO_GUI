/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */


package ucf.assignments;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class CastedUtilityGeneral {

    public static void addToDoSaveFolder() {
        // Check to see if there is a file called ToDo-Files
        // if there is not create one

        String path = System.getProperty("user.dir") + "\\ToDo_Files";

        File ToDoFolder = new File(path);
        if (ToDoFolder.mkdir()) {
            System.out.println("Created ToDo_Files folder.");
        }
    }

    public static void createTempFolder() {
        // Check to see if there is a file called .temp
        // if there is not create one

        String path = tempDirec();

        File ToDoFolder = new File(path);
        if (ToDoFolder.mkdir()) {
            System.out.println("Created .temp folder.");
        }
    }

    public static int indexer() {
        // looks in .temp for the next available list_#
        // returns the available index

        int i;
        String path = tempDirec();

        for (i = 1; i < 100 + 1; i++) {
            File test = new File(path + "\\list_" + i + ".txt");
            if (!test.exists()) {
                break;
            }
        }

        return i;
    }

    public static String tempDirec() {
        // returns a string of the .temp folder created in the initial startup

        return System.getProperty("user.dir") + "\\.temp";
    }

    public static void removeTempFiles() {

        // gets the first 100 list_#.txt files
        // removes them
        // prints the deleted files in the console
        // then removes the other temp file not labled as list_#

        String path = CastedUtilityGeneral.tempDirec();

        for (int i = 1; i < 101; i++) {

            String newPath = path + "\\list_" + i + ".txt";

            File index = new File(newPath);

            if (index.delete()) {
                System.out.println("Removed " + "list_" + i + ".txt");
            }

        }

        File previousSelected = new File(tempDirec() + "\\selected.txt");
        try {
            if (previousSelected.delete()) {
                System.out.println("Removed selected.txt");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete selected.txt");
        }
    }

    public static boolean checkDateFormat(String date){

        // takes a string from a date input
        // check to see if its in YYYY-MM-DD format
        // check to see if the year is at least the year of the systems year
        // check to see if the day/month inputs are valid
        // if everything is good return true

        StringBuilder year = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();

        if(date.length() == 10){

            year.append(date.charAt(0)).append(date.charAt(1)).append(date.charAt(2)).append(date.charAt(3));
            month.append(date.charAt(5)).append(date.charAt(6));
            day.append(date.charAt(8)).append(date.charAt(9));

            try{

                Calendar systemCalendar = Calendar.getInstance();
                int systemYear = systemCalendar.get(Calendar.YEAR);

                Integer.parseInt(year.toString());
                Integer.parseInt(month.toString());
                Integer.parseInt(day.toString());

                if(Integer.parseInt(year.toString()) < systemYear) {
                    System.out.println("Year input is less then current year (" + systemYear + ").");
                    return false;
                }

                if(Integer.parseInt(month.toString()) < 1 || Integer.parseInt(month.toString()) > 12) {
                    System.out.println("Month input is out-of-bounds.");
                    return false;
                }

                if(Integer.parseInt(day.toString()) < 1 || Integer.parseInt(day.toString()) > 31) {
                    System.out.println("Day input is out-of-bounds");
                    return false;
                }

                return true;
            } catch(NumberFormatException | NullPointerException e) {
                return false;
            }
        }

        return false;
    }

}
