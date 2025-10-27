package Business.Profiles;

import Business.Person.Person;

/**
 * Abstract class for all profiles (Admin, Faculty, Student, Registrar).
 * Links to a Person and defines a role type.
 * @author Jaya
 */
public abstract class Profile {

    protected Person person;
    protected String role;

    public Profile(Person p, String role) {
        this.person = p;
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
        return role + ": " + person.getName();
    }
}
