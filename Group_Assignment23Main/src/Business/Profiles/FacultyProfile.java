package Business.Profiles;

import Business.Person.Person;

/**
 * Profile for Faculty users.
 * @author Manav
 */
public class FacultyProfile extends Profile {

    public FacultyProfile(Person p) {
        super(p, "FACULTY");
    }
}
