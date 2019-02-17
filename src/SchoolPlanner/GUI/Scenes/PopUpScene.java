package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.Classroom;
import SchoolPlanner.Data.FileReader;

import SchoolPlanner.Data.Lesson;
import SchoolPlanner.Data.LessonPeriod;
import SchoolPlanner.GUI.Logics.LessonRectangle;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author Stijn van Berkel & Jelmer Surewaard & Pascal Holthuijsen & Arno Nagtzaam
 * Creates a pop-up panel with 4 comboboxes containing:
 * teachers, classrooms, subjects, timestamps.
 */

public class PopUpScene {

    private ComboBox teacherComboBox;
    private ComboBox classroomComboBox;
    private ComboBox<String> subjectComboBox;
    private ComboBox timeFromComboBox;
    private ComboBox timeToComboBox;
    private Button submitButton;
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
        Label timeFromLabel = new Label("Start Time:");
        timeFromLabel.setMinWidth(100);
        Label timeToLabel = new Label("End Time:");
        timeToLabel.setMinWidth(100);
        HBox hBoxComboBox = new HBox();

        this.teacherComboBox = new ComboBox();
        this.teacherComboBox.setMinSize(200,10);
        this.classroomComboBox = new ComboBox();
        this.classroomComboBox.setMinSize(200,10);
        this.subjectComboBox = new ComboBox<>();
        this.subjectComboBox.setMinSize(200,10);
        this.timeFromComboBox = new ComboBox();
        this.timeFromComboBox.setMinSize(200,10);
        this.timeToComboBox = new ComboBox();
        this.timeToComboBox.setMinSize(200,10);

        HBox teacher = new HBox();
        teacher.getChildren().addAll(teacherLabel, teacherComboBox);

        HBox classroom = new HBox();
        classroom.getChildren().addAll(classroomLabel, classroomComboBox);

        HBox subject = new HBox();
        subject.getChildren().addAll(courseLabel, subjectComboBox);

        HBox timeFrom= new HBox();
        timeFrom.getChildren().addAll(timeFromLabel, timeFromComboBox);

        HBox timeTo= new HBox();
        timeTo.getChildren().addAll(timeToLabel, timeToComboBox);

        this.submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            Lesson lesson = new Lesson(subjectComboBox.getValue(),teacherComboBox.getValue().toString(),
                    new LessonPeriod(timeFromComboBox.getValue().toString(),timeToComboBox.getValue().toString()),
                    new Classroom(classroomComboBox.getValue().toString()));
            MainScene.addLesson(lesson);
            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(teacher, classroom, subject, timeFrom,timeTo,submitButton);
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
            File timesFile = fileReader.readTextFile("src/TextFile/Times.txt");

            Set<String> classes = fileReader.readFile(classfile);
            Set<String> teachers = fileReader.readFile(teacherFile);
            Set<String> subjects = fileReader.readFile(subjectFile);
            Set<String> times = fileReader.readFile(timesFile);

            List<String> sortedTimeList = new ArrayList<>(times);
            Collections.sort(sortedTimeList);

            classroomComboBox.setItems(FXCollections.observableArrayList(classes));
            teacherComboBox.setItems(FXCollections.observableArrayList(teachers));
            subjectComboBox.setItems(FXCollections.observableArrayList(subjects));
            timeFromComboBox.setItems(FXCollections.observableArrayList(sortedTimeList));
            timeToComboBox.setItems(FXCollections.observableArrayList(sortedTimeList));



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Button getSubmitButton(){
        return submitButton;
    }

}
