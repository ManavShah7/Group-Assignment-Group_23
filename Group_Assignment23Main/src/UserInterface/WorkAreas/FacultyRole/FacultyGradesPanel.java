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
public class FacultyGradesPanel extends JPanel{
    public FacultyGradesPanel(Business business) {
        setLayout(new BorderLayout());
        add(new JLabel("🧮 Grades: Grade Assignments, Compute GPA, Rank Students", SwingConstants.CENTER),
            BorderLayout.CENTER);
    }
}
