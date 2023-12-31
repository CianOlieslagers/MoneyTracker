package GUI.ticket.change;

import GUI.ticket.change.panels.ChangeTicketPanel;
import GUI.ticket.show.panels.ShowTicketPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import ticket.Ticket;

import javax.swing.*;
import java.awt.*;

public class ChangeTicketFrame extends JFrame
{

    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());
    DatabaseTickets ticketDB = TicketDB.getInstance();
    ChangeTicketPanel buttons;
    Ticket ticket;

    public ChangeTicketFrame(Ticket ticket)
    {
        super("CHANGE TICKET SCREEN");
        this.ticket = ticket;
    }

    public void initialize()
    {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new ChangeTicketPanel(personController, ticketController, ticket,this);


        this.add(buttons);
        this.setVisible(true);
    }


}
