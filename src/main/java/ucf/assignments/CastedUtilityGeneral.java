/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */


package ucf.assignments;

import java.io.File;

public class CastedUtilityGeneral {

    public static void addToDoSaveFolder() {

        String path = System.getProperty("user.dir") + "\\ToDo_Files";

        File ToDoFolder = new File(path);
        if (ToDoFolder.mkdir()) {
            System.out.println("Created ToDo_Files folder.");
        }
    }

    public static void createTempFolder() {
        String path = tempDirec();

        File ToDoFolder = new File(path);
        if (ToDoFolder.mkdir()) {
            System.out.println("Created .temp folder.");
        }
    }

    public static int indexer() {
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
        // modify userDirec so that it's in a specific file path [i.e. /To-Do saves]

        return System.getProperty("user.dir") + "\\.temp";
    }

    public static void removeTempFiles() {

        String path = CastedUtilityGeneral.tempDirec();

        for (int i = 0; i < 100; i++) {

            int item = i + 1;
            String newPath = path + "\\list_" + item + ".txt";

            File index = new File(newPath);

            if (index.delete()) {
                System.out.println("Removed " + "list_" + item + ".txt");
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
        // YYYY-MM-DD

        StringBuilder year = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();

        if(date.length() == 10){

            year.append(date.charAt(0)).append(date.charAt(1)).append(date.charAt(2)).append(date.charAt(3));
            month.append(date.charAt(5)).append(date.charAt(6));
            day.append(date.charAt(8)).append(date.charAt(9));

            try{
                Integer.parseInt(year.toString());
                Integer.parseInt(month.toString());
                Integer.parseInt(day.toString());
                return true;
            } catch(NumberFormatException | NullPointerException e) {
                return false;
            }
        }

        return false;
    }

}
