package controller.ticket;

import database.DatabasePersons;
import database.DatabaseTickets;
import ticket.Ticket;


public class TicketController implements TController
{
    private DatabaseTickets db;

    public TicketController(DatabaseTickets db, DatabasePersons dbPersons)
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

    @Override
    public double totaalSum(DatabaseTickets dbt) {
        return dbt.totaalSum();
    }





}
