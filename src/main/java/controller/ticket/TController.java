package controller.ticket;

import database.DatabaseTickets;
import database.PersonDB;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;

public interface TController
{
    void addTicket(Ticket ticket) throws Exception;
    void removeTicket(Ticket ticket);
    ArrayList<Ticket> getTickets();
    double totaalSum();
    HashMap<Person, Double> KostPP(Person person);

    void printSchulden(HashMap<Person,Double> schuldenmap);

    void setActivity(Ticket oldTicket, Category category) throws Exception;

    HashMap<Person,Double> getBill();

    ArrayList<String> getBillPerPerson(Person person);
}
