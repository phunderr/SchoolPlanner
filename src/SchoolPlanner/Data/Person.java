package SchoolPlanner.Data;

public abstract class Person {

    private String name;


    Person (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

}
