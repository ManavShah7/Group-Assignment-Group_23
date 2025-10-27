package Business.Profiles;

import Business.Person.Person;

/**
 * Base class for all role profiles (Admin, Faculty, Student, Registrar).
 * Connects a Person with their role in the system.
 * @author Manav
 */
public abstract class Profile {

    protected Person person;
    protected String role;

    public Profile(Person person, String role) {
        this.person = person;
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role + ": " + person.getPersonName();
    }
}
