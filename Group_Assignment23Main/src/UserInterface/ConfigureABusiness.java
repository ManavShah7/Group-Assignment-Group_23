package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.*;
import Business.UserAccounts.*;

/**
 * Seeds initial data for login testing and meets Part 1 requirements.
 * @author jaya
 */
public class ConfigureABusiness {

    public static Business initialize() {
        Business business = new Business("Digital University");

        PersonDirectory persons = business.getPersonDirectory();
        EmployeeDirectory employees = business.getEmployeeDirectory();
        FacultyDirectory faculties = business.getFacultyDirectory();
        StudentDirectory students = business.getStudentDirectory();
        RegistrarDirectory registrars = business.getRegistrarDirectory();
        UserAccountDirectory uad = business.getUserAccountDirectory();

        // --- Admin ---
        Person adminP = persons.newPerson("System Admin");
        EmployeeProfile adminProfile = employees.newEmployeeProfile(adminP);
        uad.newUserAccount(adminProfile, "admin", "admin123", Role.ADMIN);

        // --- Faculty ---
        for (int i = 1; i <= 10; i++) {
            Person fp = persons.newPerson("Faculty" + i);
            FacultyProfile fprof = faculties.newFacultyProfile(fp);
            uad.newUserAccount(fprof, "faculty" + i, "fac" + i, Role.FACULTY);
        }

        // --- Students ---
        for (int i = 1; i <= 10; i++) {
            Person sp = persons.newPerson("Student" + i);
            StudentProfile sprof = students.newStudentProfile(sp);
            uad.newUserAccount(sprof, "student" + i, "stud" + i, Role.STUDENT);
        }

        // --- Registrar ---
        Person regP = persons.newPerson("Registrar One");
        RegistrarProfile regProf = registrars.newRegistrarProfile(regP);
        uad.newUserAccount(regProf, "registrar", "reg123", Role.REGISTRAR);

        return business;
    }
}
