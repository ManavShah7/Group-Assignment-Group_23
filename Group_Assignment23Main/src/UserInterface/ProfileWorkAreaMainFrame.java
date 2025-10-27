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

 * @author Manav
 */
public class ProfileWorkAreaMainFrame extends javax.swing.JFrame {

    private Business business;
    private JSplitPane splitPane;
    private JPanel loginPanel, rightPanel;
    private JLabel userLabel, passLabel, welcomeLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public ProfileWorkAreaMainFrame() {
        initComponents();
        business = ConfigureABusiness.initialize();
        setTitle("Digital University ⚡");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        splitPane = new JSplitPane();
        loginPanel = new JPanel();
        rightPanel = new JPanel();
        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        userField = new JTextField(12);
        passField = new JPasswordField(12);
        loginButton = new JButton("Login");
        welcomeLabel = new JLabel("Digital University Portal", SwingConstants.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        // --- Colors & Fonts ---
        Color bgColor = new Color(25, 34, 50); // dark navy background
        Color accent = new Color(51, 153, 255); // bright blue
        Color textColor = Color.WHITE;
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 22);

        // --- Login Panel ---
        loginPanel.setBackground(bgColor);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        userLabel.setForeground(textColor);
        passLabel.setForeground(textColor);
        userLabel.setFont(labelFont);
        passLabel.setFont(labelFont);

        loginButton.setBackground(accent);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(buttonFont);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- Add components with layout ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);
        gbc.gridy++;
        loginPanel.add(userField, gbc);
        gbc.gridy++;
        loginPanel.add(passLabel, gbc);
        gbc.gridy++;
        loginPanel.add(passField, gbc);
        gbc.gridy++;
        loginPanel.add(loginButton, gbc);

        // --- Right Panel (Welcome screen) ---
        rightPanel.setBackground(new Color(245, 248, 255));
        rightPanel.setLayout(new BorderLayout());
        welcomeLabel.setFont(headerFont);
        welcomeLabel.setForeground(new Color(0, 102, 204));
        rightPanel.add(welcomeLabel, BorderLayout.CENTER);

        // --- Split Pane ---
        splitPane.setDividerLocation(300);
        splitPane.setLeftComponent(loginPanel);
        splitPane.setRightComponent(rightPanel);
        splitPane.setDividerSize(3);
        add(splitPane, BorderLayout.CENTER);

        // --- Button action ---
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bruh... enter both username and password 😭");
            return;
        }

        UserAccountDirectory uad = business.getUserAccountDirectory();
        UserAccount ua = uad.authenticateUser(username, password); 
        if (ua == null) {
            JOptionPane.showMessageDialog(this, "Invalid credentials! Try again ⚠️");
            return;
        }

        // --- Successful login ---
        rightPanel.removeAll();
        JPanel nextPanel;

        if (ua.getAssociatedPersonProfile() instanceof EmployeeProfile) {
            nextPanel = new AdminRoleWorkAreaJPanel(business, rightPanel);
        } else if (ua.getAssociatedPersonProfile() instanceof StudentProfile) {
            nextPanel = new StudentWorkAreaJPanel(
                business,
                (StudentProfile) ua.getAssociatedPersonProfile(),
                rightPanel
            );
        } else {
            nextPanel = new FacultyWorkAreaJPanel(business, rightPanel);
        }

        rightPanel.add(nextPanel, BorderLayout.CENTER);
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
