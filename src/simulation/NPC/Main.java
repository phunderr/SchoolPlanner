package simulation.NPC;

import javax.lang.model.element.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/TextFile/NPCNames.txt"));

        ArrayList<String> names = new ArrayList<>();

        while (input.hasNextLine()){
            String name = input.nextLine();
            names.add(name);

        }System.out.println(names);
    }
}
