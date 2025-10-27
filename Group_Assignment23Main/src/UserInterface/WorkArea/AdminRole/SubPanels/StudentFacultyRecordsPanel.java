package UserInterface.WorkAreas.AdminRole.SubPanels;

import Business.Business;
import Business.Person.Person;
import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Admin — Manage and search Student & Faculty records.
 * Full visual + functional upgrade (matching other panels).
 * @author Manav
 */
public class StudentFacultyRecordsPanel extends JPanel {

    private final Business business;
    private JTable tblRecords;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JComboBox<String> searchTypeDropdown;
    private JButton btnSearch, btnEdit, btnDelete, btnRefresh;

    public StudentFacultyRecordsPanel(Business business, JPanel parentPanel) {
        this.business = business;
        initComponents();
        populateTable("");
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 243, 255));

        // 🔹 Title
        JLabel title = new JLabel("Student & Faculty Records Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 55, 120));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // 🔹 Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(new Color(225, 232, 250));

        JLabel lblSearch = new JLabel("Search by:");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        searchTypeDropdown = new JComboBox<>(new String[]{"Name", "ID", "Department"});
        txtSearch = new JTextField(15);

        btnSearch = new JButton("Search");
        btnRefresh = new JButton("Refresh");
        styleBlueButton(btnSearch);
        styleGrayButton(btnRefresh);

        btnSearch.addActionListener(e -> searchRecords());
        btnRefresh.addActionListener(e -> populateTable(""));

        searchPanel.add(lblSearch);
        searchPanel.add(searchTypeDropdown);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);

        add(searchPanel, BorderLayout.NORTH);

        // 🔹 Table
        model = new DefaultTableModel(new Object[]{"ID", "Name", "Role", "Department", "Email"}, 0);
        tblRecords = new JTable(model);
        tblRecords.setRowHeight(28);
        tblRecords.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblRecords.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tblRecords.getTableHeader().setBackground(new Color(25, 55, 120));
        tblRecords.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tblRecords);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Bottom Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnPanel.setBackground(new Color(240, 243, 255));

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        styleBlueButton(btnEdit);
        styleRedButton(btnDelete);

        btnEdit.addActionListener(this::handleEdit);
        btnDelete.addActionListener(this::handleDelete);

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        add(btnPanel, BorderLayout.SOUTH);
    }

    // 🔹 Styles
    private void styleBlueButton(JButton btn) {
           btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 100, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 70, 160));
            }
        });
    }

    private void styleGrayButton(JButton btn) {
          btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(180, 180, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(160, 160, 160));
            }
        });
    }

    private void styleRedButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(220, 70, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(200, 50, 50));
            }
        });
    }

    // 🔹 Populate Table
    private void populateTable(String filter) {
        model.setRowCount(0);

        for (StudentProfile sp : business.getStudentDirectory().getStudentList()) {
            Person p = sp.getPerson();
            if (filter.isEmpty() || p.getPersonName().toLowerCase().contains(filter.toLowerCase())) {
                model.addRow(new Object[]{
                        p.getPersonId(),
                        p.getPersonName(),
                        "Student",
                        sp.getDepartment() == null ? "N/A" : sp.getDepartment(),
                        p.getEmail()
                });
            }
        }

        for (FacultyProfile fp : business.getFacultyDirectory().getFacultyList()) {
            Person p = fp.getPerson();
            if (filter.isEmpty() || p.getPersonName().toLowerCase().contains(filter.toLowerCase())) {
                model.addRow(new Object[]{
                        p.getPersonId(),
                        p.getPersonName(),
                        "Faculty",
                        fp.getDepartment() == null ? "N/A" : fp.getDepartment(),
                        p.getEmail()
                });
            }
        }
    }

    // 🔹 Search
    private void searchRecords() {
        String type = (String) searchTypeDropdown.getSelectedItem();
        String query = txtSearch.getText().trim().toLowerCase();

        model.setRowCount(0);
        if (query.isEmpty()) {
            populateTable("");
            return;
        }

        for (StudentProfile sp : business.getStudentDirectory().getStudentList()) {
            Person p = sp.getPerson();
            if (matches(p, sp.getDepartment(), type, query)) {
                model.addRow(new Object[]{p.getPersonId(), p.getPersonName(), "Student",
                        sp.getDepartment() == null ? "N/A" : sp.getDepartment(), p.getEmail()});
            }
        }

        for (FacultyProfile fp : business.getFacultyDirectory().getFacultyList()) {
            Person p = fp.getPerson();
            if (matches(p, fp.getDepartment(), type, query)) {
                model.addRow(new Object[]{p.getPersonId(), p.getPersonName(), "Faculty",
                        fp.getDepartment() == null ? "N/A" : fp.getDepartment(), p.getEmail()});
            }
        }
    }

    private boolean matches(Person p, String dept, String type, String query) {
        switch (type) {
            case "Name":
                return p.getPersonName().toLowerCase().contains(query);
            case "ID":
                return p.getPersonId().toLowerCase().contains(query);
            case "Department":
                return dept != null && dept.toLowerCase().contains(query);
            default:
                return false;
        }
    }

    // 🔹 Edit
    private void handleEdit(ActionEvent e) {
        int row = tblRecords.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Select a record to edit!");
            return;
        }

        String name = (String) model.getValueAt(row, 1);
        String newDept = JOptionPane.showInputDialog(this, "Enter new department:");
        String newEmail = JOptionPane.showInputDialog(this, "Enter new email:");

        if ((newDept == null || newDept.isEmpty()) && (newEmail == null || newEmail.isEmpty())) return;

        for (StudentProfile sp : business.getStudentDirectory().getStudentList()) {
            if (sp.getPerson().getPersonName().equals(name)) {
                if (newDept != null && !newDept.isEmpty()) sp.setDepartment(newDept);
                if (newEmail != null && !newEmail.isEmpty()) sp.getPerson().setEmail(newEmail);
                JOptionPane.showMessageDialog(this, "✅ Student record updated!");
                populateTable("");
                return;
            }
        }

        for (FacultyProfile fp : business.getFacultyDirectory().getFacultyList()) {
            if (fp.getPerson().getPersonName().equals(name)) {
                if (newDept != null && !newDept.isEmpty()) fp.setDepartment(newDept);
                if (newEmail != null && !newEmail.isEmpty()) fp.getPerson().setEmail(newEmail);
                JOptionPane.showMessageDialog(this, "✅ Faculty record updated!");
                populateTable("");
                return;
            }
        }
    }

    // 🔹 Delete
    private void handleDelete(ActionEvent e) {
        int row = tblRecords.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Select a record to delete!");
            return;
        }

        String name = (String) model.getValueAt(row, 1);
        business.getStudentDirectory().getStudentList().removeIf(s -> s.getPerson().getPersonName().equals(name));
        business.getFacultyDirectory().getFacultyList().removeIf(f -> f.getPerson().getPersonName().equals(name));

        JOptionPane.showMessageDialog(this, "🗑️ Record deleted successfully!");
        populateTable("");
    }
}
