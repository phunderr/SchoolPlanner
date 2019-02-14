package SchoolPlanner.GUI.Scenes;


import SchoolPlanner.Data.Class;
import SchoolPlanner.GUI.Logics.RectangleLogics;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MainScene extends Application {

private BorderPane mainPane;
private ResizableCanvas canvas;
private double screenHeight;
private double screenWidth;
private static ArrayList<Rectangle2D.Double> clickableRectangeList;
private TabPane mainTabPane;
private VBox timeVBox;

    public static void main(String[] args) {
        launch(MainScene.class);
    }

    public void start(Stage primaryStage){
        ArrayList<Class> classes = new ArrayList<>();

        for ( int i = 0; i<= 5; i++){
            classes.add(new Class("class " + (i +1)));
        }



        primaryStage.setMaximized(true);
        this.mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        mainPane.setTop(placeClasses(classes));
        setup();
        //primaryStage.setFullScreen(true);
        canvas.setOnMousePressed(this::onMousePressed);
        primaryStage.setScene(new Scene(mainTabPane));
        primaryStage.setTitle("Rooster Application");
        primaryStage.show();
        primaryStage.setResizable(false);

        new PopUpScene().generatePopUp(new Stage());
    }

    public void draw(FXGraphics2D graphics) {
        drawGrid(graphics);

    }

    public void setup(){
        this.screenWidth = canvas.getWidth();
        this.screenHeight = canvas.getHeight();

        //main tab pane setup
        this.mainTabPane = new TabPane();
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab roosterTab = new Tab("Rooster");
        Tab rosterInputTab = new Tab("Rooster Input");
        Tab simulationTab = new Tab("Simulation");
        mainTabPane.getTabs().addAll(roosterTab,rosterInputTab,simulationTab);
        roosterTab.setContent(mainPane);

        //this.mainPane.setTop(mainTabPane);

        //RosterInputTab
        rosterInputTab.setContent(new RosterInputScene().rosterInputScene());


        //left vbox setup
        this.timeVBox = new VBox();
        timeVBox.setAlignment(Pos.CENTER_LEFT);
        timeVBox.setSpacing(this.canvas.getHeight()/6);
        for (int i = 0; i < 8 ; i++) {
            Label label = new Label((i+8)+":00");
            label.setFont(new Font("Serif",20));
            this.timeVBox.getChildren().add(label);
        }
        this.mainPane.setLeft(timeVBox);
    }


    //draws main grid with rectangles which are saved in attribute "clickableRectangleList"
    public void drawGrid(FXGraphics2D graphics) {
        clickableRectangeList = new ArrayList<>();

        int currentX;
        int currentY=0;
        for (int j = 1; j < 9 ; j++) {
        currentY = (int)(j*(this.canvas.getHeight()/10));
            for (int i = 1; i < 7; i++) {
                currentX = (int) (i * (this.canvas.getWidth() / 10));
                graphics.setColor(Color.black);
                Rectangle2D.Double rectangle = new Rectangle2D.Double(currentX, currentY, this.canvas.getWidth() / 10, this.canvas.getHeight()/10);
                graphics.draw(rectangle);
                clickableRectangeList.add(rectangle);
            }
        }
    }

    public static ArrayList<Rectangle2D.Double> getClickableRectangleList(){
        return clickableRectangeList;
    }

    public HBox placeClasses(ArrayList<Class> classes){
        HBox hbox = new HBox();
        hbox.setSpacing(canvas.getWidth() / 7);
        Label label = new Label(" ");
        label.setPrefWidth(160);
        hbox.getChildren().add(label);
        for ( Class aClass : classes ) {
            Label l  =new Label(aClass.getClassID());
            l.setFont(new Font(35));
            hbox.getChildren().add(l);
        }

        return hbox;
    }

    //event handelers

    public void onMousePressed(MouseEvent e){
        RectangleLogics.rectangleClicked(e);


    }



}





