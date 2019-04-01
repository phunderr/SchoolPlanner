package simulation.NPC;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Teacher extends Character {

    BufferedImage[] tiles;
    HashMap<String, Point2D.Double> locationInClassRoom;

    public Teacher (Point2D pos, String name, HashMap<String, Point2D.Double> locationInClassRoom) {
        super(pos);
        super.setName(name);
        init();
        this.locationInClassRoom = locationInClassRoom;
    }

    @Override
    public void draw(Graphics2D g) {

        this.drawImage(g, tiles);

    }

    public HashMap<String, Point2D.Double> getLocationInClassRoom() {
        return locationInClassRoom;
    }

    public void init(){
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/sprites/Hetalia_Sprites___Spain_01_by_CarmenMCS.png"));
            tiles = new BufferedImage[12];

            for ( int i = 0; i < tiles.length; i++ )
                tiles[i] = image.getSubimage(32 * ((i % 3) + 9), 32 * (i /3), 32, 32);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getName(){
        return super.getName();
    }


}
