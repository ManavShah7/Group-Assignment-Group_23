package Business.Profiles;

import java.util.ArrayList;
import Business.Person.Person;

/**
 * Directory for Student profiles.
 * @author Jaya
 */
public class StudentDirectory {

    private ArrayList<StudentProfile> studentList;

    public StudentDirectory() {
        studentList = new ArrayList<>();
    }

    public StudentProfile newStudentProfile(Person p) {
        StudentProfile sp = new StudentProfile(p);
        studentList.add(sp);
        return sp;
    }

    public ArrayList<StudentProfile> getStudentList() {
        return studentList;
    }
}
