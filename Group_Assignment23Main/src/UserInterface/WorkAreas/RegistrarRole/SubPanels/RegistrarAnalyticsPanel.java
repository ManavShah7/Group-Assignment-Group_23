package UserInterface.WorkAreas.RegistrarRole.SubPanels;

import Business.Business;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistrarAnalyticsPanel extends JPanel {

    private final Business business;

    public RegistrarAnalyticsPanel(Business business) {
        this.business = business;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        JLabel title = new JLabel("📊 Institutional Analytics", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        JTable tblEnroll = new JTable(new DefaultTableModel(new Object[]{"Department", "Course", "Enrollment"}, 0));
        JTable tblGPA = new JTable(new DefaultTableModel(new Object[]{"Program", "Average GPA"}, 0));

        tabbedPane.addTab("Enrollment by Course", new JScrollPane(tblEnroll));
        tabbedPane.addTab("GPA Distribution", new JScrollPane(tblGPA));

        add(tabbedPane, BorderLayout.CENTER);
    }
}
