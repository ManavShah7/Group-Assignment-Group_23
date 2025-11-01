/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentProfile;
import java.util.ArrayList;
/**
 *
 * @author prekshapraveen
 */
public class Course {
    private String courseId;
    private String title;
    private String description;
    private String schedule;
    private int capacity;
    private boolean enrollmentOpen;
    private String syllabusPath;
    private FacultyProfile faculty;
    private ArrayList<StudentProfile> enrolledStudents;
    private ArrayList<Double> grades; // parallel to enrolledStudents list
    private String semester;

    
    public Course(String courseId, String title, String description, String schedule, int capacity, FacultyProfile faculty) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
        this.capacity = capacity;
        this.enrollmentOpen = true;
        this.faculty = faculty;
        this.enrolledStudents = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    public String getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSchedule() { return schedule; }
    public int getCapacity() { return capacity; }
    public boolean isEnrollmentOpen() { return enrollmentOpen; }
    public String getSyllabusPath() { return syllabusPath; }
    public FacultyProfile getFaculty() { return faculty; }
    public ArrayList<StudentProfile> getEnrolledStudents() { return enrolledStudents; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setEnrollmentOpen(boolean enrollmentOpen) { this.enrollmentOpen = enrollmentOpen; }
    public void setSyllabusPath(String syllabusPath) { this.syllabusPath = syllabusPath; }

    public boolean enrollStudent(StudentProfile sp) {
        if (enrolledStudents.size() < capacity && !enrolledStudents.contains(sp)) {
            enrolledStudents.add(sp);
            grades.add(0.0);
            return true;
        }
        return false;
    }

    public void assignGrade(StudentProfile sp, double grade) {
        int index = enrolledStudents.indexOf(sp);
        if (index >= 0) {
            grades.set(index, grade);
        }
    }

    public double getStudentGrade(StudentProfile sp) {
        int index = enrolledStudents.indexOf(sp);
        if (index >= 0) {
            return grades.get(index);
        }
        return 0.0;
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double total = 0.0;
        for (double g : grades) total += g;
        return total / grades.size();
    }

    public double calculateClassGPA() {
        if (grades.isEmpty()) return 0.0;
        double total = 0.0;
        for (double g : grades) {
            // Example: convert percentage to GPA scale
            if (g >= 90) total += 4.0;
            else if (g >= 80) total += 3.0;
            else if (g >= 70) total += 2.0;
            else if (g >= 60) total += 1.0;
            else total += 0.0;
        }
        return total / grades.size();
    }
    
    private double tuitionFee;

public double getTuitionFee() {
    return tuitionFee;
}

public void setTuitionFee(double tuitionFee) {
    this.tuitionFee = tuitionFee;
}

}
