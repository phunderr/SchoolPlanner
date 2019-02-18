package SchoolPlanner.Data;

public class Teacher{

    private String name;
    private String subject;
    private int popularity;


    public Teacher (String name, String subject, int popularity) {
        this.name = name;
        this.subject = subject;
        this.popularity = popularity;
    }

    public String getSubject () {
        return subject;
    }

    public int getPopularity () {
        return popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}