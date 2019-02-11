package SchoolPlanner.Data;
import java.io.*;
import java.util.Scanner;

public class FileReader {
//    private File path;


    public FileReader(){
//        this.path = new File(path);
    }

    /**
     * ReadObject leest een object af van de object stream.
     * @throws Exception omdat we objectio gebruiken
     */

    public Object ReadObject(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        return ois.readObject();
    }

    /**
     * WriteObject schrijft een object naar de stream
     * @param obj is het object dat naar de stream wordt gestuurd
     * @throws Exception omdat we object io gebruiken
     */

    public void WriteObject(Object obj, String path)throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(obj);
    }



}
