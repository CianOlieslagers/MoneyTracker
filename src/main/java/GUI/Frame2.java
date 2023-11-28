package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Frame2 extends BaseFrame {
    public Frame2(JFrame mainFrame) {
        super("Frame 2", mainFrame);

        JLabel label = new JLabel("This is Frame 2");
        add(label, BorderLayout.CENTER);
    }
}
