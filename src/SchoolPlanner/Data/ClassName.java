package SchoolPlanner.Data;

import java.io.Serializable;

/**
 * @Author Jelmer Surewaard
 * an object that contains a name and a size
 */

public class ClassName implements Serializable {

    private String name;
    private int numberOfStudents;

    public ClassName(String name, int numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String toString() {
        return name;
    }
}
