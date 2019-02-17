package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.FileReader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;


/**
 * @Author Luke Taylor & Jelmer Surewaard & Pascal Holthuijsen
 * creates the scene for "Roster Input" tab in the main application.
 */

public class RosterInputScene {

    private FileReader fr = new FileReader();

    /**
     * @return the RosterInput borderpane.
     */

    public BorderPane rosterInputScene(){
        BorderPane borderPane = new BorderPane();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        Button button = new Button("Send");
        Button delete = new Button("Delete");

        button.setFont(new Font(70));
        delete.setFont(new Font(70));

        Label teacher = new Label("Teacher: ");
        Label lesson = new Label("Subject: ");
        Label classroom = new Label("Classroom: ");
        Label aClass = new Label("Class: ");
//        Label endTime = new Label("End time: ");
        TextField teacherTxt = new TextField();
        TextField lessonTxt = new TextField();
        TextField classroomTxt = new TextField();
        TextField classTextfield = new TextField();
//        TextField endTimeTxt = new TextField();


        button.setOnAction(event -> {
            if(!teacherTxt.getText().equals("")){
                try {
                    fr.addToFile("src/TextFile/Docent.txt", teacherTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!lessonTxt.getText().equals("")){
                try {
                    fr.addToFile("src/TextFile/Subject.txt", lessonTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!classroomTxt.getText().equals("")){
                try {
                    fr.addToFile("src/TextFile/Classrooms.txt", classroomTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            if(!classTextfield.getText().equals("")){
                try {
                    fr.addToFile("src/TextFile/Classes.txt", classTextfield.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });

        delete.setOnAction(event -> {
            if(!teacherTxt.getText().equals("")){
                try {
                    fr.removeFromFile("src/TextFile/Docent.txt", teacherTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!lessonTxt.getText().equals("")){
                try {
                    fr.removeFromFile("src/TextFile/Subject.txt", lessonTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!classroomTxt.getText().equals("")){
                try {
                    fr.removeFromFile("src/TextFile/Classrooms.txt", classroomTxt.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            if(!classTextfield.getText().equals("")){
                try {
                    fr.removeFromFile("src/TextFile/Classes.txt", classTextfield.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        });




        teacher.setFont(new Font(50));
        lesson.setFont(new Font(50));
        classroom.setFont(new Font(50));
        aClass.setFont(new Font(50));
//        endTime.setFont(new Font(50));

        teacherTxt.setFont(new Font(35));
        lessonTxt.setFont(new Font(35));
        classroomTxt.setFont(new Font(35));
        classTextfield.setFont(new Font(35));
//        endTimeTxt.setFont(new Font(35));

        button.setMinWidth(365);
        delete.setMinWidth(365);

        vBox1.getChildren().addAll(teacher, lesson, classroom, aClass);
//        vBox1.getChildren().addAll(teacher, lesson, classroom,startTime,endTime);
        vBox1.setPadding(new Insets(0,0,0,10));
        vBox2.getChildren().addAll(teacherTxt, lessonTxt, classroomTxt, classTextfield);
//        vBox2.getChildren().addAll(teacherTxt, lessonTxt, classroomTxt,startTimeTxt,endTimeTxt);
        vBox3.getChildren().addAll(button, delete);

        borderPane.setLeft(vBox1);
        borderPane.setCenter(vBox2);
        borderPane.setRight(vBox3);

        return borderPane;


    }
}
