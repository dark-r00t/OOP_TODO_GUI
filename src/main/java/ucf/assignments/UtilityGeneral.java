package ucf.assignments;

import java.io.File;
import java.io.FileWriter;

public class UtilityGeneral {

    public static void addToDoSaveFolder(){

        String path = System.getProperty("user.dir") + "\\ToDo_Files";

        File ToDoFolder = new File(path);
        if(ToDoFolder.mkdir()){
            System.out.println("Created ToDo_Files folder.");
        }
    }

    public static void createTempFolder(){
        String path = userDirec();

        File ToDoFolder = new File(path);
        if(ToDoFolder.mkdir()){
            System.out.println("Created .temp folder.");
        }
    }

    public static int indexer(){
        int i;
        String path = userDirec();

        for(i = 1; i < 100 + 1; i++){
            File test = new File(path + "\\list_" + i + ".txt");
            if(!test.exists()){
                break;
            }
        }

        return i;
    }

    public static String userDirec() {
        // modify userDirec so that it's in a specific file path [i.e. /To-Do saves]

        return System.getProperty("user.dir") + "\\.temp";
    }

    public static void removeTempFiles(){

        String path = UtilityGeneral.userDirec();

        for (int i = 0; i < 100; i++) {

            int item = i + 1;
            String newPath = path + "\\list_" + item + ".txt";

            File index = new File(newPath);

            if(index.delete()){
                System.out.println("Deleted list_" + item + ".txt successfully.");
            }

        }
    }

}
