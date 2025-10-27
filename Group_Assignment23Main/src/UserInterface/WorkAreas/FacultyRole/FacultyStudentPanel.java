/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

/**
 *
 * @author prekshapraveen
 */
import Business.Business;
import javax.swing.*;
import java.awt.*;

public class FacultyStudentPanel extends JPanel {
    public FacultyStudentPanel(Business business) {
        setLayout(new BorderLayout());
        add(new JLabel("🎓 Student Management: Enrolled Students & Progress Reports", SwingConstants.CENTER),
            BorderLayout.CENTER);
    }
}

