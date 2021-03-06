package SchoolPlanner.GUI.Scenes;


import SchoolPlanner.Data.*;
import SchoolPlanner.GUI.Logics.LessonButton;
import SchoolPlanner.GUI.Logics.DrawableShape;
import SchoolPlanner.GUI.Logics.LessonRectangle;
import SchoolPlanner.GUI.Logics.RectangleLogics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;


public class MainScene extends Application {

    private BorderPane mainPane;
    private ResizableCanvas canvas;
    private double screenHeight;
    private double screenWidth;
    private static ArrayList<Rectangle2D.Double> clickableRectangeList;
    private TabPane mainTabPane;
    private ArrayList<DrawableShape> drawableShapes;
    private DrawableShape ds;
    private AddLessonPopUpScene addLessonPopUpScene;
    private GeneralPath plusSymbolPath;
    private static ArrayList<Lesson> lessons;
    private ArrayList<LessonRectangle> lessonRectangles;
    private ArrayList<String> classesList;
    private BufferedImage image;
    private boolean removeState = false;
    private FileReader fileReader;

    @Override
    public void start(Stage primaryStage) {
        setup();
        primaryStage.setMaximized(true);
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        try {
            image = ImageIO.read(getClass().getResource("/trashcan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
//      primaryStage.setFullScreen(true);
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(this::onMouseReleased);
        canvas.setOnMouseMoved(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
//      addLessonPopUpScene.getSubmitButton().setOnAction(this::createLesson);
        primaryStage.setScene(new Scene(mainTabPane));
        primaryStage.setTitle("Rooster Application");
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);

        canvas.setOnMouseMoved(this::onMouseMoved);
    }

    /**
     * draw draws the GUI components on the canvas
     *
     * @param graphics
     */
    public void draw(FXGraphics2D graphics) {
        this.screenWidth = canvas.getWidth();
        this.screenHeight = canvas.getHeight();
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        //draws the roster
        drawClasses(graphics);

        AffineTransform tx = new AffineTransform();
        tx.translate(14, 912);
        tx.scale(0.16f, 0.16f);
        graphics.drawImage(image, tx, null);
        //draws button + rosterblock
        LessonButton ls = new LessonButton(new Ellipse2D.Double(1840, 915, 75, 75), addLessonPopUpScene, Color.GREEN, new Stage(), screenWidth, screenHeight);
        ls.draw(graphics);
        for (DrawableShape drawableShape : drawableShapes) {
            drawableShape.draw(graphics);
        }

        //draws the plus sign
        graphics.setColor(Color.black);
        graphics.fill(plusSymbolPath);

        if (!lessons.isEmpty()) {
            for (Lesson lesson : lessons) {
                LessonRectangle rect = new LessonRectangle(lesson, RectangleLogics.getRectangleIndex(classesList.indexOf(lesson.getaClass().getName())));
                lessonRectangles.add(rect);
                rect.draw(graphics);
            }
        }
    }

    /**
     * setup() initializes JavaFX components of the GUI
     */
    public void setup() {
        this.lessonRectangles = new ArrayList<>();
        this.fileReader = new FileReader();
        this.lessons = new ArrayList<>();
        this.drawableShapes = new ArrayList<>();
        this.addLessonPopUpScene = new AddLessonPopUpScene();
        this.mainPane = new BorderPane();
        this.mainTabPane = new TabPane();
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab rosterTab = new Tab("Roster");
        Tab rosterInputTab = new Tab("Roster Input");
        Tab simulationTab = new Tab("Simulation");
        mainTabPane.getTabs().addAll(rosterTab, rosterInputTab, simulationTab);
        rosterTab.setContent(mainPane);
        rosterInputTab.setContent(new RosterInputScene().rosterInputScene());
        simulationTab.setContent(new SimulationScene().simulationScene());

        File lessonFile = new File("src/TextFile/LessonPathNames.txt");
        try (Scanner scanner = new Scanner(lessonFile)) {
            while (scanner.hasNext()){
                String path = scanner.nextLine();
                lessons.add((Lesson) fileReader.readObject(path));
            }
        } catch (Exception e) { e.printStackTrace(); }


        this.drawableShapes.add(new LessonButton(new Ellipse2D.Double(1840, 915, 75, 75), addLessonPopUpScene, Color.green, new Stage(), screenWidth, screenHeight));

        //creates a plus sign path in the clickable circle on the bottom right
        GeneralPath path = new GeneralPath();
        path.moveTo(1840 + 33, 915 + 16.5);
        path.lineTo(1840 + 42, 915 + 16.5);
        path.lineTo(1840 + 42, 930 + 16.5);
        path.lineTo(1840 + 42 + 15, 930 + 16.5);
        path.lineTo(1840 + 42 + 15, 939 + 16.5);
        path.lineTo(1840 + 42, 939 + 16.5);
        path.lineTo(1840 + 42, 939 + 15 + 16.5);
        path.lineTo(1840 + 33, 939 + 15 + 16.5);
        path.lineTo(1840 + 33, 939 + 16.5);
        path.lineTo(1840 + 33 - 15, 939 + 16.5);
        path.lineTo(1840 + 33 - 15, 939 - 9 + 16.5);
        path.lineTo(1840 + 33, 939 - 9 + 16.5);
        path.closePath();
        this.plusSymbolPath = path;

    }

    /**
     * draws a new column for the class to add lessons
     * @param graphics
     */
    public void drawClasses(FXGraphics2D graphics) {
        try {
            java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 30);
            FileReader fileReader = new FileReader();
            File classfile = fileReader.readTextFile("src/TextFile/ClassnamePathNames.txt");
            Scanner scanner = new Scanner(classfile);
            classesList = new ArrayList<>();
            while(scanner.hasNextLine()){
                ClassName className = (ClassName) fileReader.readObject(scanner.nextLine());
                classesList.add(className.getName());
            }
            Collections.sort(classesList);
            int amountOfClasses = classesList.size();
            drawGrid(graphics, amountOfClasses);
            drawTimeGrid(graphics);
            for (int i = 0; i < classesList.size(); i++) {
                Shape shape = font.createGlyphVector(graphics.getFontRenderContext(), classesList.get(i)).getOutline();
                graphics.fill(AffineTransform.getTranslateInstance((getClickableRectangleList().get(i).getWidth()) / 2 + getClickableRectangleList().get(i).getX() - 30, 50).createTransformedShape(shape));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * drawTimeGrid() draws horizontal lines with time in the left column
     *
     * @param graphics
     */
    public void drawTimeGrid(FXGraphics2D graphics) {
        int distanceBetweenLines = (2 * (int) screenHeight / 12) - (int) screenHeight / 12;
        int fontScale = distanceBetweenLines / 2;
        java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, fontScale);
        for (int i = 1; i < 11; i++) {
            graphics.drawLine(0, (i * (int) screenHeight / 12), (int) screenWidth, (i * (int) screenHeight / 12));
            Shape shape = font.createGlyphVector(graphics.getFontRenderContext(), (i + 7) + ":00").getOutline();
            graphics.fill(AffineTransform.getTranslateInstance(10, distanceBetweenLines / 1.5 + (i * screenHeight / 12)).createTransformedShape(shape));
        }
        graphics.drawLine(0, (11 * (int) screenHeight / 12), (int) screenWidth, (11 * (int) screenHeight / 12));
    }

    /**
     * drawGrid() draws main grid with rectangles which are saved in attribute "clickableRectangleList"
     *
     * @param graphics
     * @param amountOfClasses the amount of className corresponds to the amount of columns
     */
    public void drawGrid(FXGraphics2D graphics, int amountOfClasses) {
        clickableRectangeList = new ArrayList<>();
        int currentX;
        int currentY;
        for (int j = 1; j < 11; j++) {
            currentY = (int) (j * (screenHeight / 12));
            for (int i = 1; i < amountOfClasses + 1; i++) {
                currentX = (int) (i * (screenWidth / (amountOfClasses + 1)));
                graphics.setColor(Color.black);
                graphics.drawLine(currentX, 0, currentX, currentY);
                Rectangle2D.Double rectangle = new Rectangle2D.Double(currentX, currentY, screenWidth / (amountOfClasses + 1), screenHeight / 12);
                graphics.draw(rectangle);
                clickableRectangeList.add(rectangle);
            }
        }

    }

    public static ArrayList<Rectangle2D.Double> getClickableRectangleList() {
        return clickableRectangeList;
    }

    public static void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public static ArrayList<Lesson> getLessons() {
        return lessons;
    }

    //event handlers

    public void onMouseMoved(MouseEvent e) {
        if (!removeState) {
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        }
    }

    /**
     * Mouse event handler for deleting lessons
     * @param e
     */
    public void onMousePressed(MouseEvent e) {
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        RectangleLogics.rectangleClicked(e);
        Rectangle bounds = new Rectangle(14, 912, (int) (image.getWidth() * 0.16), (int) (image.getHeight() * 0.16));
        if (bounds.contains(new Point2D.Double(e.getX(), e.getY()))) {
            removeState = true;
            java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 30);
            Shape shape = font.createGlyphVector(graphics.getFontRenderContext(), "Click on a Lesson to remove").getOutline();
            graphics.fill(AffineTransform.getTranslateInstance(830, 960).createTransformedShape(shape));
        }
        if(removeState){
            Point2D.Double mouseLocation = new Point2D.Double(e.getX(),e.getY());
            for (LessonRectangle lessonRectangle:lessonRectangles) {
                if(lessonRectangle.getShape().contains(mouseLocation)){
                    Rectangle2D rect = lessonRectangle.getShape().getBounds2D();
                    if(removeState) {
                        Lesson selectedLesson = lessonRectangle.getLesson();
                        if (selectedLesson != null) {
                            try {
                                fileReader.removeFromFile("src/TextFile/LessonPathNames.txt", "src/objectFile/lesson/" + selectedLesson.getaClass().getName() + selectedLesson.getTeacher().getName() + selectedLesson.getSubject().getName() + selectedLesson.getClassroom().getClassID() + ".dat");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            File deleteFile = new File("src/objectFile/lesson/" + selectedLesson.getaClass().getName() + selectedLesson.getTeacher().getName() + selectedLesson.getSubject().getName() + selectedLesson.getClassroom().getClassID() + ".dat");
                            deleteFile.delete();

                            lessons.remove(lessonRectangle.getLesson());
                        }
                    }
                    drawClasses(graphics);
                    graphics.clearRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
                    removeState = false;
                }
            }
        }


        for (DrawableShape shape : drawableShapes) {
            if (shape.isClicked()) {
                ds = shape;
            }
            shape.update(e.getX(), e.getY());

        }

    }

    public void onMouseReleased(MouseEvent e) {
        for (DrawableShape shape : drawableShapes) {
            shape.setClicked(false);
        }
    }

    public void onMouseDragged(MouseEvent e) {

    }
}