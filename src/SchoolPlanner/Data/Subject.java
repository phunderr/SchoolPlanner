package SchoolPlanner.Data;

public class Subject {

    private String name;
    private int popularity;

    public Subject(String name, int popularity) {

        this.name = name;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
