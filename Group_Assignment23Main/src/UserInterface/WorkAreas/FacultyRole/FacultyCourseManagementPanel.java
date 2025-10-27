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

public class FacultyCourseManagementPanel extends JPanel {
    public FacultyCourseManagementPanel(Business business) {
        setLayout(new BorderLayout());
        add(new JLabel("📘 Course Management: View, Edit, Upload Syllabus, Manage Enrollment", SwingConstants.CENTER),
            BorderLayout.CENTER);
    }
}
