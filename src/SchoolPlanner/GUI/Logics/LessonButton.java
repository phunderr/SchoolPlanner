package SchoolPlanner.GUI.Logics;

import SchoolPlanner.GUI.Scenes.PopUpScene;
import javafx.stage.Stage;
import java.awt.*;


public class LessonButton implements DrawableShape {

    private Shape shape;
    private PopUpScene popUp;
    private Color color;
    private Stage stage;
    private double height, width;

    public LessonButton (Shape shape, PopUpScene popUp, Color color, Stage stage, double screenWidth, double screenHeight) {
        this.shape = shape;
        this.popUp = popUp;
        this.color = color;
        this.stage = stage;
        this.width = screenWidth;
        this.height = screenHeight;
    }

    @Override
    public Shape getShape () {
        return shape;
    }

    @Override
    public void draw (Graphics2D g) {
        g.setColor(color);
        g.fill(shape);

    }

    @Override
    public void update () {
        popUp.generatePopUp(stage);
    }
}
