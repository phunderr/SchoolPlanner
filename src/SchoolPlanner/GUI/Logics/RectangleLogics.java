package SchoolPlanner.GUI.Logics;


import SchoolPlanner.GUI.Scenes.MainScene;
import SchoolPlanner.GUI.Scenes.PopUpScene;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static javafx.application.Application.launch;

public class RectangleLogics {

    public static void rectangleClicked(MouseEvent event){
        Point2D clickedLocation = new Point2D.Double(event.getX(),event.getY());
        for (Rectangle2D.Double rectangle: MainScene.getClickableRectangleList()) {
            if(rectangle.contains(clickedLocation)){
                System.out.println(MainScene.getClickableRectangleList().indexOf(rectangle));
                
            }
        }

    }
}