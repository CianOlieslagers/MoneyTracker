package GUI.G;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Menu Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JButton frame1Button = new JButton("Open Frame 1");
        JButton frame2Button = new JButton("Open Frame 2");
        JButton frame3Button = new JButton("Open Frame 3");

        Dimension buttonSize = new Dimension(150, 30);
        frame1Button.setPreferredSize(buttonSize);
        frame2Button.setPreferredSize(buttonSize);
        frame3Button.setPreferredSize(buttonSize);

        frame1Button.addActionListener(e -> openFrame(new Frame1(this)));
        frame2Button.addActionListener(e -> openFrame(new Frame2(this)));
        frame3Button.addActionListener(e -> openFrame(new Frame3(this)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Use BoxLayout

        panel.add(Box.createHorizontalGlue()); // Add vertical glue to center the buttons
        panel.add(frame1Button);
        panel.add(frame2Button);
        panel.add(frame3Button);
        panel.add(Box.createHorizontalGlue());

        add(panel);

        setLocationRelativeTo(null); // Center the frame
    }

    private void openFrame(JFrame frame) {
        this.setVisible(false);
        frame.setVisible(true);
    }
}
