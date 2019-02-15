package SchoolPlanner.Data;

import java.io.Serializable;

/**
 * Contains the information of a classroom
 * @Autor Pascal Holthuijsen
 */

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
