package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.*;
import Business.UserAccounts.*;
import Business.Course.*;

/**
 * Seeds initial data for login testing and meets Part 1 requirements.
 * @author Manav
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
        CourseDirectory courses = business.getCourseDirectory();

        // --- Admin ---
        Person adminP = persons.newPerson("System Admin");
        EmployeeProfile adminProfile = employees.newEmployeeProfile(adminP);
        uad.newUserAccount(adminProfile, "admin", "admin123", Role.ADMIN);

        // --- Faculty ---
        Person fp1 = persons.newPerson("Dr. Alex Carter");
        FacultyProfile faculty1 = faculties.newFacultyProfile(fp1);
        uad.newUserAccount(faculty1, "faculty1", "fac1", Role.FACULTY);

        Person fp2 = persons.newPerson("Prof. Maya Patel");
        FacultyProfile faculty2 = faculties.newFacultyProfile(fp2);
        uad.newUserAccount(faculty2, "faculty2", "fac2", Role.FACULTY);

        // --- Students ---
        Person sp1 = persons.newPerson("John Miller");
        StudentProfile student1 = students.newStudentProfile(sp1);
        uad.newUserAccount(student1, "student1", "stud1", Role.STUDENT);

        Person sp2 = persons.newPerson("Priya Singh");
        StudentProfile student2 = students.newStudentProfile(sp2);
        uad.newUserAccount(student2, "student2", "stud2", Role.STUDENT);

        Person sp3 = persons.newPerson("Liam Chen");
        StudentProfile student3 = students.newStudentProfile(sp3);
        uad.newUserAccount(student3, "student3", "stud3", Role.STUDENT);

        // --- Registrar ---
        Person regP = persons.newPerson("Registrar One");
        RegistrarProfile regProf = registrars.newRegistrarProfile(regP);
        uad.newUserAccount(regProf, "registrar", "reg123", Role.REGISTRAR);
        
        Course c1 = courses.newCourse("CS101", "Intro to Programming", "Basics of Java and OOP",
                "Mon-Wed 10AM", 30, faculty1, 1200.0, "Fall 2025");
        Course c2 = courses.newCourse("CS201", "Data Structures", "Advanced algorithms and DS",
                "Tue-Thu 11AM", 25, faculty1, 1500.0, "Fall 2025");
        Course c3 = courses.newCourse("MATH101", "Calculus I", "Differentiation and Integration",
                "Mon-Wed 9AM", 40, faculty2, 1000.0, "Fall 2025");
        
        // --- Enroll Students ---
        c1.enrollStudent(student1);
        c1.enrollStudent(student2);
        c2.enrollStudent(student1);
        c2.enrollStudent(student3);
        c3.enrollStudent(student2);
        c3.enrollStudent(student3);

        // --- Assign Grades ---
        c1.assignGrade(student1, 92);
        c1.assignGrade(student2, 85);
        c2.assignGrade(student1, 88);
        c2.assignGrade(student3, 79);
        c3.assignGrade(student2, 91);
        c3.assignGrade(student3, 84);

        return business;
    }
}
