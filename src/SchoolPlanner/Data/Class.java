package SchoolPlanner.Data;

public class Class {

    private String name;
    private int numberOfStudents;

    public Class(String name, int numberOfStudents) {
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
        return "Class{" +
                "name='" + name + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                '}';
    }
}
