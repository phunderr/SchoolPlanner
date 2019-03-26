package simulation.tiled;

import javafx.geometry.Point2D;

import javax.json.JsonObject;

public class Location {

    private Point2D location;
    private String name;

    public Location(int x, int y,String name) {
        this.location = new Point2D(x,y);
        this.name = name;
    }


    @Override
    public String toString() {
        return "(x: " + this.location.getX() + ") (y: " + this.location.getY() + ") (naam: " + this.name + ")";
    }
}
