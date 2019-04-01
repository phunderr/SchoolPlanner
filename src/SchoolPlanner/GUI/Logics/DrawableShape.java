package SchoolPlanner.GUI.Logics;

import java.awt.*;

/**
 * interface for drawable shapes to handle mouse events
 * @Autor Pascal Holthuijsen
 */
public interface DrawableShape {
    /**
     * Draws the shape
     * @param g
     */
    void draw(Graphics2D g);

    /**
     *
     * @param x mouse x coordinate to check if the shape is clicked on
     * @param y mouse y coordinate to check if the shape is clicked on
     */
    void update(double x, double y);

    /**
     *
     * @return a boolean value that determent if the shape has been clicked
     */
    boolean isClicked();

    void setClicked(boolean isClicked);
}
