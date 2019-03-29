package simulation.NPC;

import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public  class Character {

    private String name;
    private Point2D pos;
    private double angle;
    private double speed = 2;
    private Point2D dirVector;
    private Shape shape;
    private Point2D target;
    private int spriteImage;
    private int counter;

    public Character (Point2D pos) {
        this.pos = pos;
        this.angle = 0;
        shape = new Ellipse2D.Double(-8, -8, 16, 16);
        this.target = new Point2D.Double(1472,2752);
        dirVector = new Point2D.Double(0, 0);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.decode("#ffff00"));
        //g.fill(getTransformedShape());
    }

    Shape getTransformedShape(){
        AffineTransform tx = new AffineTransform();
        tx.translate(pos.getX(), pos.getY());
        //tx.rotate(angle, 50, 50);

        return tx.createTransformedShape(shape);
    }

    public void update(ArrayList<Character> characters){

        Point2D.Double newPosition = new Point2D.Double(0,0);
        if(pos.getX() < target.getX()){
            dirVector.setLocation(speed , 0);
        }
        if(pos.getX() > target.getX()){
            dirVector.setLocation(-speed , 0);
        }
        if(pos.getY() < target.getY()){
            dirVector.setLocation(0 , speed);
        }
        if(pos.getY() > target.getY()){
            dirVector.setLocation(0 , -speed);
        }

        if ( pos.distance(target) < 5)
            dirVector.setLocation(0, 0);

        newPosition.setLocation(pos.getX() + dirVector.getX(), pos.getY() + dirVector.getY());


        boolean hasCollision = false;
        for(Character character : characters)
        {
            if(character != this && this.hasCollisionCharacter(character))
            {
                hasCollision = true;

                if ( Math.random() > 0.50 ){
                    dirVector.setLocation(0 , -speed);
                }

                break;
            }
        }

        if(true) {
            setPos(newPosition);
        }

        if ( counter % 12 == 0 ){
            spriteImage++;
        }
        counter ++;
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

    public int getSpriteImage () {
        return spriteImage;
    }


    public void drawImage(Graphics2D g, BufferedImage[] tiles){
        AffineTransform tx = new AffineTransform();
        tx.translate(this.getPos().getX(), this.getPos().getY());
        //tx.scale(0.5 , 0.5);

        if ( getDirVector().getY() == 2 ){//0, 1, 2
            g.drawImage(tiles[(this.getSpriteImage() % 3)], tx, null);
        }else if (getDirVector().getX() == -2){//3, 4, 5
            g.drawImage(tiles[(this.getSpriteImage() % 3) + 3], tx, null);
        }else if ( getDirVector().getX() == 2 ){ // 6, 7, 8
            g.drawImage(tiles[(this.getSpriteImage() % 3) + 6], tx, null);
        }else if ( getDirVector().getY() == -2 ){//9, 10, 11
            g.drawImage(tiles[(this.getSpriteImage() % 3) + 9], tx, null);
        }else
            g.drawImage(tiles[1], tx, null);
    }

}
