package controller.ticket;

import database.DatabaseTickets;
import decorator.*;
import person.Person;
import ticket.Ticket;

import java.util.ArrayList;


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
    public void setActivity(Ticket oldTicket, TicketDecorator decorator) throws Exception
    {
        db.removeTicket(oldTicket);
        Ticket decoratedTicket = decorator.decorate(oldTicket);
        db.addTicket(decoratedTicket);
    }


    @Override
    public ArrayList<String> getBillPerPerson(Person person)
    {
        return db.getBillPerPerson(person);
    }

}
