package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GUI extends Application {

private BorderPane mainPane;
private ResizableCanvas canvas;
private double screenHeight;
private double screenWidth;
private static ArrayList<Rectangle2D.Double> clickableRectangeList;

    public static void main(String[] args) {
        launch(GUI.class);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        this.mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        setup();
        canvas.setOnMousePressed(RectangleLogics::rectangleClicked);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GUI");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        drawGrid(graphics);
    }

    public void setup(){
        this.screenWidth = canvas.getWidth();
        this.screenHeight = canvas.getHeight();
    }


    //draws main grid with rectangles which are saved in attribute "clickableRectangeList"
    public void drawGrid(FXGraphics2D graphics) {
        clickableRectangeList = new ArrayList<>();

        int currentX;
        int currentY=0;
        for (int j = 0; j < 8; j++) {
            currentY += 50;
            for (int i = 1; i <7 ; i++) {
                currentX = (int) (i * (this.canvas.getWidth() / 10));
                graphics.setColor(Color.black);
                Rectangle2D.Double rectangle = new Rectangle2D.Double(currentX, currentY, 200, 50);
                graphics.draw(rectangle);
                clickableRectangeList.add(rectangle);
            }
        }
    }

    public static ArrayList<Rectangle2D.Double> getClickableRectangleList(){
        return clickableRectangeList;
    }
    }





