package GUI.calculate;

import GUI.calculate.panels.CalculatePanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import java.awt.*;

public class CalculateFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    CalculatePanel panel;

    public CalculateFrame()
    {
        super("CALCULATE SCREEN");
    }

    public void initialize()
    {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        this.panel = new CalculatePanel(personController, ticketController,this);

        this.add(panel);
        this.setVisible(true);
    }
}
