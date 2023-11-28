package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Frame1 extends BaseFrame {
    public Frame1(JFrame mainFrame) {
        super("Frame 1", mainFrame);

        JLabel label = new JLabel("This is Frame 1");
        add(label, BorderLayout.CENTER);

        JButton extraButton = new JButton("Extra Button");
        extraButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Extra button clicked!");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(extraButton);
        add(buttonPanel, BorderLayout.NORTH); // Add the extra button to the north (top) of the frame
    }
}
