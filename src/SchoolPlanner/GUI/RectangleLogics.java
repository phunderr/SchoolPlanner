package SchoolPlanner.GUI;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleLogics {

    public static void rectangleClicked(MouseEvent event){
        Point2D clickedLocation = new Point2D.Double(event.getX(),event.getY());
        for (Rectangle2D.Double rectangle:GUI.getClickableRectangleList()) {
            if(rectangle.contains(clickedLocation)){
                System.out.println(GUI.getClickableRectangleList().indexOf(rectangle));
            }
        }
    }
}