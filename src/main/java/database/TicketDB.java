package database;

import Person.Person;
import Ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class TicketDB extends DatabaseTickets
{

    private final ArrayList<Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private TicketDB()
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
        //support.firePropertyChange("TicketDB",,);
        db.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        //support.firePropertyChange("TicketDB", ,);
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
