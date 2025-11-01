package Business.Person;

/**
 * Represents a Person in the system — base for Students, Faculty, etc.
 * Includes proper getters and setters for profile editing and unique ID assignment.
 * @author Manav
 */
public class Person {
    private static int counter = 1;
    private String personId;
    private String name;
    private String email;
    private String phone;
    private String department;


    public Person(String name) {
        this.personId = "P-" + counter++;
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Person.counter = counter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
