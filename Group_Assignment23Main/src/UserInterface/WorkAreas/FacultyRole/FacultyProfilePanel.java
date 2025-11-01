/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.Person.Person;
import Business.Profiles.FacultyProfile;
import javax.swing.*;
import java.awt.*;
 /*
 * @author prekshapraveen
 */
public class FacultyProfilePanel extends JPanel {
    private FacultyProfile facultyProfile;
    private JTextField txtName, txtEmail, txtPhone, txtDepartment;
    private JButton btnUpdate, btnSave;
    private JPanel contentPanel;

    public FacultyProfilePanel(FacultyProfile facultyProfile) {
        this.facultyProfile = facultyProfile;
        initComponents();
        populateProfileData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblHeader = new JLabel("My Profile", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 70, 140));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Center form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblName = new JLabel("Full Name:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Phone:");
        JLabel lblDept = new JLabel("Department:");

        txtName = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhone = new JTextField(20);
        txtDepartment = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(lblName, gbc);
        gbc.gridx = 1; formPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy++; formPanel.add(lblEmail, gbc);
        gbc.gridx = 1; formPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy++; formPanel.add(lblPhone, gbc);
        gbc.gridx = 1; formPanel.add(txtPhone, gbc);

        gbc.gridx = 0; gbc.gridy++; formPanel.add(lblDept, gbc);
        gbc.gridx = 1; formPanel.add(txtDepartment, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnUpdate = new JButton("Edit");
        btnSave = new JButton("Save Changes");

        styleButton(btnUpdate);
        styleButton(btnSave);
        btnSave.setEnabled(false);

        btnPanel.add(btnUpdate);
        btnPanel.add(btnSave);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // Actions
        btnUpdate.addActionListener(e -> enableEditing(true));
        btnSave.addActionListener(e -> saveProfile());
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(new Color(20, 20, 20)); // deep gray-black
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 80, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
    }

    private void populateProfileData() {
        if (facultyProfile == null) return;
        Person p = facultyProfile.getPerson();
        txtName.setText(p.getName());
        txtEmail.setText(p.getEmail());
        txtPhone.setText(p.getPhone());
        txtDepartment.setText(p.getDepartment());

        enableEditing(false);
    }

    private void enableEditing(boolean enable) {
        txtName.setEditable(enable);
        txtEmail.setEditable(enable);
        txtPhone.setEditable(enable);
        txtDepartment.setEditable(enable);
        btnSave.setEnabled(enable);
        btnUpdate.setEnabled(!enable);
    }

    private void saveProfile() {
        Person p = facultyProfile.getPerson();
        p.setName(txtName.getText().trim());
        p.setEmail(txtEmail.getText().trim());
        p.setPhone(txtPhone.getText().trim());
        p.setDepartment(txtDepartment.getText().trim());

        JOptionPane.showMessageDialog(this, "Profile updated successfully!");
        enableEditing(false);
    }
}
