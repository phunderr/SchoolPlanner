package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.FileReader;
import SchoolPlanner.Data.Lesson;
import SchoolPlanner.Data.Teacher;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author Luke Taylor
 * creates the scene for "Rooster Input" tab in the main application.
 */

public class RoosterInputScene {
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        Label teacher = new Label("Teacher: ");
        Label lesson = new Label("Lesson: ");
        Label classroom = new Label("Classroom: ");
        TextField teachertxt = new TextField();
        TextField lessontxt = new TextField();
        TextField classroomtxt = new TextField();

        teacher.setFont(new Font(17));
        lesson.setFont(new Font(17));
        classroom.setFont(new Font(17));

        vBox1.getChildren().add(teacher);
        vBox1.getChildren().add(lesson);
        vBox1.getChildren().add(classroom);
        vBox2.getChildren().add(teachertxt);
        vBox2.getChildren().add(lessontxt);
        vBox2.getChildren().add(classroomtxt);


        borderPane.setRight(vBox2);
        borderPane.setCenter(vBox1);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
