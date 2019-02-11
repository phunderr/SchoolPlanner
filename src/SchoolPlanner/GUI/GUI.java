package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GUI extends Application {

private BorderPane mainPane;
private ResizableCanvas canvas;

    public static void main(String[] args) {
        launch(GUI.class);
    }

    public void start(Stage primaryStage) throws Exception {
        this.mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GUI");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fill(new Rectangle2D.Double(100,100,100,100) {});
    }

    public void setup(){

    }




}

