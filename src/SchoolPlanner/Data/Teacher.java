package SchoolPlanner.Data;

import java.io.Serializable;

/**
 * @Author Jelmer Surewaard
 * Object Teacher that contains the Teachers and its Popularity
 */
public class Teacher implements Serializable {

    private String name;
    private int popularity;


    public Teacher (String name, int popularity) {
        this.name = name;
        this.popularity = popularity;
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

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}