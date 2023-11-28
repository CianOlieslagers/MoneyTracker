package observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrintUpdated implements PropertyChangeListener
{
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("PersonDB add"))
        {
            System.out.println("Database Persons updated: " + evt.getNewValue() + " added!");
        }
        if (evt.getPropertyName().equals("PersonDB remove"))
        {
            System.out.println("Database Persons updated: " + evt.getNewValue() + " removed!");
        }
        if (evt.getPropertyName().equals("TicketDB add"))
        {
            System.out.println("Database Tickets updated: " + evt.getNewValue() + " added!");
        }
        if (evt.getPropertyName().equals("TicketDB remove"))
        {
            System.out.println("Database Tickets updated: " + evt.getNewValue() + " removed!");
        }
    }
}
