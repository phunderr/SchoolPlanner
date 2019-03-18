package simulation.NPC;


import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author Stijn van Berkel
 */
public class NPC {
    private String name;
    private int popularity;
    private BufferedImage image;

    public NPC(String name, int popularity, BufferedImage image){
        this.name = name;
        this.popularity = popularity;
        this.image = image;
    }

    public String toString(){
        return name  + " " + popularity;
    }

}
