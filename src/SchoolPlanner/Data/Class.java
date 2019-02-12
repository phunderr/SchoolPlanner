package SchoolPlanner.Data;

import java.io.Serializable;

public class Class implements Serializable {

    private String classID;

    public Class (String classID) {
        this.classID = classID;
    }

    public String getClassID () {
        return classID;
    }
}
