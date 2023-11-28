package observers;

import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrintUpdated implements PropertyChangeListener
{
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("PersonDB add"))
        {
            Person person = (Person) evt.getNewValue();
            System.out.println("Database Persons updated: " + person.getName() + " added!");
        }
        if (evt.getPropertyName().equals("PersonDB remove"))
        {
            Person person = (Person) evt.getNewValue();
            System.out.println("Database Persons updated: " + person.getName() + " removed!");
        }
        if (evt.getPropertyName().equals("TicketDB add"))
        {
            Ticket ticket = (Ticket) evt.getNewValue();
            System.out.println("Database Tickets updated: " + ticket + " added!");
        }
        if (evt.getPropertyName().equals("TicketDB remove"))
        {
            Ticket ticket = (Ticket) evt.getNewValue();
            System.out.println("Database Tickets updated: " + ticket + " removed!");
        }
    }
}
