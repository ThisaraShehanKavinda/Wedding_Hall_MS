/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package abcweddinghallbooking_ms.View;

import abcweddinghallbooking_ms.DBConnection;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sanduni punchihewa
 */
public class Gallery extends javax.swing.JFrame {

    /**
     * Creates new form Gallery
     */
    
    
    private final JPanel galleryPanel;
    public Gallery() {
        initComponents();
        
        
         // Set the frame title
        setTitle("Gallery View");

        // Get the button component from the GUI builder
        JButton displayButton = getDisplayButton();
        displayButton.addActionListener((ActionEvent e) -> {
            displayHalls();
        });

        // Create a panel to hold the gallery view
        galleryPanel = new JPanel();
        galleryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        galleryPanel.setPreferredSize(new Dimension(900, 500));

        // Create the main content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(displayButton, BorderLayout.NORTH);
        container.add(galleryPanel, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private JButton getDisplayButton() {
        // Replace this code with the code generated by your GUI builder to get the button component
        JButton button = new JButton("Display Halls");
        return button;
    }
    
    
    
     private void displayHalls() {
        // Clear the gallery panel before displaying the halls
        galleryPanel.removeAll();

        // Fetch the halls from the database
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT hall_id, hall_name, photo FROM hall");

            // Create a JLabel for each hall and add it to the gallery panel
            while (resultSet.next()) {
                int hallId = resultSet.getInt("hall_id");
                String name = resultSet.getString("hall_name");
                byte[] photo = resultSet.getBytes("photo");

                ImageIcon imageIcon = new ImageIcon(photo);
                Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(image));
                label.setText("<html><b>Hall ID:</b> " + hallId + "<br><b>Name:</b> " + name + "</html>");

                galleryPanel.add(label);
            }

           
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Refresh the gallery panel to update the displayed halls
        galleryPanel.revalidate();
        galleryPanel.repaint();
    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1256, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gallery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gallery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gallery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gallery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Gallery().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}