package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Faculty Work Area — clean UI version.
 * Consistent style with admin panel.
 * @author jaya
 */
public class FacultyWorkAreaJPanel extends JPanel {

    private Business business;
    private JPanel rightPanel;
    private JLabel titleLabel, welcomeLabel;
    private JButton btnMyProfile, btnManageCourses, btnViewStudents, btnGrades;

    public FacultyWorkAreaJPanel(Business business, JPanel rightPanel) {
        this.business = business;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));

        // Header
        titleLabel = new JLabel("Faculty Dashboard", SwingConstants.CENTER);
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
        btnManageCourses = createButton("Manage Courses", e -> showMessage("Courses"));
        btnViewStudents = createButton("View Students", e -> showMessage("Students"));
        btnGrades = createButton("Manage Grades", e -> showMessage("Grades"));

        gbc.gridx = 0;
        gbc.gridy = 0; menuPanel.add(btnMyProfile, gbc);
        gbc.gridy++; menuPanel.add(btnManageCourses, gbc);
        gbc.gridy++; menuPanel.add(btnViewStudents, gbc);
        gbc.gridy++; menuPanel.add(btnGrades, gbc);

        add(menuPanel, BorderLayout.WEST);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        welcomeLabel = new JLabel("Welcome, Faculty Member", SwingConstants.CENTER);
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
                "Faculty Portal", JOptionPane.INFORMATION_MESSAGE);
    }
}
