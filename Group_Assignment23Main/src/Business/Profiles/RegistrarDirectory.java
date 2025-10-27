package Business.Profiles;

import java.util.ArrayList;
import Business.Person.Person;

/**
 * Directory for Registrar profiles.
 * @author Jaya
 */
public class RegistrarDirectory {

    private ArrayList<RegistrarProfile> registrarList;

    public RegistrarDirectory() {
        registrarList = new ArrayList<>();
    }

    public RegistrarProfile newRegistrarProfile(Person p) {
        RegistrarProfile rp = new RegistrarProfile(p);
        registrarList.add(rp);
        return rp;
    }

    public ArrayList<RegistrarProfile> getRegistrarList() {
        return registrarList;
    }
}
