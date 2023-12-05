package database;

import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.util.Iterator;

public abstract class DatabaseTickets
{

    private static DatabaseTickets instance = null;


    public DatabaseTickets()
    {

    }


    // Toevoeging van de singleton voor beide DatabaseTickets
    public static DatabaseTickets getInstance() { // First we check if the database already exist. If it does we use that database instead.
        if (instance == null) {
            instance = new TicketDB(); // Or any other implementation you prefer
        }
        return instance;
    }


    public abstract void addTicket(Ticket ticket);
    public abstract void removeTicket(Ticket ticket);
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);




    public abstract Double totaalSum();
}
