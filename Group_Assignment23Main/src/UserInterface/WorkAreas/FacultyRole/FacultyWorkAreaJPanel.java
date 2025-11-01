package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.Course.Course;
import Business.Course.CourseDirectory;
import Business.Profiles.FacultyProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;

/**
 * Faculty Work Area — clean UI version.
 * Consistent style with admin panel.
 * @author prekshapraveen
 */
public class FacultyWorkAreaJPanel extends JPanel {

    private Business business;
    private JPanel rightPanel;
    private JLabel titleLabel;
    private JButton btnMyProfile, btnManageCourses, btnViewStudents, btnGrades,btnTuition;
    private JPanel contentPanel;  // Center area with CardLayout
    private CardLayout cardLayout;

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
        gbc.insets = new Insets(25, 15, 25, 15); // add more vertical space
        gbc.fill = GridBagConstraints.HORIZONTAL;


        btnMyProfile = createButton("My Profile", e -> showPanel("PROFILE"));
        btnManageCourses = createButton("Manage Courses", e -> openManageCoursesPanel());
        btnViewStudents = createButton("View Students", e -> showPanel("STUDENTS"));
        btnGrades = createButton("Manage Grades", e -> openPerformanceReportPanel());
        btnTuition = createButton("Tuition / Enrollment Insight", e -> openTuitionInsightPanel());
        gbc.gridy++; menuPanel.add(btnTuition, gbc);


        gbc.gridx = 0;
        gbc.gridy = 0; menuPanel.add(btnMyProfile, gbc);
        gbc.gridy++; menuPanel.add(btnManageCourses, gbc);
        gbc.gridy++; menuPanel.add(btnViewStudents, gbc);
        gbc.gridy++; menuPanel.add(btnGrades, gbc);
        gbc.gridy++; menuPanel.add(btnTuition, gbc);
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        add(menuPanel, BorderLayout.WEST);

        // Center Panel — Card Layout for different sections
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);

        // Default welcome panel
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, Faculty Member!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Add subpanels
        contentPanel.add(welcomePanel, "WELCOME");
        FacultyProfile facultyProfile = (FacultyProfile) business.getCurrentUser().getAssociatedPersonProfile();
        contentPanel.add(new FacultyProfilePanel(facultyProfile), "PROFILE");
        contentPanel.add(new FacultyStudentPanel(facultyProfile, business.getCourseDirectory()), "STUDENTS");
        contentPanel.add(new FacultyGradesPanel(business), "GRADES");

        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(0, 100, 200), 1),
    BorderFactory.createEmptyBorder(8, 16, 8, 16)
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

    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }
    
    private void openManageCoursesPanel() {
    FacultyProfile facultyProfile = (FacultyProfile) business.getCurrentUser().getAssociatedPersonProfile();

    // Create ManageCoursesJPanel dynamically
    FacultyCourseManagementPanel manageCoursesPanel = new FacultyCourseManagementPanel(contentPanel, facultyProfile, business.getCourseDirectory());
    
    contentPanel.add(manageCoursesPanel, "MANAGE_COURSES");
    cardLayout.show(contentPanel, "MANAGE_COURSES");
}

    private void goBack() {
    CardLayout layout = (CardLayout) rightPanel.getLayout();
    layout.show(rightPanel, "WELCOME");
}
    
    private void openPerformanceReportPanel() {
    FacultyProfile facultyProfile = (FacultyProfile) business.getCurrentUser().getAssociatedPersonProfile();

    FacultyPerformanceReportPanel reportPanel = new FacultyPerformanceReportPanel(
            facultyProfile, business.getCourseDirectory()
    );

    contentPanel.add(reportPanel, "REPORTS");
    cardLayout.show(contentPanel, "REPORTS");
}

    private void openTuitionInsightPanel() {
    FacultyProfile facultyProfile = (FacultyProfile) business.getCurrentUser().getAssociatedPersonProfile();
    FacultyTuitionInsightPanel panel = new FacultyTuitionInsightPanel(facultyProfile, business.getCourseDirectory());
    contentPanel.add(panel, "TUITION");
    cardLayout.show(contentPanel, "TUITION");
}

    
}
