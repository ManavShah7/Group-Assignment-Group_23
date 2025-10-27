package Business.Profiles;

import Business.Person.Person;

/**
 * Profile for system admin or staff users.
 * @author Manav
 */
public class EmployeeProfile extends Profile {

    public EmployeeProfile(Person p) {
        super(p, "ADMIN");
    }
}
