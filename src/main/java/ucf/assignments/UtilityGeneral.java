package ucf.assignments;

import java.io.File;
import java.util.Scanner;

public class UtilityGeneral {

    public static void createFolder(){
        String path = userDirec();

        File ToDoFolder = new File(path);
        if(ToDoFolder.mkdir()){
            System.out.println("created folder");
        }
        else
            System.out.println("folder already exists");
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

        return System.getProperty("user.dir") + "\\ToDo_Files";
    }

}
