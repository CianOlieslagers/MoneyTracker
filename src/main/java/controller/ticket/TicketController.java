package controller.ticket;

import Ticket.Ticket;
import database.TicketDB;

public class TicketController implements Controller
{
    private TicketDB db;

    public TicketController(TicketDB db)
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
