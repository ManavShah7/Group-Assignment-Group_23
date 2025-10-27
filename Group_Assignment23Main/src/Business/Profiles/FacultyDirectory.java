package Business.Profiles;

import java.util.ArrayList;
import Business.Person.Person;

/**
 * Directory for Faculty profiles.
 * @author Jaya
 */
public class FacultyDirectory {

    private ArrayList<FacultyProfile> facultyList;

    public FacultyDirectory() {
        facultyList = new ArrayList<>();
    }

    public FacultyProfile newFacultyProfile(Person p) {
        FacultyProfile fp = new FacultyProfile(p);
        facultyList.add(fp);
        return fp;
    }

    public ArrayList<FacultyProfile> getFacultyList() {
        return facultyList;
    }
}
