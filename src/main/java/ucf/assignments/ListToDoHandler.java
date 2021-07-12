package ucf.assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ListToDoHandler {

    public static String formatFile(File selectedFile, int index) throws IOException {
        // takes a file
        // reads the first line in file (name of the to-do list)
        // - stores into an available list_#.txt
        // - adds name of list to the display
        // - adds item into the linkedList of objects
        // writes every line afterwards into the file

        Scanner file = new Scanner(selectedFile);
        String name = file.nextLine();

        FileWriter fw = new FileWriter(CastedUtilityGeneral.tempDirec() + "\\list_" + index + ".txt");
        fw.write(name + "\n");

        while (file.hasNextLine()) {
            fw.write(file.nextLine() + "\n");
        }

        fw.close();

        return name;
    }

    public static void updateFileName(int index, int size) {
        // takes in a file index
        // loop for as many items are in the linked list
        // set i = file index
        // create a file with desired index and the actual index
        // rename file so it matches desired output

        for (int j = index; j < size; j++) {

            File newFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + (j + 1) + ".txt");
            File oldFile = new File(CastedUtilityGeneral.tempDirec() + "\\list_" + (j) + ".txt");

            try {
                if (newFile.renameTo(oldFile)) {
                    System.out.print("");
                }
            } catch (Exception e) {
                System.out.println("Update file failed.");
            }
        }
    }

    public static void updateIndex(LinkedList<ListToDoObj> every_item) {
        for (ListToDoObj item : every_item){
            item.setIndex(item.getIndex() - 1);
        }
    }
}
