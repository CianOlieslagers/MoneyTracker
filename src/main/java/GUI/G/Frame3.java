package GUI.G;
import javax.swing.*;
import java.awt.*;


class Frame3 extends BaseFrame {
    public Frame3(JFrame mainFrame) {
        super("Frame 3", mainFrame);

        JLabel label = new JLabel("This is Frame 3");
        add(label, BorderLayout.CENTER);
    }
}