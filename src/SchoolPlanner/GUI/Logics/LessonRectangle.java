package SchoolPlanner.GUI.Logics;

import SchoolPlanner.Data.Lesson;
import SchoolPlanner.GUI.Scenes.MainScene;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Makes a rectangle that contains lesson data
 *
 * @Autor Arno Nagtzaam & Pascal Holthuijsen
 */
public class LessonRectangle implements DrawableShape {

    private Shape shape;
    private Color color;
    private Point2D previousPoint;
    private boolean isClicked;
    private boolean isReleased;
    private AffineTransform af;
    private Rectangle2D.Double rectangle;
    LinkedHashMap<String, Integer> timeValues;

    //Lesson info
    private Lesson lesson;

    //Horizontal
    private Set<Double> XCoordinates;

    public LessonRectangle(Lesson lesson, Rectangle2D.Double rectangle) {
        timeValues = new LinkedHashMap<>();
        XCoordinates = new HashSet<>();
        this.lesson = lesson;
        color = Color.cyan;
        this.rectangle = rectangle;
        for (int i = 1; i < 11; i++) {
            String time = "";
            if (i + 7 < 10) {
                time = "0" + (i + 7) + ":00";
            } else {
                time = (i + 7) + ":00";
            }
            timeValues.put(time, 83 * i);
        }
    }

    public void addXCoordinates(Double x) {
        XCoordinates.add(x);
    }

    @Override
    public void draw(Graphics2D g) {
        int Y = 0;
        int scale = 26;
        String firstTime = "";
        String endTime = "";
        for (String time : timeValues.keySet()) {
            if (time.equals(lesson.getLessonPeriod().getLessonStartTime())) {
                Y = timeValues.get(time);
                firstTime = time;
            }
            if (time.equals(lesson.getLessonPeriod().getLessonEndTime())) {
                endTime = time;
            }
        }
        int firstNum = Integer.parseInt(firstTime.substring(0,firstTime.length()-3));
        int lastNum = Integer.parseInt(endTime.substring(0,endTime.length()-3));
        int difference = lastNum-firstNum;
        int height = difference * 83;
        int yTeacher = Y + 30;
        int ySubject = Y + 80;
        int yClassroom = Y + 130;
        if(height-Y <= 83){
            yTeacher -= 4;
            ySubject -= 29;
            yClassroom -= 55;
            scale = 25;
        }
        g.setColor(color);
        shape = new Rectangle2D.Double(rectangle.getX(), Y, rectangle.getWidth(), height);
        g.fill(shape);
        java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, scale);
        Shape shapeTeacher = font.createGlyphVector(g.getFontRenderContext(), "Teacher: " + lesson.getTeacher()).getOutline();
        Shape shapeSubject = font.createGlyphVector(g.getFontRenderContext(), "Subject: " + lesson.getSubject()).getOutline();
        Shape shapeClassRoom = font.createGlyphVector(g.getFontRenderContext(), "Classroom: " + lesson.getClassroom().getClassID()).getOutline();
        g.setColor(Color.BLACK);
        g.fill(AffineTransform.getTranslateInstance(rectangle.getX() + 10, yTeacher).createTransformedShape(shapeTeacher));
        g.fill(AffineTransform.getTranslateInstance(rectangle.getX() + 10, ySubject).createTransformedShape(shapeSubject));
        g.fill(AffineTransform.getTranslateInstance(rectangle.getX() + 10, yClassroom).createTransformedShape(shapeClassRoom));
    }


    public Shape getShape(){
        return shape;
    }

    @Override
    public void update(double x, double y) {    }

    public Lesson getLesson(){
        return lesson;
    }

    @Override
    public boolean isClicked() {
        return isClicked;
    }

    @Override
    public void setClicked(boolean isClicked) {
        this.isClicked = true;
    }


}
