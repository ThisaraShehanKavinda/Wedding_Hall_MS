/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author sanduni punchihewa
 */
public class CustomOptionPane extends JOptionPane {
      @Override
    public JDialog createDialog(Component parentComponent, String title) throws HeadlessException {
        JDialog dialog = super.createDialog(parentComponent, title);
        dialog.getContentPane().setBackground(new Color(0, 128, 0)); // Set the background color to dark green
        dialog.getRootPane().setBorder(new LineBorder(new Color(0, 128, 0), 2)); // Set the border color to dark green
        return dialog;
    }
}
