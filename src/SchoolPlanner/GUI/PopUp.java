package SchoolPlanner.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PopUp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Add Lesson");
        stage.setMinWidth(400);
        stage.setMinHeight(200);

        HBox hBoxLabel = new HBox();

        Label teacherLabel = new Label("Teacher:");
        teacherLabel.setMinWidth(100);
        Label classroomLabel = new Label("Classroom:");
        classroomLabel.setMinWidth(100);
        Label courseLabel = new Label("Course:");
        courseLabel.setMinWidth(100);
        Label timestampLabel = new Label("Timestamp:");
        timestampLabel.setMinWidth(100);


        HBox hBoxComboBox = new HBox();

        ComboBox teacherComboBox = new ComboBox();
        ComboBox classroomComboBox = new ComboBox();
        ComboBox courseComboBox = new ComboBox();
        ComboBox timestampComboBox = new ComboBox();

        HBox teacher = new HBox();
        teacher.getChildren().addAll(teacherLabel, teacherComboBox);

        HBox classroom = new HBox();
        classroom.getChildren().addAll(classroomLabel, classroomComboBox);

        HBox course = new HBox();
        course.getChildren().addAll(courseLabel, courseComboBox);

        HBox timestamp = new HBox();
        timestamp.getChildren().addAll(timestampLabel, timestampComboBox);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(teacher, classroom, course, timestamp);

        vBox.getChildren().addAll(hBoxLabel, hBoxComboBox);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(vBox);

        

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
