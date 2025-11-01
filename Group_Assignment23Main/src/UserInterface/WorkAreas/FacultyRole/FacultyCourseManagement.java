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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FacultyCourseManagementPanel extends JPanel {
    private FacultyProfile faculty;
    private CourseDirectory courseDirectory;
    private JPanel rightPanel;

    private JTable tblCourses;
    private DefaultTableModel model;
    private JTextField txtTitle, txtDesc, txtSchedule, txtCapacity;
    private JButton btnUpdate, btnUploadSyllabus, btnToggleEnrollment, btnBack;
    
    public FacultyCourseManagementPanel(JPanel rightPanel, FacultyProfile faculty, CourseDirectory courseDirectory) {
        this.rightPanel = rightPanel;
        this.faculty = faculty;
        this.courseDirectory = courseDirectory;

        initComponents();
        populateCourses();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblHeader = new JLabel("Manage Courses", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 70, 140));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(
                new Object[]{"Course ID", "Title", "Description", "Schedule", "Capacity", "Enrollment", "Syllabus"}, 0);
        tblCourses = new JTable(model);
        tblCourses.setRowHeight(25);
        tblCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tblCourses), BorderLayout.CENTER);

        // Details form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit Course Details"));
        formPanel.setBackground(new Color(245, 248, 255));

        formPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField(); formPanel.add(txtTitle);

        formPanel.add(new JLabel("Description:"));
        txtDesc = new JTextField(); formPanel.add(txtDesc);

        formPanel.add(new JLabel("Schedule:"));
        txtSchedule = new JTextField(); formPanel.add(txtSchedule);

        formPanel.add(new JLabel("Capacity:"));
        txtCapacity = new JTextField(); formPanel.add(txtCapacity);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnUpdate = new JButton("Update Details");
        btnUploadSyllabus = new JButton("Upload Syllabus");
        btnToggleEnrollment = new JButton("Open/Close Enrollment");
        btnBack = new JButton("Back");

        styleButton(btnUpdate);
        styleButton(btnUploadSyllabus);
        styleButton(btnToggleEnrollment);
        styleButton(btnBack);

        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnUploadSyllabus);
        buttonPanel.add(btnToggleEnrollment);
        buttonPanel.add(btnBack);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(formPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // Button actions
        tblCourses.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loadSelectedCourse();
            }
        });

        btnUpdate.addActionListener(e -> updateCourse());
        btnUploadSyllabus.addActionListener(e -> uploadSyllabus());
        btnToggleEnrollment.addActionListener(e -> toggleEnrollment());
        btnBack.addActionListener(e -> goBack());
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
        model.setRowCount(0);
        for (Course c : courseDirectory.getCoursesByFaculty(faculty)) {
            model.addRow(new Object[]{
                    c.getCourseId(),
                    c.getTitle(),
                    c.getDescription(),
                    c.getSchedule(),
                    c.getCapacity(),
                    c.isEnrollmentOpen() ? "Open" : "Closed",
                    c.getSyllabusPath() != null ? "Uploaded" : "Not uploaded"
            });
        }
    }

    private void loadSelectedCourse() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) return;
        txtTitle.setText(model.getValueAt(row, 1).toString());
        txtDesc.setText(model.getValueAt(row, 2).toString());
        txtSchedule.setText(model.getValueAt(row, 3).toString());
        txtCapacity.setText(model.getValueAt(row, 4).toString());
    }

    private void updateCourse() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a course to update.");
            return;
        }

        String id = model.getValueAt(row, 0).toString();
        Course course = courseDirectory.findCourseById(id);

        try {
            course.setTitle(txtTitle.getText());
            course.setDescription(txtDesc.getText());
            course.setSchedule(txtSchedule.getText());
            course.setCapacity(Integer.parseInt(txtCapacity.getText()));

            JOptionPane.showMessageDialog(this, "Course updated successfully!");
            populateCourses();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your fields.");
        }
    }

    private void uploadSyllabus() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a course to upload syllabus.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String id = model.getValueAt(row, 0).toString();
            Course course = courseDirectory.findCourseById(id);
            course.setSyllabusPath(file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Syllabus uploaded for " + course.getTitle());
            populateCourses();
        }
    }

    private void toggleEnrollment() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a course first.");
            return;
        }
        String id = model.getValueAt(row, 0).toString();
        Course c = courseDirectory.findCourseById(id);
        c.setEnrollmentOpen(!c.isEnrollmentOpen());
        JOptionPane.showMessageDialog(this,
                "Enrollment is now " + (c.isEnrollmentOpen() ? "OPEN" : "CLOSED") + " for " + c.getTitle());
        populateCourses();
    }

    private void goBack() {
        CardLayout layout = (CardLayout) rightPanel.getLayout();
        layout.previous(rightPanel);
    }
}
