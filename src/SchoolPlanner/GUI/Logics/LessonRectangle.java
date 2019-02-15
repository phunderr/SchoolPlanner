package SchoolPlanner.GUI.Logics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Makes a rectangle that contains lesson data
 * @Autor Pascal Holthuijsen
 */
public class LessonRectangle implements DrawableShape {

    private Shape shape;
    private Color color;
    private Point2D previousPoint;
    private boolean isClicked;
    private AffineTransform af;


    @Override
    public void draw (Graphics2D g) {

    }

    @Override
    public void update (double x, double y) {
        if ( isClicked ){
            double dx = previousPoint.getX();
            double dy;
        }
    }

    @Override
    public boolean isClicked () {
        return isClicked;
    }

    @Override
    public void setClicked (boolean isClicked) {
        this.isClicked = true;
    }
}
