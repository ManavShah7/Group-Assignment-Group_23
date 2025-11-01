package UserInterface.WorkArea.AdminRole.SubPanels;

import Business.Business;
import Business.Person.Person;
import Business.Profiles.Profile;
import Business.UserAccounts.Role;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Admin panel for managing User Accounts (create, edit, delete).
 * Modernized UI + proper color contrast and layout polish.
 * @author hiteshikawadia
 */
public class UserAccountManagementPanel extends JPanel {

    private final Business business;
    private JTable tblAccounts;
    private DefaultTableModel model;
    private JTextField txtUsername, txtPassword;
    private JComboBox<String> roleDropdown;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh;

    public UserAccountManagementPanel(Business business, JPanel rightPanel) {
        this.business = business;
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 243, 255));

        // 🔹 Header
        JLabel title = new JLabel("User Account Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(25, 55, 120));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // 🔹 Table setup
        model = new DefaultTableModel(new Object[]{"Username", "Role", "Person Name"}, 0);
        tblAccounts = new JTable(model);
        tblAccounts.setRowHeight(28);
        tblAccounts.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblAccounts.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tblAccounts.getTableHeader().setBackground(new Color(25, 55, 120));
        tblAccounts.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tblAccounts);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Form section
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(225, 232, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 230), 1, true),
                "Create / Edit Account",
                0, 0, new Font("Segoe UI", Font.BOLD, 16), new Color(25, 55, 120)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");
        JLabel lblRole = new JLabel("Role:");

        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtUsername = new JTextField(12);
        txtPassword = new JTextField(12);
        roleDropdown = new JComboBox<>(new String[]{"Admin", "Student", "Faculty", "Registrar"});

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(lblUser, gbc);
        gbc.gridx = 1; formPanel.add(txtUsername, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(lblPass, gbc);
        gbc.gridx = 1; formPanel.add(txtPassword, gbc);
        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(lblRole, gbc);
        gbc.gridx = 1; formPanel.add(roleDropdown, gbc);

        add(formPanel, BorderLayout.NORTH);

        // 🔹 Buttons
        btnAdd = new JButton("Add Account");
        btnUpdate = new JButton("Update Password");
        btnDelete = new JButton("Delete Account");
        btnRefresh = new JButton("Refresh Table");

        styleButton(btnAdd);
        styleButton(btnUpdate);
        styleButton(btnDelete);
        styleButton(btnRefresh);

        btnAdd.addActionListener(this::handleAdd);
        btnUpdate.addActionListener(this::handleUpdate);
        btnDelete.addActionListener(this::handleDelete);
        btnRefresh.addActionListener(e -> populateTable());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnPanel.setBackground(new Color(240, 243, 255));
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 70, 160));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 50, 120)),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 100, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 70, 160));
            }
        });
    }

    /** ✅ Populates the table */
    private void populateTable() {
        model.setRowCount(0);
        for (UserAccount ua : business.getUserAccountDirectory().getUserAccountList()) {
            Profile p = ua.getAssociatedPersonProfile();
            String name = (p != null && p.getPerson() != null)
                    ? p.getPerson().getName()
                    : "N/A";
            model.addRow(new Object[]{ua.getUsername(), ua.getRole().name(), name});
        }
    }

    /** ✅ Add new user account */
    private void handleAdd(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String roleStr = (String) roleDropdown.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Username and Password required!");
            return;
        }

        UserAccountDirectory uad = business.getUserAccountDirectory();
        for (UserAccount ua : uad.getUserAccountList()) {
            if (ua.getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(this, "🚫 Username already exists!");
                return;
            }
        }

        Person person = business.getPersonDirectory().newPerson(username);
        Profile profile;

        switch (roleStr) {
            case "Admin":
                profile = business.getEmployeeDirectory().newEmployeeProfile(person);
                break;
            case "Student":
                profile = business.getStudentDirectory().newStudentProfile(person);
                break;
            case "Faculty":
                profile = business.getFacultyDirectory().newFacultyProfile(person);
                break;
            case "Registrar":
                profile = business.getRegistrarDirectory().newRegistrarProfile(person);
                break;
            default:
                profile = null;
        }

        if (profile != null) {
            Role role = Role.valueOf(roleStr.toUpperCase());
            uad.newUserAccount(profile, username, password, role);
            JOptionPane.showMessageDialog(this, "✅ Account created successfully!");
            populateTable();
            clearFields();
        }
    }

    /** ✅ Update user password */
    private void handleUpdate(ActionEvent e) {
        int row = tblAccounts.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Select an account to update!");
            return;
        }

        String username = (String) model.getValueAt(row, 0);
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");

        if (newPassword == null || newPassword.trim().isEmpty()) return;

        for (UserAccount ua : business.getUserAccountDirectory().getUserAccountList()) {
            if (ua.getUsername().equals(username)) {
                ua.setPassword(newPassword.trim());
                JOptionPane.showMessageDialog(this, "✅ Password updated!");
                populateTable();
                return;
            }
        }
    }

    /** ✅ Delete user account */
    private void handleDelete(ActionEvent e) {
        int row = tblAccounts.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Select an account to delete!");
            return;
        }

        String username = (String) model.getValueAt(row, 0);
        UserAccountDirectory uad = business.getUserAccountDirectory();

        boolean removed = uad.getUserAccountList().removeIf(ua -> ua.getUsername().equals(username));

        if (removed) {
            JOptionPane.showMessageDialog(this, "🗑️ Account deleted successfully!");
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error: Could not delete account.");
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        roleDropdown.setSelectedIndex(0);
    }
}
