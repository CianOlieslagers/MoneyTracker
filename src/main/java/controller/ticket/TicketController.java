package controller.ticket;

import database.DatabaseTickets;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;


public class TicketController implements TController
{
    private DatabaseTickets db;

    public TicketController(DatabaseTickets db)
    {
        this.db = db;
    }

    @Override
    public void addTicket(Ticket ticket) throws Exception
    {
        db.addTicket(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        db.removeTicket(ticket);
    }

    @Override
    public ArrayList<Ticket> getTickets()
    {
        return (db.getTickets());
    }

    @Override
    public double totaalSum(DatabaseTickets dbt) {
        return dbt.totaalSum();
    }

    @Override
    public HashMap<String, Double> KostPP(String user, DatabaseTickets dbt){

        return dbt.KostPP(user);
    }

    @Override
    public void setActivity(Ticket oldTicket)
    {
        //DECORATOR

//        Ticket newTicket =
//        db.removeTicket(oldTicket);
//        db.addTicket(newTicket);
    }


}
