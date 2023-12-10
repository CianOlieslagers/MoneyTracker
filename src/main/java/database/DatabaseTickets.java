package database;

import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class DatabaseTickets
{

    protected DatabaseTickets()
    {

    }


    public abstract void addTicket(Ticket ticket);
    public abstract void removeTicket(Ticket ticket);
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);
    public abstract ArrayList<Ticket> getTickets();
    public abstract Double totaalSum();

    public abstract HashMap<String,Double> KostPP(String user);


}
