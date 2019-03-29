package simulation.tiled;


import javax.json.JsonObject;
import java.awt.geom.Point2D;

public class Location {

    private Point2D location;
    private String name;
    private int width, height;

    public Location(int x, int y,String name, int width, int height) {
        this.location = new Point2D.Double(x,y);
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Point2D getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }

    @Override
    public String toString() {
        return "(x: " + this.location.getX() + ") (y: " + this.location.getY() + ") (naam: " + this.name + ")";
    }
}
