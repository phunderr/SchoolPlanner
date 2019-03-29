package simulation.NPC;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Teacher extends Character {

    BufferedImage[] tiles;

    public Teacher (Point2D pos, String name) {
        super(pos);
        super.setName(name);
        init();
    }

    @Override
    public void draw(Graphics2D g) {

        this.drawImage(g, tiles);

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


}
