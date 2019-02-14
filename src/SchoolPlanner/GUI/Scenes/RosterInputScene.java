package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.FileReader;
import SchoolPlanner.Data.Lesson;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author Luke Taylor & Jelmer Surewaard
 * creates the scene for "Roster Input" tab in the main application.
 */

public class RosterInputScene {

    /**
     * @return the RosterInput borderpane.
     */

    public BorderPane rosterInputScene(){
        BorderPane borderPane = new BorderPane();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        Button button = new Button("Send");



        button.setFont(new Font(105));

        Label teacher = new Label("Teacher: ");
        Label lesson = new Label("Subject: ");
        Label classroom = new Label("Classroom: ");
        TextField teachertxt = new TextField();
        TextField lessontxt = new TextField();
        TextField classroomtxt = new TextField();

//        button.setOnAction(event -> {
//            if(!teachertxt.getText().contains("")){
//
//            }
//        });


        teacher.setFont(new Font(50));
        lesson.setFont(new Font(50));
        classroom.setFont(new Font(50));

        teachertxt.setFont(new Font(35));
        lessontxt.setFont(new Font(35));
        classroomtxt.setFont(new Font(35));

        vBox1.getChildren().addAll(teacher, lesson, classroom);
        vBox2.getChildren().addAll(teachertxt, lessontxt, classroomtxt);

        borderPane.setLeft(vBox1);
        borderPane.setCenter(vBox2);
        borderPane.setRight(button);

        return borderPane;


    }
}
