package SchoolPlanner.GUI.Scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @Author Pascal Holthuijsen
 */
public class SimulationScene {

    private Graphics2D g;

    public BorderPane simulationScene(){

        BorderPane simulationPane = new BorderPane();
        ResizableCanvas canvas = new ResizableCanvas(this::draw, simulationPane);
        g = new FXGraphics2D(canvas.getGraphicsContext2D());
        simulationPane.setCenter(canvas);


        return simulationPane;
    }

    public void draw(Graphics2D g){
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, 1980, 1080);


    }

    public void update(double deltaTime){

    }


    public void run(){
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle (long now) {
                if ( last == -1 )
                    last = now;
                update((now - last) * 1.0e9);
                last = now;
                draw(g);
            }
        }.start();


    }

}
