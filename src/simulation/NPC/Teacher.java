package simulation.NPC;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;

public class Teacher extends Character {



    public Teacher (Point2D pos, String name) {
        super(pos);
        super.setName(name);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#5c42f4"));
        g.fill(getTransformedShape());
    }


}
