package GUI.G;
import javax.swing.*;
import java.awt.*;


class Frame2 extends BaseFrame {
    public Frame2(JFrame mainFrame) {
        super("Frame 2", mainFrame);

        JLabel label = new JLabel("This is Frame 2");
        add(label, BorderLayout.CENTER);
    }
}
