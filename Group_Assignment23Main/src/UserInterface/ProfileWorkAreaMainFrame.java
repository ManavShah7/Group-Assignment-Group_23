package UserInterface;

import Business.Business;
import Business.ConfigureABusiness;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.StudentProfile;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;
import UserInterface.WorkAreas.AdminRole.AdminRoleWorkAreaJPanel;
import UserInterface.WorkAreas.FacultyRole.FacultyWorkAreaJPanel;
import UserInterface.WorkAreas.StudentRole.StudentWorkAreaJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main JFrame for Digital University portal
 * Clean modernized version with responsive split layout.
 * @author Manav
 */
public class ProfileWorkAreaMainFrame extends JFrame {

    private Business business;
    private JSplitPane splitPane;
    private JPanel loginPanel, rightPanel;
    private JLabel lblUsername, lblPassword, lblWelcome;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public ProfileWorkAreaMainFrame() {
        business = ConfigureABusiness.initialize();
        initComponents();
        setTitle("Digital University ⚡");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        // Frame setup
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLayout(new BorderLayout());

        // Panels
        loginPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new BorderLayout());
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loginPanel, rightPanel);

        // Colors
        Color darkNavy = new Color(18, 25, 39);
        Color blueAccent = new Color(0, 102, 204);
        Color lightBg = new Color(245, 248, 255);

        // Fonts
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 26);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        // --- Left (Login Panel) ---
        loginPanel.setBackground(darkNavy);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblWelcome = new JLabel("Digital University", SwingConstants.CENTER);
        lblWelcome.setFont(headerFont);
        lblWelcome.setForeground(Color.WHITE);

        lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(labelFont);

        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(labelFont);

        txtUsername = new JTextField(14);
        txtPassword = new JPasswordField(14);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(blueAccent);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(buttonFont);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(blueAccent);
            }
        });

        // Layout login components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(lblWelcome, gbc);

        gbc.gridy++;
        loginPanel.add(lblUsername, gbc);
        gbc.gridy++;
        loginPanel.add(txtUsername, gbc);
        gbc.gridy++;
        loginPanel.add(lblPassword, gbc);
        gbc.gridy++;
        loginPanel.add(txtPassword, gbc);
        gbc.gridy++;
        loginPanel.add(btnLogin, gbc);

        // --- Right (Dynamic Content) ---
        rightPanel.setBackground(lightBg);
        JLabel splash = new JLabel("Welcome to Digital University ⚡", SwingConstants.CENTER);
        splash.setFont(new Font("Segoe UI", Font.BOLD, 26));
        splash.setForeground(new Color(0, 70, 150));
        rightPanel.add(splash, BorderLayout.CENTER);

        // --- SplitPane setup ---
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(4);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        add(splitPane, BorderLayout.CENTER);

        // Action
        btnLogin.addActionListener(e -> handleLogin());
    }

    /** Handles user authentication and role-based navigation */
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both username and password 😭");
            return;
        }

        UserAccountDirectory uad = business.getUserAccountDirectory();
        UserAccount ua = uad.authenticateUser(username, password);

        if (ua == null) {
            JOptionPane.showMessageDialog(this, "Invalid credentials ⚠️");
            return;
        }

        // Successful login
        JPanel workArea;
        if (ua.getAssociatedPersonProfile() instanceof EmployeeProfile) {
            workArea = new AdminRoleWorkAreaJPanel(business, rightPanel);
        } else if (ua.getAssociatedPersonProfile() instanceof StudentProfile) {
            workArea = new StudentWorkAreaJPanel(
                    business,
                    (StudentProfile) ua.getAssociatedPersonProfile(),
                    rightPanel
            );
        } else {
            workArea = new FacultyWorkAreaJPanel(business, rightPanel);
        }

        rightPanel.removeAll();
        rightPanel.add(workArea, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProfileWorkAreaMainFrame frame = new ProfileWorkAreaMainFrame();
            frame.setVisible(true);
        });
    }
}
