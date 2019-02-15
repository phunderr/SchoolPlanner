package SchoolPlanner.GUI.Logics;

import java.awt.*;

public interface DrawableShape {
    void draw(Graphics2D g);
    void update();
    Shape getShape();
}
