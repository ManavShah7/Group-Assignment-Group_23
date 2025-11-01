/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Course.Course;
import Business.Course.CourseDirectory;
import Business.Profiles.FacultyProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author prekshapraveen
 */
public class FacultyTuitionInsightPanel extends JPanel{
    private FacultyProfile facultyProfile;
    private CourseDirectory courseDirectory;
    private JTable tblTuition;
    private DefaultTableModel model;

    public FacultyTuitionInsightPanel(FacultyProfile facultyProfile, CourseDirectory courseDirectory) {
        this.facultyProfile = facultyProfile;
        this.courseDirectory = courseDirectory;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblHeader = new JLabel("Tuition / Enrollment Insight", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 70, 140));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(
                new Object[]{"Course ID", "Course Title", "Enrollment Count", "Tuition per Student ($)", "Total Tuition ($)"}, 0);
        tblTuition = new JTable(model);
        tblTuition.setRowHeight(25);
        add(new JScrollPane(tblTuition), BorderLayout.CENTER);

        // Generate Data button
        JButton btnGenerate = new JButton("Generate Insights");
        styleButton(btnGenerate);
        btnGenerate.addActionListener(e -> generateTuitionData());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        bottomPanel.setBackground(new Color(245, 248, 255));
        bottomPanel.add(btnGenerate);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(new Color(0, 85, 180)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(new Color(0, 102, 204)); }
        });
    }

    private void generateTuitionData() {
        model.setRowCount(0);
        double overallTuition = 0.0;

        // Loop through all courses assigned to this faculty
        for (Course c : courseDirectory.getCoursesByFaculty(facultyProfile)) {
            int enrollment = c.getEnrolledStudents().size();

            // Tuition per student — can be fixed or from the Course object
            double tuitionPerStudent = c.getTuitionFee() > 0 ? c.getTuitionFee() : 1200.00;
            double total = enrollment * tuitionPerStudent;
            overallTuition += total;

            model.addRow(new Object[]{
                    c.getCourseId(),
                    c.getTitle(),
                    enrollment,
                    String.format("%.2f", tuitionPerStudent),
                    String.format("%.2f", total)
            });
        }

        JOptionPane.showMessageDialog(this,
                "Total tuition collected across all courses: $" + String.format("%.2f", overallTuition),
                "Tuition Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
