package Business.Profiles;

import Business.Person.Person;

/**
 * Profile for Registrar role users.
 * @author Jaya
 */
public class RegistrarProfile extends Profile {

    public RegistrarProfile(Person p) {
        super(p, "REGISTRAR");
    }
}
