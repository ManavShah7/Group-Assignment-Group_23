package Business.Profiles;

import Business.Person.Person;

/**
 * Represents a faculty member's profile.
 * Includes department and title fields.
 * @author Manav
 */
public class FacultyProfile extends Profile {

    private String department;
    private String title;

    public FacultyProfile(Person p) {
        super(p, "Faculty");
        this.department = "General";
        this.title = "Professor";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
