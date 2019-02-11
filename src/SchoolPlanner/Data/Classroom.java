package SchoolPlanner.Data;

import java.io.Serializable;

public class Classroom implements Serializable {
    private String classID;

    public Classroom(String classID) {
        this.classID = classID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
