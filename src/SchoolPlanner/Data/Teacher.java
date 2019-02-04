package SchoolPlanner.Data;

public class Teacher extends Person{

    private String subject;
    private int popularity;


    public Teacher (String name, Gender gender, String subject, int popularity) {
        super(name, gender);
        this.subject = subject;
        this.popularity = popularity;
    }

    public String getSubject () {
        return subject;
    }

    public int getPopularity () {
        return popularity;
    }
}
