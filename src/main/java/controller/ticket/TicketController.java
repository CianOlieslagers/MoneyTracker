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
    public void setActivity(Ticket oldTicket, Category category) throws Exception {

        Ticket AirplaneTicket = new AirplaneTicketDecorator(oldTicket);
        if(AirplaneTicket.getCategory() == category){
            db.removeTicket(oldTicket);
            db.addTicket(AirplaneTicket);
        }

        Ticket DrinksTicket = new DrinksTicketDecorator(oldTicket);
        if(DrinksTicket.getCategory() == category){
            db.removeTicket(oldTicket);
            db.addTicket(DrinksTicket);
        }

//        Ticket newTicket =
//        db.removeTicket(oldTicket);
//        db.addTicket(newTicket);
    }


}
