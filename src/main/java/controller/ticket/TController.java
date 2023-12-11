package controller.ticket;

import database.DatabaseTickets;
import ticket.Ticket;

import java.util.ArrayList;

public interface TController
{
    void addTicket(Ticket ticket);
    void removeTicket(Ticket ticket);
    ArrayList<Ticket> getTickets();
    double totaalSum(DatabaseTickets dbt);
}
