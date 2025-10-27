package Business.Profiles;

import Business.Person.Person;

/**
 * Profile for Student users.
 * @author Jaya
 */
public class StudentProfile extends Profile {

    public StudentProfile(Person p) {
        super(p, "STUDENT");
    }
}
