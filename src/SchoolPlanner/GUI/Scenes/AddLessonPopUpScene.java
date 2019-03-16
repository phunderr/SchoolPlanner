package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.*;

import SchoolPlanner.GUI.Logics.LessonRectangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AddLessonPopUpScene {

    private ComboBox teacherComboBox;
    private ComboBox classroomComboBox;
    private ComboBox subjectComboBox;
    private ComboBox timeFromComboBox;
    private ComboBox timeToComboBox;
    private ComboBox classComboBox;
    private Button submitButton;
    private FileReader fileReader;
    private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
    private ObservableList<ClassName> classNameObservableList = FXCollections.observableArrayList();
    private ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
    private ObservableList<Classroom> classroomObservableList = FXCollections.observableArrayList();
    private ObservableList<Lesson> lessonObservableList = FXCollections.observableArrayList();

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
        Label subjectLabel = new Label("Subject:");
        subjectLabel.setMinWidth(100);
        Label timeFromLabel = new Label("Start Time:");
        timeFromLabel.setMinWidth(100);
        Label timeToLabel = new Label("End Time:");
        timeToLabel.setMinWidth(100);
        Label classLabel = new Label("Classes:");
        classLabel.setMinWidth(100);
        HBox hBoxComboBox = new HBox();

        this.teacherComboBox = new ComboBox();
        this.teacherComboBox.setMinSize(200,10);
        this.classroomComboBox = new ComboBox();
        this.classroomComboBox.setMinSize(200,10);
        this.subjectComboBox = new ComboBox();
        this.subjectComboBox.setMinSize(200,10);
        this.timeFromComboBox = new ComboBox();
        this.timeFromComboBox.setMinSize(200,10);
        this.timeToComboBox = new ComboBox();
        this.timeToComboBox.setMinSize(200,10);
        this.classComboBox = new ComboBox();
        this.classComboBox.setMinSize(200,10);

        HBox teacher = new HBox();
        teacher.getChildren().addAll(teacherLabel, teacherComboBox);

        HBox classroom = new HBox();
        classroom.getChildren().addAll(classroomLabel, classroomComboBox);

        HBox subject = new HBox();
        subject.getChildren().addAll(subjectLabel, subjectComboBox);

        HBox timeFrom = new HBox();
        timeFrom.getChildren().addAll(timeFromLabel, timeFromComboBox);

        HBox timeTo = new HBox();
        timeTo.getChildren().addAll(timeToLabel, timeToComboBox);

        HBox classes = new HBox();
        classes.getChildren().addAll(classLabel, classComboBox);

        this.submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            Lesson lesson = new Lesson((Subject) subjectComboBox.getValue(), (Teacher) teacherComboBox.getValue(),
                    new LessonPeriod(timeFromComboBox.getValue().toString(),timeToComboBox.getValue().toString()),
                    (Classroom) classroomComboBox.getValue(),(ClassName) classComboBox.getValue());
            //todo: Add lessonConstraints
//            for (Lesson lesson1 : lessonObservableList){
//                if(lesson.getaClass().getName() == lesson1.getaClass().getName() && lesson.getLessonPeriod().getLessonStartTime() <= lesson1.getLessonPeriod().getLessonEndTime() )

//            }
            try {
                fileReader.writeObject(lesson, "src/objectFile/lesson/" + classComboBox.getValue() + teacherComboBox.getValue() + subjectComboBox.getValue() + classroomComboBox.getValue() + ".dat");
                fileReader.addToFile("src/TextFile/LessonPathNames.txt", "src/objectFile/lesson/" + classComboBox.getValue() + teacherComboBox.getValue() + subjectComboBox.getValue() + classroomComboBox.getValue()+ ".dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainScene.addLesson(lesson);
            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(classes,teacher, classroom, subject, timeFrom,timeTo,submitButton);
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

            File teacherFile = new File("src/TextFile/TeacherPathNames.txt");
            try (Scanner scanner = new Scanner(teacherFile)) {
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    teacherObservableList.add((Teacher) fileReader.readObject(path));
                }
            } catch (Exception e) { e.printStackTrace(); }

            File classNameFile = new File("src/TextFile/ClassnamePathNames.txt");
            try (Scanner scanner = new Scanner(classNameFile)) {
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    classNameObservableList.add((ClassName) fileReader.readObject(path));
                }
            } catch (Exception e) { e.printStackTrace(); }

            File classRoomFile = new File("src/TextFile/ClassroomPathNames.txt");
            try (Scanner scanner = new Scanner(classRoomFile)) {
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    classroomObservableList.add((Classroom) fileReader.readObject(path));
                }
            } catch (Exception e) { e.printStackTrace(); }

            File subjectFile = new File("src/TextFile/SubjectPathNames.txt");
            try (Scanner scanner = new Scanner(subjectFile)) {
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    subjectObservableList.add((Subject) fileReader.readObject(path));
                }
            } catch (Exception e) { e.printStackTrace(); }

            File lessonFile = new File("src/TextFile/LessonPathNames.txt");
            try (Scanner scanner = new Scanner(lessonFile)) {
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    lessonObservableList.add((Lesson) fileReader.readObject(path));
                }
            } catch (Exception e) { e.printStackTrace(); }

            File timesFile = fileReader.readTextFile("src/TextFile/Times.txt");

            Set<String> times = fileReader.readFile(timesFile);

            List<String> sortedTimeList = new ArrayList<>(times);
            Collections.sort(sortedTimeList);

            classroomComboBox.setItems(classroomObservableList);
            teacherComboBox.setItems(teacherObservableList);
            subjectComboBox.setItems(subjectObservableList);
            timeFromComboBox.setItems(FXCollections.observableArrayList(sortedTimeList));
            timeToComboBox.setItems(FXCollections.observableArrayList(sortedTimeList));
            classComboBox.setItems(classNameObservableList);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Button getSubmitButton(){
        return submitButton;
    }

}
