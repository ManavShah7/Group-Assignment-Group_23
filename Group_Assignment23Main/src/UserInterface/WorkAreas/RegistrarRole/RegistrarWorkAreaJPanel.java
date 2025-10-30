package UserInterface.WorkAreas.RegistrarRole;

import Business.Business;
import UserInterface.WorkAreas.RegistrarRole.SubPanels.*;
import javax.swing.*;
import java.awt.*;

/**
 * Registrar main dashboard panel — clean version (no emojis).
 * Handles navigation to course, registration, finance, analytics, and profile subpanels.
 * @author Manav
 */
public class RegistrarWorkAreaJPanel extends JPanel {

    private final Business business;
    private final JPanel rightPanel;

    public RegistrarWorkAreaJPanel(Business business, JPanel rightPanel) {
        this.business = business;
        this.rightPanel = rightPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        // Left sidebar
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(18, 25, 39));
        sidePanel.setPreferredSize(new Dimension(220, getHeight()));
        sidePanel.setLayout(new GridLayout(6, 1, 10, 10));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCourseOffer = makeButton("Manage Courses");
        JButton btnStudentReg = makeButton("Student Registration");
        JButton btnFinance = makeButton("Tuition & Finance");
        JButton btnReports = makeButton("Analytics & Reports");
        JButton btnProfile = makeButton("My Profile");

        sidePanel.add(btnCourseOffer);
        sidePanel.add(btnStudentReg);
        sidePanel.add(btnFinance);
        sidePanel.add(btnReports);
        sidePanel.add(btnProfile);

        // Right dashboard
        JLabel title = new JLabel("Registrar Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));

        add(sidePanel, BorderLayout.WEST);
        add(title, BorderLayout.CENTER);

        // Button actions
        btnCourseOffer.addActionListener(e -> switchPanel(new CourseOfferingPanel(business)));
        btnStudentReg.addActionListener(e -> switchPanel(new StudentRegistrationPanel(business)));
        btnFinance.addActionListener(e -> switchPanel(new TuitionFinancePanel(business)));
        btnReports.addActionListener(e -> switchPanel(new RegistrarAnalyticsPanel(business)));
        btnProfile.addActionListener(e -> switchPanel(new RegistrarProfilePanel(business)));
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 102, 204));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 130, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
        return btn;
    }

    private void switchPanel(JPanel panel) {
        rightPanel.removeAll();
        rightPanel.add(panel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}
