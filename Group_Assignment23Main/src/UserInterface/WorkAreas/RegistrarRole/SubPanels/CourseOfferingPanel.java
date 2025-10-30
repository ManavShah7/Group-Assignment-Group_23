package UserInterface.WorkAreas.RegistrarRole.SubPanels;

import Business.Business;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Course Offering Management Panel for Registrar
 * Supports Add, Edit, Delete course offerings.
 * @author Manav
 */
public class CourseOfferingPanel extends JPanel {

    private final Business business;
    private JTable tblCourses;
    private DefaultTableModel model;
    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;

    public CourseOfferingPanel(Business business) {
        this.business = business;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        // --- Title ---
        JLabel title = new JLabel("Course Offering Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        // --- Table Setup ---
        model = new DefaultTableModel(new Object[]{"Course", "Faculty", "Room", "Time", "Capacity"}, 0);
        tblCourses = new JTable(model);
        tblCourses.setRowHeight(25);
        tblCourses.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblCourses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(new JScrollPane(tblCourses), BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(new Color(230, 238, 255));

        btnAdd = makeButton("Add Course");
        btnEdit = makeButton("Edit Course");
        btnDelete = makeButton("Delete Course");
        btnRefresh = makeButton("Refresh");

        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);
        add(btnPanel, BorderLayout.SOUTH);

        // --- Actions ---
        btnAdd.addActionListener(e -> handleAdd());
        btnEdit.addActionListener(e -> handleEdit());
        btnDelete.addActionListener(e -> handleDelete());
        btnRefresh.addActionListener(e -> populateTable());
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 102, 204));   // ⚽ Bright blue, visible AF
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

    private void populateTable() {
        // Temporary demo data (you can later load this from a CourseDirectory)
        model.setRowCount(0);
        model.addRow(new Object[]{"INFO 5100", "Dr. Smith", "Room 202", "MWF 10-11", 40});
        model.addRow(new Object[]{"INFO 6150", "Prof. Brown", "Room 305", "TTh 2-3:30", 35});
    }

    private void handleAdd() {
        JTextField courseField = new JTextField();
        JTextField facultyField = new JTextField();
        JTextField roomField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField capField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Course Name:"));
        panel.add(courseField);
        panel.add(new JLabel("Faculty:"));
        panel.add(facultyField);
        panel.add(new JLabel("Room:"));
        panel.add(roomField);
        panel.add(new JLabel("Time:"));
        panel.add(timeField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Course", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String course = courseField.getText().trim();
            String faculty = facultyField.getText().trim();
            String room = roomField.getText().trim();
            String time = timeField.getText().trim();
            String cap = capField.getText().trim();

            if (course.isEmpty() || faculty.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Course and Faculty are required!");
                return;
            }

            model.addRow(new Object[]{course, faculty, room, time, cap});
        }
    }

    private void handleEdit() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a course to edit!");
            return;
        }

        String course = (String) model.getValueAt(row, 0);
        String faculty = (String) model.getValueAt(row, 1);
        String room = (String) model.getValueAt(row, 2);
        String time = (String) model.getValueAt(row, 3);
        String cap = model.getValueAt(row, 4).toString();

        JTextField courseField = new JTextField(course);
        JTextField facultyField = new JTextField(faculty);
        JTextField roomField = new JTextField(room);
        JTextField timeField = new JTextField(time);
        JTextField capField = new JTextField(cap);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Course Name:"));
        panel.add(courseField);
        panel.add(new JLabel("Faculty:"));
        panel.add(facultyField);
        panel.add(new JLabel("Room:"));
        panel.add(roomField);
        panel.add(new JLabel("Time:"));
        panel.add(timeField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Course", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            model.setValueAt(courseField.getText(), row, 0);
            model.setValueAt(facultyField.getText(), row, 1);
            model.setValueAt(roomField.getText(), row, 2);
            model.setValueAt(timeField.getText(), row, 3);
            model.setValueAt(capField.getText(), row, 4);
        }
    }

    private void handleDelete() {
        int row = tblCourses.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a course to delete!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this course?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(row);
        }
    }
}
