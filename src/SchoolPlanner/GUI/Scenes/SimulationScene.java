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
    private Point2D startingPoint = new Point2D.Double(1472 + 96, 2752 + 50);


    public BorderPane simulationScene () {
        init();
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        canvas.setHeight(1080);
        canvas.setWidth(1920);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.characterArrayList = new ArrayList<>();
        characterArrayList.add(new Character(startingPoint));


        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle (long now) {
                if ( last == -1 ) {
                    last = now;
                }
                update(( now - last ) * 1.0e9);
                last = now;
                draw(g2d);
            }
        }.start();

        camera = new Camera(canvas, this::draw, g2d);

        return mainPane;
    }


    public void draw (FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        if ( camera != null ) {
            g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

            map.draw(g);
            for ( Character character : this.characterArrayList ) {
                character.draw(g);
            }
        }
    }

    public void update (double deltaTime) {
        loadinCharacters();
        for ( Character character : characterArrayList ) {
            character.update(characterArrayList);
        }

    }

    private void loadinCharacters () {
        if ( characterArrayList.size() < 51 ) {
            if ( characterArrayList.get(characterArrayList.size() - 1).getDistance(startingPoint) > 32 ) {
                characterArrayList.add(new Character(startingPoint));
            }
        }
        boolean containsteacher = false;
        for ( Character character : characterArrayList ) {
            if ( character instanceof Teacher ) {
                containsteacher = true;
            }
        }
    }

    public void init () {
        this.camera = null;
        map = new Map("/maps/SchoolPlannerMap.json");
    }

}
