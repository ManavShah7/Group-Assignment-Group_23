package UserInterface.WorkAreas.RegistrarRole.SubPanels;

import Business.Business;
import javax.swing.*;
import java.awt.*;

public class RegistrarProfilePanel extends JPanel {

    private final Business business;
    private JTextField txtName, txtEmail, txtPhone, txtOfficeHours;
    private JButton btnSave;

    public RegistrarProfilePanel(Business business) {
        this.business = business;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 255));

        JLabel title = new JLabel("👤 Registrar Profile", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 2, 15, 15));
        txtName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtOfficeHours = new JTextField();

        form.add(new JLabel("Name:"));
        form.add(txtName);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel("Phone:"));
        form.add(txtPhone);
        form.add(new JLabel("Office Hours:"));
        form.add(txtOfficeHours);

        add(form, BorderLayout.CENTER);

        btnSave = new JButton("Save Changes");
        btnSave.setBackground(new Color(0, 102, 204));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSave.setFocusPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnSave, BorderLayout.SOUTH);
    }
}
