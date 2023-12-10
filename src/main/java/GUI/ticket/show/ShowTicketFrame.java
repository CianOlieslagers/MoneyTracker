package GUI.ticket.show;

import GUI.homescreen.panels.HomePanel;
import GUI.ticket.show.panels.ShowTicketPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class ShowTicketFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    DatabaseTickets ticketDB = TicketDB.getInstance();

    ShowTicketPanel buttons;

    public ShowTicketFrame()
    {
        super("SHOW TICKET SCREEN");
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new ShowTicketPanel(personController, ticketController);

        ticketDB.addObserver(buttons);

        this.add(buttons);
        this.setVisible(true);
    }
}
