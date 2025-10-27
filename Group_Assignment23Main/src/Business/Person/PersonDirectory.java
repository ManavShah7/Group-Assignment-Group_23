package Business.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Directory for storing and creating Person objects.
 * Prevents duplicates and auto-generates unique persons.
 * @author Manav
 */
public class PersonDirectory {

    private List<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    /** ✅ Creates and adds a new person with basic details */
    public Person newPerson(String first, String last, String phone, String email, String address) {
        Person p = new Person(first, last, phone, email, address);
        personList.add(p);
        return p;
    }

    /** ✅ Simpler overloaded version for quick creation (used by ConfigureABusiness etc.) */
    public Person newPerson(String fullName) {
        String[] parts = fullName.split(" ");
        String first = parts.length > 0 ? parts[0] : "";
        String last = parts.length > 1 ? parts[1] : "";
        Person p = new Person(first, last, "", "", "");
        personList.add(p);
        return p;
    }

    /** Finds a person by email or ID */
    public Person findByEmailOrId(String query) {
        for (Person p : personList) {
            if (p.getEmail().equalsIgnoreCase(query) || p.getPersonId().equalsIgnoreCase(query)) {
                return p;
            }
        }
        return null;
    }
}
