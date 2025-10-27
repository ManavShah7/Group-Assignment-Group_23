package Business;

import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.RegistrarDirectory;
import Business.UserAccounts.UserAccountDirectory;

/**
 * Root container for the Digital University business structure.
 * @author Manav
 */
public class Business {

    private String name;

    private PersonDirectory personDirectory;
    private EmployeeDirectory employeeDirectory;
    private FacultyDirectory facultyDirectory;
    private StudentDirectory studentDirectory;
    private RegistrarDirectory registrarDirectory;
    private UserAccountDirectory userAccountDirectory;

    public Business(String name) {
        this.name = name;
        this.personDirectory = new PersonDirectory();
        this.employeeDirectory = new EmployeeDirectory();
        this.facultyDirectory = new FacultyDirectory();
        this.studentDirectory = new StudentDirectory();
        this.registrarDirectory = new RegistrarDirectory();
        this.userAccountDirectory = new UserAccountDirectory();
    }

    public String getName() { return name; }

    public PersonDirectory getPersonDirectory() { return personDirectory; }
    public EmployeeDirectory getEmployeeDirectory() { return employeeDirectory; }
    public FacultyDirectory getFacultyDirectory() { return facultyDirectory; }
    public StudentDirectory getStudentDirectory() { return studentDirectory; }
    public RegistrarDirectory getRegistrarDirectory() { return registrarDirectory; }
    public UserAccountDirectory getUserAccountDirectory() { return userAccountDirectory; }
}
