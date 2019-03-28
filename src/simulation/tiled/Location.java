package simulation.tiled;


import javax.json.JsonObject;
import java.awt.geom.Point2D;

public class Location {

    private Point2D location;
    private String name;

    public Location(int x, int y,String name) {
        this.location = new Point2D.Double(x,y);
        this.name = name;
    }

    public Point2D getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(x: " + this.location.getX() + ") (y: " + this.location.getY() + ") (naam: " + this.name + ")";
    }
}
