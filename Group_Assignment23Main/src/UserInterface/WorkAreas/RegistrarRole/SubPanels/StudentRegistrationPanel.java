package UserInterface.WorkAreas.RegistrarRole.SubPanels;

import Business.Business;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Student Registration Panel — allows Registrar to enroll/drop students from courses.
 * @author Manav
 */
public class StudentRegistrationPanel extends JPanel {

    private final Business business;
    private JTable tblStudents, tblCourses, tblEnrollments;
    private DefaultTableModel studentModel, courseModel, enrollModel;
    private JButton btnEnroll, btnDrop, btnRefresh;

    public StudentRegistrationPanel(Business business) {
        this.business = business;
        initComponents();
        loadMockData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        // --- Title ---
        JLabel title = new JLabel("Student Registration Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        // --- Main Split ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(280);
        splitPane.setResizeWeight(0.5);

        // --- Top: Students & Courses ---
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setBackground(new Color(245, 247, 255));

        // --- Students Table ---
        studentModel = new DefaultTableModel(new Object[]{"Student ID", "Name"}, 0);
        tblStudents = new JTable(studentModel);
        tblStudents.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblStudents.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblStudents.setRowHeight(25);
        topPanel.add(new JScrollPane(tblStudents));

        // --- Courses Table ---
        courseModel = new DefaultTableModel(new Object[]{"Course ID", "Course Name"}, 0);
        tblCourses = new JTable(courseModel);
        tblCourses.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblCourses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblCourses.setRowHeight(25);
        topPanel.add(new JScrollPane(tblCourses));

        // --- Bottom: Enrollments + Buttons ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 247, 255));

        enrollModel = new DefaultTableModel(new Object[]{"Student", "Course"}, 0);
        tblEnrollments = new JTable(enrollModel);
        tblEnrollments.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblEnrollments.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblEnrollments.setRowHeight(25);
        bottomPanel.add(new JScrollPane(tblEnrollments), BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(new Color(230, 238, 255));

        btnEnroll = makeButton("Enroll Student");
        btnDrop = makeButton("Drop Student");
        btnRefresh = makeButton("Refresh Data");

        btnPanel.add(btnEnroll);
        btnPanel.add(btnDrop);
        btnPanel.add(btnRefresh);

        bottomPanel.add(btnPanel, BorderLayout.SOUTH);

        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        add(splitPane, BorderLayout.CENTER);

        // --- Actions ---
        btnEnroll.addActionListener(e -> enrollStudent());
        btnDrop.addActionListener(e -> dropStudent());
        btnRefresh.addActionListener(e -> loadMockData());
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
        return btn;
    }

    private void loadMockData() {
        // mock student list
        studentModel.setRowCount(0);
        studentModel.addRow(new Object[]{"S1001", "Manav Shah"});
        studentModel.addRow(new Object[]{"S1002", "Preksha Praveen"});
        studentModel.addRow(new Object[]{"S1003", "Jayashree"});
   studentModel.addRow(new Object[]{"S1003", "Hiteshi"});
        // mock course list
        courseModel.setRowCount(0);
        courseModel.addRow(new Object[]{"C5100", "INFO 5100 - AED"});
        courseModel.addRow(new Object[]{"C5200", "INFO 6205 - Web Dev"});
        courseModel.addRow(new Object[]{"C5300", "INFO 6350 - DBMS"});

        enrollModel.setRowCount(0); // clear enrollments
    }

    private void enrollStudent() {
        int studentRow = tblStudents.getSelectedRow();
        int courseRow = tblCourses.getSelectedRow();

        if (studentRow < 0 || courseRow < 0) {
            JOptionPane.showMessageDialog(this, "Select both a student and a course to enroll!");
            return;
        }

        String studentName = (String) tblStudents.getValueAt(studentRow, 1);
        String courseName = (String) tblCourses.getValueAt(courseRow, 1);

        // prevent duplicate enrollments
        for (int i = 0; i < enrollModel.getRowCount(); i++) {
            if (enrollModel.getValueAt(i, 0).equals(studentName)
                    && enrollModel.getValueAt(i, 1).equals(courseName)) {
                JOptionPane.showMessageDialog(this, "Student already enrolled in this course!");
                return;
            }
        }

        enrollModel.addRow(new Object[]{studentName, courseName});
    }

    private void dropStudent() {
        int row = tblEnrollments.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an enrolled student to drop!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Drop this student from the course?",
                "Confirm Drop", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            enrollModel.removeRow(row);
        }
    }
}
