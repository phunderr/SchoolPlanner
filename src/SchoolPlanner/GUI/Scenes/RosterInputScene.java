package SchoolPlanner.GUI.Scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


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
        Label startTime = new Label("Start time: ");
        Label endTime = new Label("End time: ");
        TextField teacherTxt = new TextField();
        TextField lessonTxt = new TextField();
        TextField classroomTxt = new TextField();
        TextField startTimeTxt = new TextField();
        TextField endTimeTxt = new TextField();

//        button.setOnAction(event -> {
//            if(!teachertxt.getText().contains("")){
//
//            }
//        });


        teacher.setFont(new Font(50));
        lesson.setFont(new Font(50));
        classroom.setFont(new Font(50));
        startTime.setFont(new Font(50));
        endTime.setFont(new Font(50));

        teacherTxt.setFont(new Font(35));
        lessonTxt.setFont(new Font(35));
        classroomTxt.setFont(new Font(35));
        startTimeTxt.setFont(new Font(35));
        endTimeTxt.setFont(new Font(35));

        vBox1.getChildren().addAll(teacher, lesson, classroom,startTime,endTime);
        vBox1.setPadding(new Insets(0,0,0,10));
        vBox2.getChildren().addAll(teacherTxt, lessonTxt, classroomTxt,startTimeTxt,endTimeTxt);

        borderPane.setLeft(vBox1);
        borderPane.setCenter(vBox2);
        borderPane.setRight(button);

        return borderPane;


    }
}
