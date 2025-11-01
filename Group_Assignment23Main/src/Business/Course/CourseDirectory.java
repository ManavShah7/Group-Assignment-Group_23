/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;


import Business.Profiles.FacultyProfile;
import java.util.ArrayList;
/**
 *
 * @author prekshapraveen
 */
public class CourseDirectory {
    private ArrayList<Course> courseList;

    public CourseDirectory() {
        this.courseList = new ArrayList<>();
    }

    public Course newCourse(String id, String title, String desc, String schedule,
                            int capacity, FacultyProfile faculty,
                            double tuition, String semester) {
        Course c = new Course(id, title, desc, schedule, capacity, faculty);
        c.setTuitionFee(tuition);
        c.setEnrollmentOpen(true);
        c.setSyllabusPath(null);
        // you already have semester field — let’s set it here
        try {
            java.lang.reflect.Field semField = c.getClass().getDeclaredField("semester");
            semField.setAccessible(true);
            semField.set(c, semester);
        } catch (Exception e) {
            e.printStackTrace();
        }
        courseList.add(c);
        return c;
    }
    
    public ArrayList<Course> getCoursesByFaculty(FacultyProfile faculty) {
        ArrayList<Course> result = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getFaculty().equals(faculty)) result.add(c);
        }
        return result;
    }

    public ArrayList<Course> getCourseList() { return courseList; }

    public Course findCourseById(String id) {
        for (Course c : courseList) {
            if (c.getCourseId().equals(id)) return c;
        }
        return null;
    }

}
