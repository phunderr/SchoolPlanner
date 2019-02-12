package SchoolPlanner.GUI.Scenes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author Stijn van Berkel & Jelmer Surewaard
 * Creates a pop-up panel with 4 comboboxes containing:
 * teachers, classrooms, subjects, timestamps.
 */

public class PopUpScene extends Application {

    private ComboBox teacherComboBox;
    private ComboBox classroomComboBox;
    private ComboBox subjectComboBox;
    private ComboBox timestampComboBox;

    @Override
    public void start(Stage stage) {

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
        this.subjectComboBox = new ComboBox();
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
    }
}
