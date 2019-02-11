package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import SchoolPlanner.GUI.TestData.Group;


public class ApplicationMain extends javafx.application.Application {

   private Canvas mainCanvas;
   private ObservableList<SchoolPlanner.GUI.TestData.Group> groupList;



    public static void main(String[] args) {
        launch(ApplicationMain.class);
    }
    public void start(Stage stage){
        initialize(stage);
        drawGrid();

    }

    public void initialize(Stage stage){
        this.mainCanvas = new Canvas();
        stage.setScene(new Scene(new javafx.scene.Group(mainCanvas)));
        stage.setTitle("Rooster");
        mainCanvas.setHeight(1080);
        mainCanvas.setWidth(1920);
        stage.show();
    }

    public void drawGrid(){
        for (int i = 0; i < 3; i++) {
            groupList.add(new SchoolPlanner.GUI.TestData.Group("class"+i));
        }
        for (Group g:groupList) {
            System.out.println(g.getName());
        }
    }

    }
