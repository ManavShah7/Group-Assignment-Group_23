package Business.Person;

/**
 * Represents a Person in the system — base for Students, Faculty, etc.
 * Includes proper getters and setters for profile editing and unique ID assignment.
 * @author Manav
 */
public class Person {
    private String personName;
    private String first;
    private String last;
    private String phone;
    private String email;
    private String address;
    private String personId;

    private static int idCounter = 1000;

    public Person(String first, String last, String phone, String email, String address) {
        this.first = first;
        this.last = last;
        this.personName = first + " " + last;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.personId = generateId();
    }

    private String generateId() {
        return "U" + (idCounter++);
    }

    // --- Getters ---
    public String getPersonName() { return personName; }
    public String getFirst() { return first; }
    public String getLast() { return last; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPersonId() { return personId; }

    // --- Setters ---
    public void setPersonName(String name) { this.personName = name; }
    public void setFirst(String first) { this.first = first; }
    public void setLast(String last) { this.last = last; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

    /** ✅ Added missing setter — fixes the red error in registration panel */
    public void setPersonId(String personId) { 
        this.personId = personId; 
    }

    @Override
    public String toString() {
        return personName + " (" + personId + ")";
    }
}
