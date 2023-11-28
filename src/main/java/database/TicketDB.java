package database;

import observers.PrintUpdated;
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
        this.addObserver(new PrintUpdated());
    }

    public static DatabaseTickets getInstance()
    {
        return ticketDB;
    }

    @Override
    public void addTicket(Ticket ticket)
    {

        support.firePropertyChange("TicketDB", null, ticket);
        db.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket)
    {

        support.firePropertyChange("TicketDB", null, ticket);
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
