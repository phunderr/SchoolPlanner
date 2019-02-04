package SchoolPlanner.Data;

public class Student extends Person {

    private int studentNumber;

    public Student (String name, Gender gender, int studentNumber) {
        super(name, gender);
        this.studentNumber = studentNumber;
    }

    public int getStudentNumber () {
        return studentNumber;
    }
}
