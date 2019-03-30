package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.ClassName;
import SchoolPlanner.Data.Classroom;
import SchoolPlanner.Data.Lesson;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import simulation.NPC.Character;
import simulation.NPC.Student;
import simulation.NPC.Teacher;
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
import java.util.*;

public class SimulationScene {


    private ResizableCanvas canvas;
    private Map map;
    private ArrayList<Character> characterArrayList;
    private Camera camera;
    private Point2D startingPoint = new Point2D.Double(1472 + 96 / 2, 2752);
    private Shape timerShape;
    private long timeNow;
    private Font font;
    public static LocalTime schoolTime;
    private ArrayList<Lesson> lessons;
    private ArrayList<ClassName> classNames;
    private ArrayList<Classroom> listClassRooms;
    private ArrayList<Classroom> listClassRoomsTeachers;
    private ArrayList<SchoolPlanner.Data.Teacher> teachers;
    private boolean isStarted = false;
    private HashMap<String, ArrayList<Point2D.Double>> klasLocations = new HashMap<>();
    private int characterClassIterator = 0;

    private int klasAIterator = 0;
    private int klasBIterator = 0;
    private int klasCIterator = 0;
    private int klasDIterator = 0;
    private int teacherIterator = 0;

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
                if (isStarted)
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
            if (character instanceof Student) {
                character.setTarget(new Point2D.Double(1120, 1440));
                for (ClassName className : classNames) {
                    if (((Student) character).getClassName().getName().equals(className.getName())) {
                        for (Lesson lesson : lessons) {
                            if (lesson.getaClass().getName().equals(className.getName())) {
                                if (Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) == schoolTime.getHour() ||
                                        Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) <= schoolTime.getHour() &&
                                                Integer.parseInt(lesson.getLessonPeriod().getLessonEndTime().substring(0, 2)) > schoolTime.getHour()) {
                                    for (Classroom classroom : listClassRooms) {
                                        for (Location location : Map.locations) {
                                            if (location.getName().equals(classroom.getClassID()) && classroom.getClassID().equals(lesson.getClassroom().getClassID())) {
                                                for (String classRoomName : ((Student) character).getLocationInClassRoom().keySet()) {
                                                    if (classroom.getClassID().equals(classRoomName)) {
                                                        character.setTarget(((Student) character).getLocationInClassRoom().get(classRoomName));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (character instanceof Teacher){
                for (Lesson lesson : lessons){
                    for (SchoolPlanner.Data.Teacher teacher : teachers){
                        if (lesson.getTeacher().getName().equals(teacher.getName())){
                            if (Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) == schoolTime.getHour() ||
                                    Integer.parseInt(lesson.getLessonPeriod().getLessonStartTime().substring(0, 2)) <= schoolTime.getHour() &&
                                            Integer.parseInt(lesson.getLessonPeriod().getLessonEndTime().substring(0, 2)) > schoolTime.getHour()) {
                                    for (Classroom classroom : listClassRoomsTeachers){
                                        for (Location location : Map.locations) {
                                            if (location.getName().equals(classroom.getClassID()) && classroom.getClassID().equals(lesson.getClassroom().getClassID() + "Teacher")) {
                                                for (String classRoomName : ((Teacher) character).getLocationInClassRoom().keySet()) {
                                                    if (classroom.getClassID().equals(classRoomName)) {
                                                        character.setTarget(((Teacher) character).getLocationInClassRoom().get(classRoomName));
                                                    }

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
    }


    private void loadingCharacters() {
        int currentAmountOfStudents = 0;
        if (characterArrayList.size() < 72 + teachers.size()) {

            if (klasAIterator == 18) {
                klasAIterator = 0;
                characterClassIterator++;
            }
            for (int i = 0; i < characterArrayList.size(); i++) {
                if (characterArrayList.get(i) instanceof Student) {
                    if (((Student) characterArrayList.get(i)).getClassName().getName().equals(classNames.get(characterClassIterator).getName())) {
                        currentAmountOfStudents++;
                    }
                }
                if (characterArrayList.get(characterArrayList.size() - 1).getDistance(startingPoint) > 32) {

                    HashMap<String, Point2D.Double> teacherLocationPerClass = new HashMap<>();
                    teacherLocationPerClass.put("LA101Teacher", new Point2D.Double(1904, 2992));
                    teacherLocationPerClass.put("LA102Teacher", new Point2D.Double(2224, 2992));
                    teacherLocationPerClass.put("LA103Teacher", new Point2D.Double(1904, 1968));
                    teacherLocationPerClass.put("LA104Teacher", new Point2D.Double(1136, 1968));

                    for (SchoolPlanner.Data.Teacher t : teachers) {
                        if (teacherIterator < teachers.size()){
                            characterArrayList.add(new Teacher(startingPoint, t.getName(), teacherLocationPerClass));
                            teacherIterator++;
                            continue;
                        }
                    }
                    if (classNames.get(characterClassIterator).getNumberOfStudents() > currentAmountOfStudents) {
                        HashMap<String, Point2D.Double> studentLocationPerClass = new HashMap<>();
                        studentLocationPerClass.put("LA101", klasLocations.get("LA101").get(klasAIterator));
                        klasAIterator++;
                        studentLocationPerClass.put("LA102", klasLocations.get("LA102").get(klasBIterator));
                        klasBIterator++;
                        klasBIterator %= 18;
                        studentLocationPerClass.put("LA103", klasLocations.get("LA103").get(klasCIterator));
                        klasCIterator++;
                        klasCIterator %= 18;
                        studentLocationPerClass.put("LA104", klasLocations.get("LA104").get(klasDIterator));
                        klasDIterator++;
                        klasDIterator %= 18;
                        characterArrayList.add(new Student(startingPoint, classNames.get(characterClassIterator), studentLocationPerClass));
                        continue;
                    }


                    System.out.println(klasLocations);
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
                this.timerShape = font.createGlyphVector(g2d.getFontRenderContext(), (startTime + elapsedminutesDisplay) + ":0" + elapsedsecondsDisplay).getOutline();
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

    public HashMap<String, ArrayList<Point2D.Double>> fillClassHashmap() {
        HashMap<String, ArrayList<Point2D.Double>> mapHashMap = new HashMap<>();
        for (Classroom classroom : listClassRooms) {
            ArrayList<Point2D.Double> locations = new ArrayList<>();
            for (Location location : Map.locations) {
                if (location.getName().equals(classroom.getClassID())) {
                    if (location.getName().equals("LA104") || location.getName().equals("LA103")) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 6; j++) {
                                switch (j) {
                                    case 0:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13, location.getLocation().getY() + 48 + (i * 96)));
                                        break;
                                    case 1:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13 + 32, location.getLocation().getY() + 48 + (i * 96)));
                                        break;
                                    case 2:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13 + (2 * 32) + 32, location.getLocation().getY() + 48 + (i * 96)));
                                        break;
                                    case 3:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13 + (3 * 32) + 32, location.getLocation().getY() + 48 + (i * 96)));
                                        break;
                                    case 4:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13 + (4 * 32) + 64, location.getLocation().getY() + 48 + (i * 96)));
                                        break;
                                    case 5:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 13 + (5 * 32) + 64, location.getLocation().getY() + 48 + (i * 96)));
                                        break;

                                }
                            }
                        }
                    } else if(location.getName().equals("LA102") || location.getName().equals("LA101")){
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 6; j++) {
                                switch (j) {
                                    case 0:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18, location.getLocation().getY() + 42 + (i * 96)));
                                        break;
                                    case 1:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18 + 32, location.getLocation().getY() + 42 + (i * 96)));
                                        break;
                                    case 2:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18 + (2 * 32) + 32, location.getLocation().getY() + 42 + (i * 96)));
                                        break;
                                    case 3:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18 + (3 * 32) + 32, location.getLocation().getY() + 42 + (i * 96)));
                                        break;
                                    case 4:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18 + (4 * 32) + 64, location.getLocation().getY() + 42 + (i * 96)));
                                        break;
                                    case 5:
                                        locations.add(new Point2D.Double(location.getLocation().getX() + 18 + (5 * 32) + 64, location.getLocation().getY() + 42 + (i * 96)));
                                        break;

                                }
                            }
                        }
                    }
                }
            }
            mapHashMap.put(classroom.getClassID(), locations);
        }
        System.out.println(listClassRooms);
        return mapHashMap;
    }


    public void init() {
        lessons = getLesson();
        classNames = getClasses();
        listClassRooms = getClassRooms();
        teachers = getTeachers();
        listClassRoomsTeachers = getClassRoomsTeachers();

        this.camera = null;
        map = new Map("/maps/SchoolPlannerMap.json");
        klasLocations = fillClassHashmap();
    }

    private ArrayList<Classroom> getClassRoomsTeachers() {
        ArrayList<Classroom> listClassRoomsTeachers = new ArrayList<Classroom>();
        Classroom classroom101 = new Classroom("LA101Teacher");
        Classroom classroom102 = new Classroom("LA102Teacher");
        Classroom classroom103 = new Classroom("LA103Teacher");
        Classroom classroom104 = new Classroom("LA104Teacher");
        listClassRoomsTeachers.add(classroom101);
        listClassRoomsTeachers.add(classroom102);
        listClassRoomsTeachers.add(classroom103);
        listClassRoomsTeachers.add(classroom104);

        return listClassRoomsTeachers;
    }

    private ArrayList<SchoolPlanner.Data.Teacher> getTeachers() {
        ArrayList teachers = new ArrayList<>();
        ArrayList<File> files = loadFiles("src\\objectFile\\teacher");
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getAbsolutePath());
            SchoolPlanner.Data.Teacher teacher;
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                teacher = (SchoolPlanner.Data.Teacher) reader.readObject();
                teachers.add(teacher);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return teachers;

    }

    public void setStarted() {
        isStarted = true;
    }


    //events


}
