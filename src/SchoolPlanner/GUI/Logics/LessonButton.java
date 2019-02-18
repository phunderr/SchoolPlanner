package SchoolPlanner.GUI.Logics;

import SchoolPlanner.GUI.Scenes.AddLessonPopUpScene;
import javafx.stage.Stage;
import java.awt.*;

/**
 * Makes a small button that gives a pop up when clicked on
 * @Autor Pascal Holthuijsen
 */
public class LessonButton implements DrawableShape {

    private Shape shape;
    private AddLessonPopUpScene popUp;
    private Color color;
    private Stage stage;
    private double height, width;
    private boolean isCLicked;

    public LessonButton (Shape shape, AddLessonPopUpScene popUp, Color color, Stage stage, double screenWidth, double screenHeight) {
        this.shape = shape;
        this.popUp = popUp;
        this.color = color;
        this.stage = stage;
        this.width = screenWidth;
        this.height = screenHeight;
    }

    @Override
    public void setClicked(boolean isCLicked){
        this.isCLicked = isCLicked;
    }

    @Override
    public boolean isClicked(){
        return isCLicked;
    }
    public Shape getShape () {
        return shape;
    }

    @Override
    public void draw (Graphics2D g) {
        g.setColor(color);
        g.fill(shape);

    }

    @Override
    public void update (double x, double y) {
        if ( shape.contains(x, y) ) {
            popUp.generatePopUp(stage);
            this.isCLicked = true;
        }
    }
}
