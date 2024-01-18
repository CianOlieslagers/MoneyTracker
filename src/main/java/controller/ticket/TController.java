package controller.ticket;

import decorator.TicketDecorator;
import person.Person;
import ticket.Ticket;

import java.util.ArrayList;

public interface TController
{
    void addTicket(Ticket ticket) throws Exception;
    void removeTicket(Ticket ticket);
    ArrayList<Ticket> getTickets();
    void setActivity(Ticket oldTicket, TicketDecorator decorator) throws Exception;
    ArrayList<String> getBillPerPerson(Person person);
}