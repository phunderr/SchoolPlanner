package SchoolPlanner;

import SchoolPlanner.Data.FileReader;
import SchoolPlanner.GUI.Scenes.MainScene;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.removeFromFile("src/TextFile/Docent.txt", "johan talboom");
    }
}
