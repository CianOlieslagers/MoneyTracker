package controller.ticket;

import database.DatabaseTickets;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;

public interface TController
{
    void addTicket(Ticket ticket);
    void removeTicket(Ticket ticket);
    ArrayList<Ticket> getTickets();
    double totaalSum(DatabaseTickets dbt);

    HashMap<String, Double> KostPP(String user, DatabaseTickets dbt);
}
