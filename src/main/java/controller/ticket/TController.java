package controller.ticket;

import database.DatabaseTickets;
import ticket.Ticket;

public interface TController
{
    void addTicket(Ticket ticket);

    void removeTicket(Ticket ticket);

    double totaalSum(DatabaseTickets dbt);

}
