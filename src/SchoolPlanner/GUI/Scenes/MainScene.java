package SchoolPlanner.GUI.Scenes;


import SchoolPlanner.GUI.Logics.LessonButton;
import SchoolPlanner.GUI.Logics.DrawableShape;
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
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MainScene extends Application {

    private BorderPane mainPane;
    private ResizableCanvas canvas;
    private double screenHeight;
    private double screenWidth;
    private static ArrayList<Rectangle2D.Double> clickableRectangeList;
    private TabPane mainTabPane;
    private ArrayList<DrawableShape> drawableShapes;


    public void start(Stage primaryStage){
        setup();


        primaryStage.setMaximized(true);
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        //mainPane.setTop(placeClasses(classes));

        //primaryStage.setFullScreen(true);
        canvas.setOnMousePressed(this::onMousePressed);
        primaryStage.setScene(new Scene(mainTabPane));
        primaryStage.setTitle("Rooster Application");
        primaryStage.show();
        primaryStage.setResizable(false);

        //new PopUpScene().generatePopUp(new Stage());
    }

    /**
     * draw draws the GUI components on the canvas
     * @param graphics
     */
    public void draw(FXGraphics2D graphics) {
        this.screenWidth = canvas.getWidth();
        this.screenHeight = canvas.getHeight();
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        //draws the roster
        int amountOfClasses = 2;
        drawGrid(graphics, amountOfClasses);
        drawTimeGrid(graphics);

        for ( DrawableShape drawableShape : drawableShapes ) {
            drawableShape.draw(graphics);
        }
    }

    /**
     * setup() initializes JavaFX components of the GUI
     */
    public void setup() {
        this.drawableShapes = new ArrayList<>();

        this.mainPane = new BorderPane();
        this.mainTabPane = new TabPane();
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab rosterTab = new Tab("Rooster");
        Tab rosterInputTab = new Tab("Rooster Input");
        Tab simulationTab = new Tab("Simulation");
        mainTabPane.getTabs().addAll(rosterTab, rosterInputTab, simulationTab);
        rosterTab.setContent(mainPane);
        rosterInputTab.setContent(new RosterInputScene().rosterInputScene());

        //numbers are hard coded because i couldnt get screenwidth, screenHeigt to work
        this.drawableShapes.add(new LessonButton(new Ellipse2D.Double(1850, 900 , 75, 75 ), new PopUpScene(), Color.CYAN, new Stage(), screenWidth, screenHeight));


    }

    /**
     * drawTimeGrid() draws horizontal lines with time in the left column
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
     * @param graphics
     * @param amountOfClasses the amount of classes corresponds to the amount of columns
     */
    public void drawGrid(FXGraphics2D graphics, int amountOfClasses) {
        clickableRectangeList = new ArrayList<>();
        int currentX;
        int currentY = 0;
        for (int j = 1; j < 11; j++) {
            currentY = (int) (j * (screenHeight / 12));
            for (int i = 1; i < amountOfClasses + 1; i++) {
                currentX = (int) (i * (screenWidth / (amountOfClasses + 1)));
                graphics.setColor(Color.black);
                graphics.drawLine(currentX,0,currentX,currentY);
                Rectangle2D.Double rectangle = new Rectangle2D.Double(currentX, currentY, screenWidth / (amountOfClasses + 1), screenHeight / 12);
                graphics.draw(rectangle);
                clickableRectangeList.add(rectangle);
            }
        }
    }

    public static ArrayList<Rectangle2D.Double> getClickableRectangleList() {
        return clickableRectangeList;
    }


    //event handelers

    public void onMousePressed(MouseEvent e){
        RectangleLogics.rectangleClicked(e);

        for ( DrawableShape shape : drawableShapes ) {
            if ( shape.getShape().contains(e.getX(), e.getY()) ){
                shape.update();
            }
        }


    }



}





