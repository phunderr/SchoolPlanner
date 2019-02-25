package SchoolPlanner.GUI.Logics;


import SchoolPlanner.GUI.Scenes.MainScene;

import javafx.scene.input.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleLogics {

    /**
     *
     * rectangleClicked returns a rectangle that has been clicked
     * @param event
     * @return
     */
    public static Rectangle2D.Double rectangleClicked(MouseEvent event){
        Point2D clickedLocation = new Point2D.Double(event.getX(),event.getY());
        for (Rectangle2D.Double rectangle: MainScene.getClickableRectangleList()) {
            if(rectangle.contains(clickedLocation)){
                return rectangle;
            }
        }
        return new Rectangle2D.Double(0,0,0,0);
    }

    public static Rectangle2D.Double getRectangleIndex(int index){
        return MainScene.getClickableRectangleList().get(index);
    }
}