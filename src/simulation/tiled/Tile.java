package simulation.tiled;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Tile {
    private Point2D location;
    private BufferedImage bf;
    private Rectangle2D rect;

    public Tile (Point2D location, BufferedImage bf) {
        this.location = location;
        this.bf = bf;
        this.rect = new Rectangle2D.Double(location.getX(),location.getY(),32,32);
    }

    public Point2D getLocation() {
        return location;
    }

    public BufferedImage getBf() {
        return bf;
    }

    public Rectangle2D getRect(){
        return this.rect;
    }
}
