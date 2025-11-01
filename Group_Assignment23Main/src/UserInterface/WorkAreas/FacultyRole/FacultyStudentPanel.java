/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

/**
 *
 * @author prekshapraveen
 */

import Business.Course.Course;
import Business.Course.CourseDirectory;
import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentProfile;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;

public class FacultyStudentPanel extends JPanel {
    private FacultyProfile facultyProfile;
    private CourseDirectory courseDirectory;

    private JComboBox<String> cmbCourses;
    private JTable tblStudents;
    private DefaultTableModel model;
    private JTextField txtGrade;
    private JButton btnAssignGrade, btnComputeGPA, btnRankStudents;
    private JLabel lblGPA;

    public FacultyStudentPanel(FacultyProfile facultyProfile, CourseDirectory courseDirectory) {
        this.facultyProfile = facultyProfile;
        this.courseDirectory = courseDirectory;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblHeader = new JLabel("Student Management", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 70, 140));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Course selector
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(240, 245, 255));
        topPanel.add(new JLabel("Select Course:"));
        cmbCourses = new JComboBox<>();
        topPanel.add(cmbCourses);
        add(topPanel, BorderLayout.NORTH);

        // Table for enrolled students
        model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Grade (%)", "Letter Grade"}, 0);
        tblStudents = new JTable(model);
        tblStudents.setRowHeight(25);
        add(new JScrollPane(tblStudents), BorderLayout.CENTER);

        // Bottom panel for grading and GPA
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(245, 248, 255));

        txtGrade = new JTextField(5);
        btnAssignGrade = new JButton("Assign Grade");
        btnComputeGPA = new JButton("Compute GPA");
        btnRankStudents = new JButton("Rank Students");
        lblGPA = new JLabel("Class GPA: -");

        styleButton(btnAssignGrade);
        styleButton(btnComputeGPA);
        styleButton(btnRankStudents);

        bottomPanel.add(new JLabel("Enter Grade (%):"));
        bottomPanel.add(txtGrade);
        bottomPanel.add(btnAssignGrade);
        bottomPanel.add(btnComputeGPA);
        bottomPanel.add(btnRankStudents);
        bottomPanel.add(lblGPA);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event listeners
        cmbCourses.addActionListener(e -> populateStudents());
        btnAssignGrade.addActionListener(e -> assignGrade());
        btnComputeGPA.addActionListener(e -> computeGPA());
        btnRankStudents.addActionListener(e -> rankStudents());

        populateCourses();
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(51, 153, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(30, 130, 230)); }
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(51, 153, 255)); }
        });
    }

    private void populateCourses() {
        cmbCourses.removeAllItems();
        for (Course c : courseDirectory.getCoursesByFaculty(facultyProfile)) {
            cmbCourses.addItem(c.getCourseId() + " - " + c.getTitle());
        }
    }

    private void populateStudents() {
        model.setRowCount(0);
        int index = cmbCourses.getSelectedIndex();
        if (index < 0) return;
        Course selectedCourse = courseDirectory.getCoursesByFaculty(facultyProfile).get(index);

        for (StudentProfile sp : selectedCourse.getEnrolledStudents()) {
            double grade = selectedCourse.getStudentGrade(sp);
            String letter = getLetterGrade(grade);
            model.addRow(new Object[]{sp.getPerson().getPersonId(), sp.getPerson().getName(), grade, letter});
        }
    }

    private void assignGrade() {
        int row = tblStudents.getSelectedRow();
        int index = cmbCourses.getSelectedIndex();
        if (row < 0 || index < 0) {
            JOptionPane.showMessageDialog(this, "Select a course and student first.");
            return;
        }

        try {
            double grade = Double.parseDouble(txtGrade.getText());
            Course selectedCourse = courseDirectory.getCoursesByFaculty(facultyProfile).get(index);
            StudentProfile sp = selectedCourse.getEnrolledStudents().get(row);
            selectedCourse.assignGrade(sp, grade);
            JOptionPane.showMessageDialog(this, "Grade assigned successfully!");
            populateStudents();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric grade (0-100).");
        }
    }

    private void computeGPA() {
        int index = cmbCourses.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Select a course first.");
            return;
        }

        Course selectedCourse = courseDirectory.getCoursesByFaculty(facultyProfile).get(index);
        double gpa = selectedCourse.calculateClassGPA();
        lblGPA.setText("Class GPA: " + String.format("%.2f", gpa));
    }

    private void rankStudents() {
        int index = cmbCourses.getSelectedIndex();
        if (index < 0) return;
        Course selectedCourse = courseDirectory.getCoursesByFaculty(facultyProfile).get(index);

        selectedCourse.getEnrolledStudents().sort(Comparator.comparingDouble(selectedCourse::getStudentGrade).reversed());
        JOptionPane.showMessageDialog(this, "Students ranked by total grade percentage.");
        populateStudents();
    }

    private String getLetterGrade(double grade) {
        if (grade >= 90) return "A";
        if (grade >= 80) return "B";
        if (grade >= 70) return "C";
        if (grade >= 60) return "D";
        return "F";
    }
}
