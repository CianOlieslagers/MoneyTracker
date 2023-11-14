package database;

import Ticket.Ticket;

import java.beans.PropertyChangeListener;

public abstract class DatabaseTickets
{
    public DatabaseTickets()
    {

    }

    public abstract void addTicket(Ticket ticket);

    public abstract void removeTicket(Ticket ticket);

    public abstract void addObserver(PropertyChangeListener pcl);

    public abstract void removeObserver(PropertyChangeListener pcl);
}
