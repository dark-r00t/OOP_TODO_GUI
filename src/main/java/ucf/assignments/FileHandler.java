/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jan Darge
 */


package ucf.assignments;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler {

    public static LinkedList<TaskToDoObj> tasks() throws FileNotFoundException {

        LinkedList<TaskToDoObj> tasks = new LinkedList<>();

        /*private String name;
        private String date;
        private String description;
        boolean complete;*/

        try {
            File selected = new File(System.getProperty("user.dir") + "\\.temp\\selected.txt");
            Scanner scanner = new Scanner(selected);

            scanner.nextLine();

            int i = 0;
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String date = scanner.nextLine();
                String description = scanner.nextLine();
                String completed = scanner.nextLine();

                tasks.add(new TaskToDoObj(name, date, description, Boolean.parseBoolean(completed)));

                i++;
            }

        } catch (Exception e) {
            System.out.println("Failed to read file.");
        }

        return tasks;
    }

    public static void renewFileAfterDelete(TaskToDoObj item) throws IOException {

        String fileContent = readSelectedFile();

        fileContent = removeTaskFileData(fileContent, item);

        fileSelectedGenerator(fileContent);
    }

    private static String readSelectedFile() throws FileNotFoundException {

        StringBuilder output = new StringBuilder();
        File document = new File(System.getProperty("user.dir") + "\\.temp\\selected.txt");
        Scanner scanner = new Scanner(document);
        while (scanner.hasNextLine()) {
            output.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        return output.toString();
    }

    private static void fileSelectedGenerator(String fileString) throws IOException {

        String output = System.getProperty("user.dir") + "\\.temp\\selected.txt";

        FileWriter export = new FileWriter(output);
        PrintWriter writer = new PrintWriter(export);

        writer.println(fileString);

        writer.close();
    }

    private static String removeTaskFileData(String fileContent, TaskToDoObj item) {

        if (fileContent.contains(item.getName())) {

            String remove = item.getName() + "\n" + item.getDate() + "\n" + item.getDescription() + "\n" + item.isComplete() + "\n";

            return fileContent.replace(remove, "");

        }

        return fileContent;
    }

}
