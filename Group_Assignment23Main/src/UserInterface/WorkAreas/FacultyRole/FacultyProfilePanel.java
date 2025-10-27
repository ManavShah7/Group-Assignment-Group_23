/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author prekshapraveen
 */
public class FacultyProfilePanel extends JPanel {
    public FacultyProfilePanel(Business business) {
        setLayout(new BorderLayout());
        add(new JLabel("👤 Profile Management: View & Update Personal Info", SwingConstants.CENTER),
            BorderLayout.CENTER);
    }
}
