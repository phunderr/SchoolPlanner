package SchoolPlanner.Data;
import java.io.*;
import java.util.Scanner;

public class FileReader {
//    private File path;


    public FileReader(){
//        this.path = new File(path);
    }

    /**
     * ReadObject reads an object from the object stream.
     * @throws Exception because we  use objectio
     */

    public Object ReadObject(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        return ois.readObject();
    }

    /**
     * WriteObject writes an object to the stream
     * @param obj is the object that is send to the stream
     * @throws Exception because we use objectio
     */

    public void WriteObject(Object obj, String path)throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(obj);
    }

    /**
     *
     * @param pathName
     * @return
     * @throws IOException
     * if a file isnt found
     */
    public File readTextFile(String pathName)throws IOException{
        File file = new File(pathName);



        return file;
    }

    /**
     *
     */
    public void writeTextFIle(){}


}
