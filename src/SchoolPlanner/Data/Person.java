package SchoolPlanner.Data;

public abstract class Person {

    private String name;
    private Gender gender;

    Person (String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName () {
        return name;
    }

    public Gender getGender () {
        return gender;
    }
}
