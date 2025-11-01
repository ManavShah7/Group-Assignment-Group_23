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
public class FacultyPerformanceReportPanel extends JPanel{
    private FacultyProfile facultyProfile;
    private CourseDirectory courseDirectory;

    private JComboBox<String> cmbSemester;
    private JButton btnGenerate;
    private JTable tblReport;
    private DefaultTableModel model;

    public FacultyPerformanceReportPanel(FacultyProfile facultyProfile, CourseDirectory courseDirectory) {
        this.facultyProfile = facultyProfile;
        this.courseDirectory = courseDirectory;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Header
        JLabel lblHeader = new JLabel("Course Performance Report", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 70, 140));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Top Filter Bar
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        filterPanel.setBackground(new Color(245, 248, 255));

        filterPanel.add(new JLabel("Select Semester:"));
        cmbSemester = new JComboBox<>(new String[]{
            "Fall 2025", "Spring 2025", "Summer 2025", "Fall 2024"
        });
        btnGenerate = new JButton("Generate Report");
        styleButton(btnGenerate);

        filterPanel.add(cmbSemester);
        filterPanel.add(btnGenerate);
        add(filterPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(
                new Object[]{"Course ID", "Title", "Average Grade", "Grade Distribution (A/B/C/D/F)", "Enrollment"}, 0);
        tblReport = new JTable(model);
        tblReport.setRowHeight(25);
        add(new JScrollPane(tblReport), BorderLayout.CENTER);

        // Event
        btnGenerate.addActionListener(e -> generateReport());
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(0, 85, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
    }

    private void generateReport() {
        model.setRowCount(0);
        String semester = cmbSemester.getSelectedItem().toString();

        // You can extend Course class to store semester info in future if needed
        for (Course c : courseDirectory.getCoursesByFaculty(facultyProfile)) {

            double avg = c.calculateAverageGrade();
            int total = c.getEnrolledStudents().size();

            int a = 0, b = 0, cc = 0, d = 0, f = 0;
            for (var sp : c.getEnrolledStudents()) {
                double g = c.getStudentGrade(sp);
                if (g >= 90) a++;
                else if (g >= 80) b++;
                else if (g >= 70) cc++;
                else if (g >= 60) d++;
                else f++;
            }

            String dist = String.format("A:%d | B:%d | C:%d | D:%d | F:%d", a, b, cc, d, f);
            model.addRow(new Object[]{
                c.getCourseId(),
                c.getTitle(),
                String.format("%.2f", avg),
                dist,
                total
            });
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No courses found for this semester.");
        }
    }
}
