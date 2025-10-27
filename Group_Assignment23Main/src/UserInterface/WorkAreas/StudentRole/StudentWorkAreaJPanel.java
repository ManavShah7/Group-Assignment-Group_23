package UserInterface.WorkAreas.StudentRole;

import Business.Business;
import Business.Profiles.StudentProfile;
import javax.swing.*;
import java.awt.*;

/**
 * Student Work Area Panel — shows student info, course and tuition options.
 * Compatible with ProfileWorkAreaMainFrame.
 * @author Manav
 */
public class StudentWorkAreaJPanel extends JPanel {

    private final Business business;
    private final StudentProfile studentProfile;
    private final JPanel rightPanel;

    private JLabel lblWelcome, lblDept, lblStatus;
    private JButton btnViewCourses, btnPayTuition, btnLogout;

    public StudentWorkAreaJPanel(Business business, StudentProfile studentProfile, JPanel rightPanel) {
        this.business = business;
        this.studentProfile = studentProfile;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 255));

        // --- Header ---
        lblWelcome = new JLabel("Welcome, " + studentProfile.getPerson().getPersonName(), SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblWelcome.setForeground(new Color(0, 70, 140));
        add(lblWelcome, BorderLayout.NORTH);

        // --- Center Info Panel ---
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        infoPanel.setBackground(new Color(250, 250, 255));

        lblDept = new JLabel("Department: " + safeText(studentProfile.getDepartment()), SwingConstants.CENTER);
        lblDept.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        lblStatus = new JLabel("Academic Status: " + safeText(studentProfile.getAcademicStatus()), SwingConstants.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        infoPanel.add(lblDept);
        infoPanel.add(lblStatus);

        add(infoPanel, BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(240, 245, 255));

        btnViewCourses = createStyledButton("View My Courses");
        btnPayTuition = createStyledButton("Pay Tuition");
        btnLogout = createStyledButton("Logout");

        btnLogout.addActionListener(e -> handleLogout());

        btnPanel.add(btnViewCourses);
        btnPanel.add(btnPayTuition);
        btnPanel.add(btnLogout);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }
        });
        return button;
    }

    private void handleLogout() {
        JOptionPane.showMessageDialog(this, "Logged out successfully!");
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private String safeText(String val) {
        return (val == null || val.isEmpty()) ? "N/A" : val;
    }
}
