package UserInterface.WorkAreas.AdminRole;

import Business.Business;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Admin Role Work Area — clean modern look (no emojis).
 * Simplified color scheme and consistent layout.
 * @author jaya
 */
public class AdminRoleWorkAreaJPanel extends JPanel {

    private Business business;
    private JPanel rightPanel;

    private JButton btnMyProfile, btnAdminUsers, btnManageFaculty, btnManageStudents, btnRegisterPersons;
    private JLabel titleLabel;

    public AdminRoleWorkAreaJPanel(Business business, JPanel rightPanel) {
        this.business = business;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));

        // --- Title Header ---
        titleLabel = new JLabel("Admin Control Center", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Left side menu ---
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(new Color(25, 34, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Buttons ---
        btnMyProfile = createStyledButton("My Profile", e -> handleClick("profile"));
        btnAdminUsers = createStyledButton("Manage User Accounts", e -> handleClick("users"));
        btnManageFaculty = createStyledButton("Manage Faculty", e -> handleClick("faculty"));
        btnManageStudents = createStyledButton("Manage Students", e -> handleClick("students"));
        btnRegisterPersons = createStyledButton("Register Persons (HR)", e -> handleClick("register"));

        gbc.gridx = 0;
        gbc.gridy = 0; menuPanel.add(btnMyProfile, gbc);
        gbc.gridy++; menuPanel.add(btnAdminUsers, gbc);
        gbc.gridy++; menuPanel.add(btnManageFaculty, gbc);
        gbc.gridy++; menuPanel.add(btnManageStudents, gbc);
        gbc.gridy++; menuPanel.add(btnRegisterPersons, gbc);

        add(menuPanel, BorderLayout.WEST);

        // --- Center Panel Placeholder ---
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(250, 250, 255));
        centerPanel.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, System Admin", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcome.setForeground(new Color(0, 102, 204));
        centerPanel.add(welcome, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 200), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }
        });

        button.addActionListener(action);
        return button;
    }

    private void handleClick(String section) {
        JOptionPane.showMessageDialog(this,
                "Opening " + section.toUpperCase() + " section (coming soon)",
                "Navigation", JOptionPane.INFORMATION_MESSAGE);
    }
}
