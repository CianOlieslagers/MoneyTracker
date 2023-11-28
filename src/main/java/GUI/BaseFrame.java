package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BaseFrame extends JFrame {
    public BaseFrame(String title, JFrame mainFrame) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        setLayout(new BorderLayout());

        JButton returnButton = new JButton("Return to Main");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        add(returnButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the frame
    }
}