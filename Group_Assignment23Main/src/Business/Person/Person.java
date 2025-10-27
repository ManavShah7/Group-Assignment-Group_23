package Business.Person;

/**
 * Represents a single person in the Digital University.
 * Each person can be linked to one or more profiles.
 * @author Manav
 */
public class Person {

    private static int counter = 1;
    private String personId;
    private String name;

    public Person(String name) {
        this.personId = "P-" + counter++;
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + personId + ")";
    }
}
