package Business.Profiles;

import java.util.ArrayList;
import Business.Person.Person;

/**
 * Directory for Admin/Employee profiles.
 * @author Jaya
 */
public class EmployeeDirectory {

    private ArrayList<EmployeeProfile> employeeList;

    public EmployeeDirectory() {
        employeeList = new ArrayList<>();
    }

    public EmployeeProfile newEmployeeProfile(Person p) {
        EmployeeProfile ep = new EmployeeProfile(p);
        employeeList.add(ep);
        return ep;
    }

    public ArrayList<EmployeeProfile> getEmployeeList() {
        return employeeList;
    }
}
