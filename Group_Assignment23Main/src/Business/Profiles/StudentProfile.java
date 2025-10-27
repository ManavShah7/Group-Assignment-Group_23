package Business.Profiles;

import Business.Person.Person;

/**
 * Represents a student profile linked to a Person.
 * Includes department and academic status fields.
 * @author Manav
 */
public class StudentProfile extends Profile {

    private String department;
    private String academicStatus;

    public StudentProfile(Person p) {
        super(p, "Student");
        this.department = "Undeclared";
        this.academicStatus = "Active";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }
}
