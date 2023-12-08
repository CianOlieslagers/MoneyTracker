package GUI.ticket.add;

import GUI.ticket.add.panels.AddTicketPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import java.awt.*;

public class AddTicketFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    DatabaseTickets ticketDB = TicketDB.getInstance();

    AddTicketPanel buttons;


    public AddTicketFrame()
    {
        super("ADD TICKET SCREEN");
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new AddTicketPanel(personController, ticketController);

        ticketDB.addObserver(buttons);

        this.add(buttons);
        this.setVisible(true);
    }

    public void closeFrame()
    {
        this.setVisible(false);
    }
}
