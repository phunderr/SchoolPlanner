package simulation.NPC;

import SchoolPlanner.Data.ClassName;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Student extends Character{

    ClassName className;
    BufferedImage[] tiles;
    HashMap<String, Point2D.Double> locationInClassRoom;

    public Student(Point2D pos, ClassName className, HashMap<String, Point2D.Double> locationInClassRoom) {
        super(pos);
        init();
        this.className = className;
        this.locationInClassRoom = locationInClassRoom;
    }


    @Override
    public void draw(Graphics2D g){
        this.drawImage(g, tiles);

    }

    public HashMap<String, Point2D.Double> getLocationInClassRoom() {
        return locationInClassRoom;
    }

    public ClassName getClassName() {
        return className;
    }

    public void setClassName(ClassName className) {
        this.className = className;
    }

    public void init(){
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Hetalia_Sprites___Spain_01_by_CarmenMCS.png"));
            tiles = new BufferedImage[12];

            for ( int i = 0; i < tiles.length; i++ )
                tiles[i] = image.getSubimage(32 * ((i % 3) + 3), 32 * ((i + (3*4))/3), 32, 32);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
