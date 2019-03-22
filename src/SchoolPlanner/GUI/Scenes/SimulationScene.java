package SchoolPlanner.GUI.Scenes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import simulation.NPC.Character;
import simulation.NPC.Teacher;
import simulation.tiled.Camera;
import simulation.tiled.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SimulationScene {


    private ResizableCanvas canvas;
    private Map map;
    private ArrayList<Character> characterArrayList;
    private Camera camera;
    private Point2D startingPoint = new Point2D.Double(960,1000);


    public BorderPane simulationScene (){
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        canvas.setHeight(1080);
        canvas.setWidth(1920);
        FXGraphics2D g2d =new FXGraphics2D(canvas.getGraphicsContext2D());
        map = new Map("/maps/SchoolPlannerMap.json");
        this.characterArrayList = new ArrayList<>();
        characterArrayList.add(new Character());


        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle (long now) {
                if ( last == -1)
                    last = now;
                update((now - last) * 1.0e9);
                last = now;
                draw(g2d);
            }
        }.start();

        camera = new Camera(canvas, g -> draw(g), g2d);

        return mainPane;
    }


    public void draw(FXGraphics2D g){
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
        g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

        map.draw(g);
        for (Character character : this.characterArrayList) {
            character.draw(g);
        }
    }

    public void update(double deltaTime){
        loadinCharacters();
        for (Character character : characterArrayList) {
            character.update(characterArrayList);
        }

    }
    private void loadinCharacters(){
        if(characterArrayList.size() < 10){
            if(characterArrayList.get(characterArrayList.size()-1).getDistance(startingPoint) > 32) {
                characterArrayList.add(new Character());
            }
        }
        boolean containsteacher = false;
        for (Character character : characterArrayList) {
            if (character instanceof Teacher){
                containsteacher = true;
            }
        }
    }

}
