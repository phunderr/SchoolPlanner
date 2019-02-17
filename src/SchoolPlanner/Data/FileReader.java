package SchoolPlanner.Data;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Reads and writes text and object files
 * @Autor Pascal Holthuijsen & luke Taylor
 */
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
     * if a file isnt found throws a exception
     */
    public File readTextFile(String pathName)throws IOException{
        File file = new File(pathName);



        return file;
    }

    /**
     *
     */
    public void writeTextFIle(){}

    /**
     *readfile reads a file and set the content to a set
     * @param file text file that contains data
     * @return  a set of strings containig the data of the file
     * @throws IOException throws this if the exception the file cannot be read
     */
    public Set<String> readFile(File file)throws IOException{
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> data = new HashSet<>();

        while(scanner.hasNextLine()){
            data.add(scanner.nextLine());
        }
        return data;
    }

    public void addToFile(String pathName, String message) throws IOException{
        FileOutputStream fos = new FileOutputStream(pathName, true);
        message = "\n" + message;
        fos.write(message.getBytes());
        fos.close();

    }



    public void removeFromFile(String pathName, String removeFile) throws IOException{
        FileInputStream fis = new FileInputStream(pathName);

        Scanner reader = new Scanner(fis);
        StringBuilder dataKept = new StringBuilder();

        while(reader.hasNextLine()){
            String data = reader.nextLine().trim();

            if ( !data.equals(removeFile) ){
                dataKept.append(data).append("\n");
            }
        }

        FileWriter fl = new FileWriter(pathName);
        fl.write("");
        FileOutputStream out = new FileOutputStream(pathName);
        out.write(dataKept.toString().trim().getBytes());
        fl.close();
        out.close();
        fis.close();
    }




}
