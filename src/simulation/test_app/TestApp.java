package simulation.test_app;

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

public class TestApp extends Application {


    private ResizableCanvas canvas;
    private Map map;
    private ArrayList<Character> characterArrayList;
    Camera camera = new Camera();
    Point2D startingPoint = new Point2D.Double(960,1000);


    @Override
    public void start (Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        canvas.setHeight(1080);
        canvas.setWidth(1920);
        Graphics2D g =new FXGraphics2D(canvas.getGraphicsContext2D());

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Test");
        stage.show();

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle (long now) {
                if ( last == -1)
                    last = now;
                update((now - last) * 1.0e9);
                last = now;
                draw(g);
            }
        }.start();

        canvas.setOnMouseDragged(e -> {
            try {
                camera.mouseDragged(e);
            } catch (NoninvertibleTransformException e1) {
                e1.printStackTrace();
            }
        });
        canvas.setOnScroll(e-> camera.mouseScroll(e));
        canvas.setOnMousePressed(e-> {
            try {
                camera.mousePressed(e);
            } catch (NoninvertibleTransformException e1) {
                e1.printStackTrace();
            }
        });


    }


    public void draw(Graphics2D g){
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
        g.setTransform(camera.getCameraTransform());
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

    public void init(){
        map = new Map("/maps/SchoolPlannerMap.json");
        this.characterArrayList = new ArrayList<>();
        characterArrayList.add(new Character());

    }

    public static void main (String[] args) {
        launch(TestApp.class);
    }



}
