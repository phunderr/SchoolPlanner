package simulation.NPC;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class Teacher extends Character {

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#5c42f4"));
        g.fill(getTransformedShape());
    }
}
