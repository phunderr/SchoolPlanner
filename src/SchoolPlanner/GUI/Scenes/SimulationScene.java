package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.ClassName;
import SchoolPlanner.Data.Classroom;
import SchoolPlanner.Data.Lesson;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import simulation.NPC.Character;
import simulation.NPC.Student;
import simulation.tiled.Camera;
import simulation.tiled.Location;
import simulation.tiled.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ClassName> classNames;
    private ArrayList<Classroom> list;
    private boolean isStarted = false;

    BorderPane simulationScene() {
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
                if ( isStarted )
                    update((now - last) * 1.0e9, g2d);
                last = now;
                draw(g2d);

            }
        }.start();

        camera = new Camera(canvas, this::draw, g2d, this);

        return mainPane;
    }


    public void draw(FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        if (camera != null) {
            g.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

            map.draw(g);

            for (Character character : this.characterArrayList) {
                character.draw(g);
            }
        }
        if (timerShape != null) {
            g.setTransform(new AffineTransform());
            g.draw(AffineTransform.getTranslateInstance(100, 100).createTransformedShape(timerShape));
        }

    }

    public void update(double deltaTime, FXGraphics2D g2d) {
        loadingCharacters();
        updateTimer(deltaTime, g2d);
        for (Character character : characterArrayList) {
            character.setTarget(new Point2D.Double(1120, 1440));
            if (character instanceof Student) {
                for (ClassName className : classNames) {
                    if (((Student) character).getClassName().getName().equals(className.getName())) {
                        for (Lesson lesson : lessons) {
                            if (lesson.getaClass().getName().equals(className.getName())) {
                                if (Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) == schoolTime.getHour() ||
                                    Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) <= schoolTime.getHour() &&
                                    Integer.parseInt(lesson.getLessonPeriod().getLessonEndTime().substring(0, 2)) > schoolTime.getHour()

                                    ) {
                                    for (Classroom classroom : list ) {
                                        for (Location location : Map.locations) {
                                            if (location.getName().equals(classroom.getClassID()) && classroom.getClassID().equals(lesson.getClassroom().getClassID())) {
                                                character.setTarget(new Point2D.Double(location.getLocation().getX() + 100 , location.getLocation().getY() + 100));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            character.update(characterArrayList);
        }
        canvas.requestFocus();
        canvas.setOnMouseClicked(e -> System.out.println("sup"));
    }




    private void loadingCharacters () {
        int currentAmountOfStudents = 0;
        if (characterArrayList.size() < 13) {

            int r = ((int) (Math.random() * classNames.size()));
            for (int i = 0; i < characterArrayList.size(); i++) {
                if (characterArrayList.get(i) instanceof Student) {
                    if (((Student) characterArrayList.get(i)).getClassName().getName().equals(classNames.get(r).getName())) {
                        currentAmountOfStudents++;
                    }
                }
                if (characterArrayList.get(characterArrayList.size() - 1).getDistance(startingPoint) > 32) {
                    if (classNames.get(r).getNumberOfStudents() > currentAmountOfStudents) {
                        characterArrayList.add(new Student(startingPoint, classNames.get(r)));
                    }
                }
            }
        }
    }

    public void updateTimer(double deltaTime, FXGraphics2D g2d) {

        int startTime = 8;
        long newTime = System.currentTimeMillis();
        long elapsedTime = newTime - timeNow;
        long elapsedseconds = elapsedTime / 500;
        long elapsedsecondsDisplay = elapsedseconds % 60;
        long elapsedminutes = elapsedseconds / 60;
        long elapsedminutesDisplay = elapsedminutes % 9;
        System.out.println(newTime);

        if (elapsedsecondsDisplay > 9) {
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (startTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
            schoolTime = LocalTime.of(startTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            if ((startTime + elapsedminutesDisplay) > 9) {
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (startTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
                schoolTime = LocalTime.of(startTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            }
        } else {
            this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), "0" + (startTime + elapsedminutesDisplay) + ":0" + elapsedsecondsDisplay).getOutline();
            schoolTime = LocalTime.of(startTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            if ((startTime + elapsedminutesDisplay) > 9) {
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (startTime + elapsedminutesDisplay) + ":" + elapsedsecondsDisplay).getOutline();
                schoolTime = LocalTime.of(startTime + (int) elapsedminutesDisplay, (int) elapsedsecondsDisplay);
            }
        }


    }

    public ArrayList<File> loadFiles(String pathname) {
        File folder = new File(pathname);
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
        ArrayList<File> files = loadFiles("src\\objectFile\\lesson");
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getAbsolutePath());
            Lesson lesson;
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

    public ArrayList<ClassName> getClasses() {
        ArrayList<ClassName> classNames = new ArrayList<>();
        ArrayList<File> files = loadFiles("src\\objectFile\\className");
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getAbsolutePath());
            ClassName className;
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                className = (ClassName) reader.readObject();
                classNames.add(className);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return classNames;
    }

    public ArrayList<Classroom> getClassRooms() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        ArrayList<File> files = loadFiles("src\\objectFile\\classroom");
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getAbsolutePath());
            Classroom classroom;
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                classroom = (Classroom) reader.readObject();
                classrooms.add(classroom);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return classrooms;
    }




    public void init() {
        lessons = getLesson();
        classNames = getClasses();
        list = getClassRooms();

        System.out.println(lessons);
        this.camera = null;
        map = new Map("/maps/SchoolPlannerMap.json");
    }

    public void setStarted () {
        isStarted = true;
    }


    //events


}
