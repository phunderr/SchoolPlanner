package simulation.NPC;

import javafx.scene.input.MouseEvent;

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
    private Shape shape;
    private Point2D target;

    public Character () {
        this.pos = new Point2D.Double(960,1000);
        this.angle = 0;
        shape = new Ellipse2D.Double(-50, -50, 16, 16);
        this.target = new Point2D.Double(100,100);
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

        Point2D.Double newPosition = new Point2D.Double(0,0);
        if(pos.getX() < target.getX()){
            newPosition = new Point2D.Double(pos.getX() + speed, pos.getY());
        }
        if(pos.getX() > target.getX()){
            newPosition = new Point2D.Double(pos.getX() - speed, pos.getY());
        }
        if(pos.getY() < target.getY()){
            newPosition = new Point2D.Double(pos.getX(), pos.getY() + speed);
        }
        if(pos.getY() > target.getY()){
            newPosition = new Point2D.Double(pos.getX(), pos.getY() - speed);
        }
//        double targetAngle = Math.atan2(diff.getY(), diff.getX());
//
//        double angleDiff = targetAngle - angle;
//        while(angleDiff > Math.PI)
//            angleDiff -= 2 * Math.PI;
//        while(angleDiff < -Math.PI)
//            angleDiff += 2 * Math.PI;
//
//        if(angleDiff < -0.1)
//            angle-=0.1;
//        else if(angleDiff > 0.1)
//            angle+=0.1;
//        else
//            this.angle = targetAngle;

        boolean hasCollision = false;
        for(Character character : characters)
        {
            if(character != this && this.hasCollisionCharacter(character))
            {
                hasCollision = true;
                break;
            }
        }

        if(!hasCollision) {
            setPos(newPosition);
        }
    }

    public void setTarget(Point2D target){
        this.target = target;
    }

    private void setPos(Point2D.Double pos){
        this.pos = pos;
    }

    public Point2D getPos(){
        return this.pos;
    }

    public boolean hasCollisionCharacter(Character otherVisitor) {
        return otherVisitor.pos.distance(pos) < 15;
    }

    public boolean hasCollisionPoint(Point2D otherPosition)
    {
        return otherPosition.distance(pos) < 15;
    }

    public double getDistance(Point2D pointCompare){
        return this.pos.distance(pointCompare);
    }

    public void setName(String name){
        this.name = name;
    }
}
