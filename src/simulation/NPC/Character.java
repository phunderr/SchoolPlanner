package simulation.NPC;

import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public  class Character {

    private String name;
    private Point2D pos;
    private double angle;
    private double speed = 2;
    private Point2D dirVector;
    private Shape shape;
    private Point2D target;

    public Character (Point2D pos) {
        this.pos = pos;
        this.angle = 0;
        shape = new Ellipse2D.Double(-50, -50, 16, 16);
        this.target = new Point2D.Double(1472,2752);
        dirVector = new Point2D.Double(0, 0);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.decode("#ffff00"));
        g.fill(getTransformedShape());
    }

    Shape getTransformedShape(){
        AffineTransform tx = new AffineTransform();
        tx.translate(pos.getX(), pos.getY());
        tx.rotate(angle, 50, 50);

        return tx.createTransformedShape(shape);
    }

    public void update(ArrayList<Character> characters){

        Point2D newPosition = new Point2D.Double(pos.getX() + speed * Math.cos(angle), pos.getY() + speed* Math.sin(angle));

        //newPosition.setLocation(pos.getX() + dirVector.getX(), pos.getY() + dirVector.getY());


        boolean hasCollision = false;
        for(Character character : characters)
        {
            if(character != this && (this.hasCollisionCharacter(character) ||character.hasCollisionPoint(newPosition)))
            {
                hasCollision = true;
                break;
            }
        }

        if(!hasCollision) {
            setPos(newPosition);
        }else{
            this.angle += 2;
        }
    }

    public void setTarget(Point2D target){
        this.target = target;
    }

    private void setPos(Point2D pos){
        this.pos = pos;
    }

    public Point2D getPos(){
        return this.pos;
    }

    public boolean hasCollisionCharacter(Character otherVisitor) {
        return otherVisitor.pos.distance(pos) < 16;
    }

    public boolean hasCollisionPoint(Point2D otherPosition)
    {
        return otherPosition.distance(pos) < 16;
    }

    public double getDistance(Point2D pointCompare){
        return this.pos.distance(pointCompare);
    }

    public void setName(String name){
        this.name = name;
    }

    public Point2D getDirVector () {
        return dirVector;
    }

    public void setDirVector (Point2D dirVector) {
        this.dirVector = dirVector;
    }

    public double getSpeed () {
        return speed;
    }

    public void setSpeed (double speed) {
        this.speed = speed;
    }
}
