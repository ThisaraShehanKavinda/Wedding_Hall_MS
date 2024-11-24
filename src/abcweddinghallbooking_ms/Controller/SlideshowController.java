/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author PC
 */




public class SlideshowController {
    
    private JLabel[] labels = new JLabel[5];

    public SlideshowController(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4, JLabel lbl5) {
    labels[0] = lbl1;
    labels[1] = lbl2;
    labels[2] = lbl3;
    labels[3] = lbl4;
    labels[4] = lbl5;
}

    public void startSlideshow() {
    int delay = 2000; // milliseconds
    Timer timer = new Timer(delay, new ActionListener() {
        int currentIndex = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            labels[currentIndex].setVisible(false);
            currentIndex = (currentIndex + 1) % labels.length;
            labels[currentIndex].setVisible(true);
        }
    });
    timer.start();
}

    
}
