package simulation.tiled;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class Camera {

    public Point2D position = new Point2D.Double(100, 100);
    private Point2D cameraposition = new Point2D.Double(0, 0);
    private double camerazoom = 1;

    /**
     * moves the screen by clicking on a mouse
     * @param e
     * @throws NoninvertibleTransformException
     */
    public void mouseDragged(MouseEvent e) throws NoninvertibleTransformException {
        Point2D position2 = new Point2D.Double(e.getX(), e.getY());
        position2 = getCameraTransform().inverseTransform(position2, null);

        Point2D difference = new Point2D.Double(position.getX() - position2.getX(), position.getY() - position2.getY());
        cameraposition = new Point2D.Double(cameraposition.getX() - difference.getX(), cameraposition.getY() - difference.getY());
    }

    /**
     * gets the transformateded camera position
     * @return
     */
    public AffineTransform getCameraTransform(){
        AffineTransform tx = new AffineTransform();
        tx.translate(cameraposition.getX(), cameraposition.getY());
        tx.scale(camerazoom, camerazoom);
        return tx;
    }

    /**
     * zooms in and out
     * @param e
     */
    public void mouseScroll(ScrollEvent e){
        if(camerazoom < 0.6){
            camerazoom = 0.6;
        }
        camerazoom += e.getDeltaY()/1000;
    }

    public void mousePressed(MouseEvent e) throws NoninvertibleTransformException {
        position = new Point2D.Double(e.getX(), e.getY());
        position = getCameraTransform().inverseTransform(position, null);
    }
}
