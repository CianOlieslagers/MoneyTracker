package controller.ticket;

import database.DatabaseTickets;
import decorator.*;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.Calendar;
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
    public double totaalSum() {
        return db.totaalSum();
    }

    @Override
    public HashMap<Person, Double> KostPP(Person person){

        return db.KostPP(person);
    }

    @Override
    public void printSchulden(HashMap<Person, Double> schuldenmap) {
        db.printSchulden(schuldenmap);
    }



    @Override
    public void setActivity(Ticket oldTicket, TicketDecorator decorator) throws Exception
    {
        System.out.println("oldticket: " + oldTicket);
        Ticket decoratedTicket = decorator.decorate(oldTicket);
        System.out.println("decoratedticket" + decoratedTicket);
        db.removeTicket(oldTicket);
        db.addTicket(decoratedTicket);
        System.out.println(db.getTickets());
    }


    @Override
    public HashMap<Person,Double> getBill()
    {
        return db.getBill();
    }

    @Override
    public ArrayList<String> getBillPerPerson(Person person)
    {
        return db.getBillPerPerson(person);
    }


}
