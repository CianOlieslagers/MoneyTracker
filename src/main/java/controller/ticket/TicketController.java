package controller.ticket;

import database.DatabaseTickets;
import ticket.Ticket;
import database.TicketDB;

public class TicketController implements Controller
{
    private DatabaseTickets db;

    public TicketController(DatabaseTickets db)
    {
        this.db = db;
    }

    @Override
    public void addTicket(Ticket ticket)
    {
        db.addTicket(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        db.removeTicket(ticket);
    }
}
