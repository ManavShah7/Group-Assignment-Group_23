/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import Business.Person.Person;
/**
 *
 * @author prekshapraveen
 */
public class FacultyProfile extends Profile{
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
