package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.Lesson;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
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
    public static LocalTime schoolTime;
    private ArrayList<Lesson> lessons;

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
        if (timerShape != null) {
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

    private void loadinCharacters() {
        if (characterArrayList.size() < 51) {
            if (characterArrayList.get(characterArrayList.size() - 1).getDistance(startingPoint) > 32) {
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
        long elapsedminutesDisplay = elapsedminutes % 9;


        if (elapsedsecondsDisplay > 9) {
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (starTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
            schoolTime = LocalTime.of(starTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            if ((starTime + elapsedminutesDisplay) > 9) {
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (starTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
                schoolTime = LocalTime.of(starTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            }
        } else {
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (starTime + elapsedminutesDisplay) + ":0" + elapsedsecondsDisplay).getOutline();
            schoolTime = LocalTime.of(starTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            if ((starTime + elapsedminutesDisplay) > 9) {
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (starTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
                schoolTime = LocalTime.of(starTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            }
        }


    }

    public ArrayList<File> loadFiles(){
        File folder = new File("src\\objectFile\\lesson");
        File[] listOfFiles = folder.listFiles();
        ArrayList<File> files = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files.add(listOfFiles[i]);
            }
        }
        return files;
    }

    public ArrayList<Lesson> getLesson() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<File> files = loadFiles();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getAbsolutePath());
            Lesson lesson = null;
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                lesson = (Lesson) reader.readObject();
                lessons.add(lesson);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return lessons;
    }

    public void init() {
        lessons = getLesson();
        System.out.println(lessons);
        this.camera = null;
        map = new Map("/maps/SchoolPlannerMap.json");
    }

}
