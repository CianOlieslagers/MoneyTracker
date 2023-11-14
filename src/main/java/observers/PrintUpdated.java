package observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrintUpdated implements PropertyChangeListener
{
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("PersonDB"))
        {
            System.out.println("Database Persons updated!");
        }
        if (evt.getPropertyName().equals("TicketDB"))
        {
            System.out.println("Database Tickets updated!");
        }
    }
}
