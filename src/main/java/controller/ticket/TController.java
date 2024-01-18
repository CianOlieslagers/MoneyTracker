package controller.ticket;

import database.DatabaseTickets;
import database.PersonDB;
import decorator.TicketDecorator;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TController
{
    void addTicket(Ticket ticket) throws Exception;
    void removeTicket(Ticket ticket);
    ArrayList<Ticket> getTickets();
    double totaalSum();
    HashMap<Person, Double> KostPP(Person person);

    List<String> printSchulden(HashMap<Person,Double> schuldenmap);

    void setActivity(Ticket oldTicket, TicketDecorator decorator) throws Exception;

    HashMap<Person,Double> getBill();

    ArrayList<String> getBillPerPerson(Person person);
}
