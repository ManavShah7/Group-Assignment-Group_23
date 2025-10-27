package UserInterface.WorkAreas.AdminRole;
import UserInterface.WorkArea.AdminRole.SubPanels.*;

import Business.Business;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * @author Manav
 */
public class AdminRoleWorkAreaJPanel extends JPanel {

    private final Business business;
    private final JPanel rightPanel;

    private JButton btnMyProfile, btnAdminUsers, btnManageRecords, btnRegisterPersons, btnAnalytics;
    private JLabel titleLabel;
    private JPanel menuPanel, contentPanel;

    public AdminRoleWorkAreaJPanel(Business business, JPanel rightPanel) {
        this.business = business;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));

        // --- Title ---
        titleLabel = new JLabel("Admin Control Center", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Left-side Menu ---
        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(25, 34, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Menu Buttons ---
        btnMyProfile = createStyledButton("My Profile", e -> handleClick("profile"));
        btnAdminUsers = createStyledButton("Manage User Accounts", e -> handleClick("users"));
        btnRegisterPersons = createStyledButton("Register New Person", e -> handleClick("register"));
        btnManageRecords = createStyledButton("Manage Records", e -> handleClick("records"));
        btnAnalytics = createStyledButton("Analytics Dashboard", e -> handleClick("analytics"));

        gbc.gridx = 0;
        gbc.gridy = 0; menuPanel.add(btnMyProfile, gbc);
        gbc.gridy++; menuPanel.add(btnAdminUsers, gbc);
        gbc.gridy++; menuPanel.add(btnRegisterPersons, gbc);
        gbc.gridy++; menuPanel.add(btnManageRecords, gbc);
        gbc.gridy++; menuPanel.add(btnAnalytics, gbc);

        add(menuPanel, BorderLayout.WEST);

        // --- Center Content Panel ---
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(250, 250, 255));

        JLabel welcome = new JLabel("Welcome, System Administrator", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcome.setForeground(new Color(0, 102, 204));
        contentPanel.add(welcome, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
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

    /**
     * Handles section switching between admin sub-panels
     */
    private void handleClick(String section) {
        contentPanel.removeAll();
        JPanel panel;

        switch (section) {
            case "users":
                panel = new UserAccountManagementPanel(business, contentPanel);
                break;
            case "register":
                panel = new PersonRegistrationPanel(business, contentPanel);
                break;
            case "records":
                panel = new StudentFacultyRecordsPanel(business,contentPanel);
                break;
            case "analytics":
                panel = new AnalyticsDashboardPanel(business, contentPanel);
                break;
            case "profile":
                panel = new AdminProfilePanel(business, contentPanel);
                break;
            default:
                panel = new JPanel();
                panel.add(new JLabel("Section not implemented yet"));
        }

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
