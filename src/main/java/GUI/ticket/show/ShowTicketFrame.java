package GUI.ticket.show;

import GUI.homescreen.panels.HomePanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import java.awt.*;

public class ShowTicketFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    HomePanel buttons;

    public ShowTicketFrame()
    {
        super("SHOW TICKET");
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new HomePanel(personController, ticketController);

        this.add(buttons);
        this.setVisible(true);
    }
}
