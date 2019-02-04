package SchoolPlanner;

import SchoolPlanner.Data.FileReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("1 2 3, motherfucker yeah ~Jack black Playing the Sax-A-Boom~");
        FileReader lol = new FileReader("src/TextFile/Docent.txt");
        try {
            lol.ReadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
