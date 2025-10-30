package UserInterface.WorkAreas.RegistrarRole.SubPanels;

import Business.Business;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

/**
 * Tuition & Financial Reconciliation Panel
 * Displays tuition data and calculates totals dynamically.
 * @author Manav
 */
public class TuitionFinancePanel extends JPanel {

    private final Business business;
    private JTable tblFinance;
    private DefaultTableModel model;
    private JLabel lblTotalCollected, lblUnpaid, lblDeptRevenue;
    private JButton btnRefresh;

    public TuitionFinancePanel(Business business) {
        this.business = business;
        initComponents();
        loadMockData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        // Title
        JLabel title = new JLabel("Tuition & Financial Reconciliation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[]{"Student", "Department", "Amount Paid", "Status"}, 0);
        tblFinance = new JTable(model);
        tblFinance.setRowHeight(25);
        tblFinance.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblFinance.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(new JScrollPane(tblFinance), BorderLayout.CENTER);

        // Footer panel
        JPanel footer = new JPanel(new GridLayout(4, 1));
        footer.setBackground(new Color(245, 247, 255));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblTotalCollected = new JLabel("Total Collected: $0");
        lblUnpaid = new JLabel("Unpaid Tuition: $0");
        lblDeptRevenue = new JLabel("Department Revenue Breakdown: N/A");

        lblTotalCollected.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUnpaid.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDeptRevenue.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnRefresh = makeButton("Refresh Report");
        footer.add(lblTotalCollected);
        footer.add(lblUnpaid);
        footer.add(lblDeptRevenue);
        footer.add(btnRefresh);
        add(footer, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> loadMockData());
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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

    private void loadMockData() {
        model.setRowCount(0); // Clear table first

        // Mock data simulating tuition payments
        Object[][] data = {
            {"Aarav Shah", "Information Systems", 2000, "Paid"},
            {"Priya Patel", "Information Systems", 0, "Unpaid"},
            {"Rohan Mehta", "Computer Science", 1500, "Paid"},
            {"Sneha Iyer", "Data Analytics", 0, "Unpaid"},
            {"Karan Gupta", "Computer Science", 2500, "Paid"}
        };

        for (Object[] row : data) {
            model.addRow(row);
        }

        calculateFinancials();
    }

    private void calculateFinancials() {
        double totalCollected = 0;
        double unpaid = 0;
        HashMap<String, Double> deptRevenue = new HashMap<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            String dept = (String) model.getValueAt(i, 1);
            double amount = Double.parseDouble(model.getValueAt(i, 2).toString());
            String status = (String) model.getValueAt(i, 3);

            if (status.equalsIgnoreCase("Paid")) {
                totalCollected += amount;
                deptRevenue.put(dept, deptRevenue.getOrDefault(dept, 0.0) + amount);
            } else {
                unpaid += 2000; // Assume tuition = 2000 for unpaid students
            }
        }

        lblTotalCollected.setText("Total Collected: $" + totalCollected);
        lblUnpaid.setText("Unpaid Tuition: $" + unpaid);

        if (deptRevenue.isEmpty()) {
            lblDeptRevenue.setText("Department Revenue Breakdown: N/A");
        } else {
            StringBuilder sb = new StringBuilder("Department Revenue Breakdown: ");
            deptRevenue.forEach((dept, amt) -> sb.append(dept).append(" - $").append(amt).append(" | "));
            lblDeptRevenue.setText(sb.toString());
        }
    }
}
