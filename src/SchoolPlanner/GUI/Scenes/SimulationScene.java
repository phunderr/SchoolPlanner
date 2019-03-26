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
    private Shape timerShape;
    private long timeNow;
    private Font font;

    public BorderPane simulationScene() {
        init();
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        canvas.setHeight(1080);
        canvas.setWidth(1920);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.timeNow = System.currentTimeMillis();
        this.font = new Font("Arial", Font.PLAIN, 30);
        this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "08:00").getOutline();

        this.characterArrayList = new ArrayList<>();
        characterArrayList.add(new Character(startingPoint));


        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) * 1.0e9, g2d);
                last = now;
                draw(g2d);
            }
        }.start();

        camera = new Camera(canvas, this::draw, g2d);

        return mainPane;
    }


    public void draw(FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        if(timerShape != null) {
            g.draw(AffineTransform.getTranslateInstance(100, 100).createTransformedShape(timerShape));
        }
        if (camera != null) {
            g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

            map.draw(g);

            for (Character character : this.characterArrayList) {
                character.draw(g);
            }
        }
    }

    public void update(double deltaTime, FXGraphics2D g2d) {
        loadinCharacters();
        updateTimer(deltaTime, g2d);
        for (Character character : characterArrayList) {
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
        for (Character character : characterArrayList) {
            if (character instanceof Teacher) {
                containsteacher = true;
            }
        }
    }

    public void updateTimer(double deltaTime, FXGraphics2D g2d) {

        int starTime = 8;
        long newTime = System.currentTimeMillis();
        long elapsedTime = newTime - timeNow;
        long elapsedseconds = elapsedTime / 1000;
        long elapsedsecondsDisplay = elapsedseconds % 60;
        long elapsedminutes = elapsedseconds / 60;


        if(elapsedsecondsDisplay > 9){
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (starTime + elapsedminutes) + ":" + elapsedsecondsDisplay).getOutline();
            if((starTime + elapsedminutes) > 9){
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (starTime + elapsedminutes) + ":" + elapsedsecondsDisplay).getOutline();
            }
        } else {
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (starTime + elapsedminutes) + ":0" + elapsedsecondsDisplay).getOutline();
            if((starTime + elapsedminutes) > 9){
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (starTime + elapsedminutes) + ":" + elapsedsecondsDisplay).getOutline();
            }
        }





//        System.out.println(elapsedseconds);
//        if(System.currentTimeMillis() % 1000 >= 970) {
//            secondsIterator++;
//            if (secondsIterator < 10) {
//
//            } else if(secondsIterator == 59){
//                secondsIterator = 0;
//                hoursIterator++;
//            } else {
//                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + hoursIterator + ":" + secondsIterator).getOutline();
//            }
//        }
//
//        if (newTime - timeNow > 1000) {
//            if(secondsIterator < 10){
//                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + hoursIterator + ":0" + secondsIterator).getOutline();
//            } else if(secondsIterator == 59){
//                secondsIterator = 0;
//            } else {
//                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + hoursIterator + ":" + secondsIterator).getOutline();
//            }
////            if (hoursIterator < 10) {
////                hoursIterator++;
////            } else {
////                if(secondsIterator<10){
////                    this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(),hoursIterator + ":0" + secondsIterator).getOutline();
////                } else {
////                    this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), hoursIterator + ":" + secondsIterator).getOutline();
////                }
////                hoursIterator++;
////            }
////            if (hoursIterator > 17) {
////                hoursIterator = 8;
////            }
//            timeNow = System.currentTimeMillis();
        }


    public void init() {
        this.camera = null;
        map = new Map("/maps/SchoolPlannerMap.json");
    }

}
