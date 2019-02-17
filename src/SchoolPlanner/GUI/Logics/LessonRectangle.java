package SchoolPlanner.GUI.Logics;

import SchoolPlanner.Data.Lesson;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Makes a rectangle that contains lesson data
 * @Autor Pascal Holthuijsen
 */
public class LessonRectangle implements DrawableShape {

    private Shape shape;
    private Color color;
    private Point2D previousPoint;
    private boolean isClicked;
    private boolean isReleased;
    private AffineTransform af;

    //Lesson info
    private Lesson lesson;

    //Placements
    //Vertical times coordinates
    private final int EIGHT = 40;
    private final int NINE = 80;
    private final int TEN = 120;
    private final int ELEVEN = 160;
    private final int TWELVE = 200;
    private final int ONE = 240;
    private final int TWO = 280;
    private final int THREE = 320;
    private final int FOUR = 360;
    private final int FIVE = 400;

    //Horizontal
    private Set<Double> XCoordinates;

    public LessonRectangle(){
        XCoordinates = new HashSet<>();
        //TODO making constructor
    }


    public void addXCoordinates(Double x){
        XCoordinates.add(x);
    }

    @Override
    public void draw (Graphics2D g) {
        //TODO drawing the shape
    }

    @Override
    public void update (double x, double y) {
        if(shape.contains(x, y)) {
            double dx = previousPoint.getX() - x;
            double dy = previousPoint.getY() - y;

            //TODO dragging transformation

            //vertical snapping
            double tranformedY = 0;
            if ( shape.getBounds2D().getY() < NINE ){
                tranformedY = EIGHT;
            }else if ( shape.getBounds2D().getY() > NINE && shape.getBounds2D().getY() < TEN ){
                tranformedY = NINE;
            }else if ( shape.getBounds2D().getY() > TEN && shape.getBounds2D().getY() < ELEVEN ){
                tranformedY = TEN;
            }else if ( shape.getBounds2D().getY() > ELEVEN && shape.getBounds2D().getY() < TWELVE ){
                tranformedY = ELEVEN;
            }else if ( shape.getBounds2D().getY() > TWELVE && shape.getBounds2D().getY() < ONE ){
                tranformedY = TWELVE;
            }else if ( shape.getBounds2D().getY() > ONE && shape.getBounds2D().getY() < TWO ){
                tranformedY = ONE;
            }else if ( shape.getBounds2D().getY() > TWO && shape.getBounds2D().getY() < THREE ){
                tranformedY = TWO;
            }else if ( shape.getBounds2D().getY() > THREE && shape.getBounds2D().getY() < FOUR ){
                tranformedY = THREE;
            }else if ( shape.getBounds2D().getY() > FOUR && shape.getBounds2D().getY() < FIVE ){
                tranformedY = FOUR;
            }else if ( shape.getBounds2D().getY() > FIVE ){
                tranformedY = FIVE;
            }

            double transformedX = 0;
            //TODO horisontal angle snapping if the center is between to lines make it snap to the middle of those lines

            if(tranformedY != 0 && transformedX != 0 && isReleased){
                af.translate(transformedX, tranformedY);
                isReleased = false;
            }

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
