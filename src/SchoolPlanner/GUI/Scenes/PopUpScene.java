package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.FileReader;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author Stijn van Berkel & Jelmer Surewaard & Pascal Holthuijsen
 * Creates a pop-up panel with 4 comboboxes containing:
 * teachers, classrooms, subjects, timestamps.
 */

public class PopUpScene {

    private ComboBox teacherComboBox;
    private ComboBox classroomComboBox;
    private ComboBox<String> subjectComboBox;
    private ComboBox timestampComboBox;
    private FileReader fileReader;

    public void generatePopUp (Stage stage) {
        this.fileReader = new FileReader();
        stage.setTitle("Add Lesson");
        stage.setMinWidth(350);
        stage.setMinHeight(350);

        HBox hBoxLabel = new HBox();

        Label teacherLabel = new Label("Teacher:");
        teacherLabel.setMinWidth(100);
        Label classroomLabel = new Label("Classroom:");
        classroomLabel.setMinWidth(100);
        Label courseLabel = new Label("Subject:");
        courseLabel.setMinWidth(100);
        Label timestampLabel = new Label("Timestamp:");
        timestampLabel.setMinWidth(100);
        HBox hBoxComboBox = new HBox();

        this.teacherComboBox = new ComboBox();
        this.classroomComboBox = new ComboBox();
        this.subjectComboBox = new ComboBox<>();
        this.timestampComboBox = new ComboBox();

        HBox teacher = new HBox();
        teacher.getChildren().addAll(teacherLabel, teacherComboBox);

        HBox classroom = new HBox();
        classroom.getChildren().addAll(classroomLabel, classroomComboBox);

        HBox subject = new HBox();
        subject.getChildren().addAll(courseLabel, subjectComboBox);

        HBox timestamp = new HBox();
        timestamp.getChildren().addAll(timestampLabel, timestampComboBox);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(teacher, classroom, subject, timestamp);
        vBox.getChildren().addAll(hBoxLabel, hBoxComboBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setPadding(new Insets(75,75,75,75));

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();

        fillComboBoxes();
    }


    /**
     * fillComboBoxes()
     * fills the combo boxes with the corresponding information
     */
    public void fillComboBoxes(){
        try {
            File classfile = fileReader.readTextFile("src/TextFile/Classes.txt");
            File teacherFile = fileReader.readTextFile("src/TextFile/Docent.txt");
            File subjectFile = fileReader.readTextFile("src/TextFile/Subject.txt");

            Set<String> classes = readFile(classfile);
            Set<String> teachers = readFile(teacherFile);
            Set<String> subjects = readFile(subjectFile);

            classroomComboBox.setItems(FXCollections.observableArrayList(classes));
            teacherComboBox.setItems(FXCollections.observableArrayList(teachers));
            subjectComboBox.setItems(FXCollections.observableArrayList(subjects));



        } catch (IOException e) {
            e.printStackTrace();
        }




//        classroomComboBox;
//        subjectComboBox;
//        teacherComboBox;
//        timestampComboBox;
    }


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
}
