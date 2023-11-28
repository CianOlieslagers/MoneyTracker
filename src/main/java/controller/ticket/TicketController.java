package controller.ticket;

import database.DatabaseTickets;
import observers.PrintUpdated;
import ticket.Ticket;

public class TicketController implements TController
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
