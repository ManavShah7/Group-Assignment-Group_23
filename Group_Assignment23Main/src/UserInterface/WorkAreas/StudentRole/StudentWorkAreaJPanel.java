package UserInterface.WorkAreas.StudentRole;

import Business.Business;
import Business.Profiles.StudentProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Student Work Area Panel — shows student info, course and tuition options.
 * Compatible with ProfileWorkAreaMainFrame.
 * @author Manav
 */
public class StudentWorkAreaJPanel extends JPanel {

    private Business business;
    private StudentProfile studentProfile;
    private JPanel rightPanel;
    private JLabel titleLabel, welcomeLabel;
    private JButton btnMyProfile, btnMyCourses, btnGrades, btnSupport;

    public StudentWorkAreaJPanel(Business business, StudentProfile studentProfile, JPanel rightPanel) {
        this.business = business;
        this.studentProfile = studentProfile;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));

        // Header
        titleLabel = new JLabel("Student Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Left menu
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(25, 34, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        btnMyProfile = createButton("My Profile", e -> showMessage("Profile"));
        btnMyCourses = createButton("My Courses", e -> showMessage("Courses"));
        btnGrades = createButton("View Grades", e -> showMessage("Grades"));
        btnSupport = createButton("Support / Help", e -> showMessage("Support"));

        gbc.gridx = 0;
        gbc.gridy = 0; menuPanel.add(btnMyProfile, gbc);
        gbc.gridy++; menuPanel.add(btnMyCourses, gbc);
        gbc.gridy++; menuPanel.add(btnGrades, gbc);
        gbc.gridy++; menuPanel.add(btnSupport, gbc);

        add(menuPanel, BorderLayout.WEST);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        welcomeLabel = new JLabel("Welcome, Student", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, ActionListener listener) {
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

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }
        });

        button.addActionListener(listener);
        return button;
    }

    private void showMessage(String section) {
        JOptionPane.showMessageDialog(this,
                "Opening " + section + " section (coming soon).",
                "Student Portal", JOptionPane.INFORMATION_MESSAGE);
    }
}
