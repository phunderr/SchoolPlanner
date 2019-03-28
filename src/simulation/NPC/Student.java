package simulation.NPC;

import SchoolPlanner.Data.ClassName;

import java.awt.geom.Point2D;

public class Student extends Character{

    ClassName className;

    public Student(Point2D pos, ClassName className) {
        super(pos);
        this.className = className;
    }

    public ClassName getClassName() {
        return className;
    }

    public void setClassName(ClassName className) {
        this.className = className;
    }


}
