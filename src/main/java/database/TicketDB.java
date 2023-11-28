package database;

import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketDB extends DatabaseTickets
{

    private final ArrayList<Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketDB() // Stond private origineel maar maakte problemen met Singleton toevoeging dus naar een public omgezet
    {
        this.db = new ArrayList<>();
    }

    public static DatabaseTickets getInstance()
    {
        return ticketDB;
    }

    @Override
    public void addTicket(Ticket ticket)
    {
        Ticket oldValue;
        if (db.isEmpty())
            oldValue = null;
        else
            oldValue = db.get(db.size() - 1);
        support.firePropertyChange("TicketDB", oldValue, ticket);
        db.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        Ticket oldValue;
        if (db.isEmpty())
            oldValue = null;
        else
            oldValue = db.get(db.size() - 1);
        support.firePropertyChange("TicketDB", oldValue, ticket);
        db.remove(ticket);
    }

    @Override
    public void addObserver(PropertyChangeListener pcl)
    {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public void removeObserver(PropertyChangeListener pcl)
    {
        support.removePropertyChangeListener(pcl);
    }
}
