package UserInterface.WorkArea.AdminRole.SubPanels;

import Business.Business;
import Business.Person.Person;
import Business.UserAccounts.UserAccount;
import javax.swing.*;
import java.awt.*;

/**
 * Admin Profile Panel — modernized look & fixed layout.
 * Matches updated Admin UI theme.
 * @author Hiteshikawadia
 */
public class AdminProfilePanel extends JPanel {

    private final Business business;
    private JTextField txtName, txtEmail, txtPhone, txtUsername, txtPassword;
    private JButton btnSave;
    private UserAccount adminAccount;

    public AdminProfilePanel(Business business, JPanel rightPanel) {
        this.business = business;
        this.adminAccount = findAdminAccount();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 248, 255));

        // --- Header ---
        JLabel title = new JLabel("My Profile", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 140));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // --- Form Section ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 120, 40, 120));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);

        JLabel lblName = new JLabel("Full Name:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Phone:");
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        lblName.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblPhone.setFont(labelFont);
        lblUsername.setFont(labelFont);
        lblPassword.setFont(labelFont);

        txtName = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhone = new JTextField(20);
        txtUsername = new JTextField(20);
        txtPassword = new JTextField(20);

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(lblName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(lblPhone, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPhone, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(lblUsername, gbc);
        gbc.gridx = 1;
        formPanel.add(txtUsername, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc); y++;

        // --- Save Button ---
        btnSave = new JButton("Save Changes");
        stylePrimaryButton(btnSave);
        btnSave.addActionListener(e -> saveProfile());

        gbc.gridx = 1; gbc.gridy = y + 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(btnSave, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
    }

    /** Finds admin account from UserAccountDirectory */
    private UserAccount findAdminAccount() {
        for (UserAccount ua : business.getUserAccountDirectory().getUserAccountList()) {
            if (ua.getRole().toString().equalsIgnoreCase("ADMIN")) {
                return ua;
            }
        }
        return null;
    }

    /** Loads admin data into text fields */
    private void loadData() {
        if (adminAccount == null) {
            JOptionPane.showMessageDialog(this, "Admin account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Person p = adminAccount.getAssociatedPersonProfile().getPerson();
        txtName.setText(p.getName());
        txtEmail.setText(p.getEmail());
        txtPhone.setText(p.getPhone());
        txtUsername.setText(adminAccount.getUsername());
        txtPassword.setText(adminAccount.getPassword());
    }

    /** Saves updated admin data */
    private void saveProfile() {
        if (adminAccount == null) return;

        Person p = adminAccount.getAssociatedPersonProfile().getPerson();
        p.setName(txtName.getText().trim());
        p.setEmail(txtEmail.getText().trim());
        p.setPhone(txtPhone.getText().trim());
        adminAccount.setUsername(txtUsername.getText().trim());
        adminAccount.setPassword(txtPassword.getText().trim());

        JOptionPane.showMessageDialog(this, "✅ Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
