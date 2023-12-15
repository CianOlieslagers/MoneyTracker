package controller.ticket;

import database.DatabaseTickets;
import person.Person;
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
    public double totaalSum() {
        return db.totaalSum();
    }

    @Override
    public HashMap<String, Double> KostPP(String user){

        return db.KostPP(user);
    }

    @Override
    public void setActivity(Ticket oldTicket)
    {
        //DECORATOR

//        Ticket newTicket =
//        db.removeTicket(oldTicket);
//        db.addTicket(newTicket);
    }

    public HashMap<Person,Double> getBill()
    {
        return db.getBill();
    }


    public String getBillPerPerson(Person person)
    {
        return db.getBillPerPerson(person);
    }

}
