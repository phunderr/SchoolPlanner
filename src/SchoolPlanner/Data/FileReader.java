package SchoolPlanner.Data;
import java.io.*;
import java.util.Scanner;

public class FileReader {
    private File schedule;

    public FileReader(String path){
        this.schedule = new File(path);
    }

    public void ReadFile() throws Exception{
        Scanner reader = new Scanner(schedule);
        reader.useDelimiter("#");
        while(reader.hasNextLine()){
            System.out.println(reader.nextLine());
        }
    }



}
