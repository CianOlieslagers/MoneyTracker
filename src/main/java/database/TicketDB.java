package database;

import observers.PrintUpdated;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

public class TicketDB extends DatabaseTickets
{

    private final HashMap<Integer, Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int ticketCount = 0;


    public TicketDB() // Stond private origineel maar maakte problemen met Singleton toevoeging dus naar een public omgezet
    {
        this.db = new HashMap<>();
        this.addObserver(new PrintUpdated());
    }


    public static DatabaseTickets getInstance()
    {
        return ticketDB;
    }

    @Override
    public void addTicket(Ticket ticket)
    {
        support.firePropertyChange("TicketDB add", null, ticket);
        this.db.put(ticketCount, ticket);
        ticketCount++;

    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        support.firePropertyChange("TicketDB remove", null, ticket);
        this.db.remove(ticketCount, ticket);
        ticketCount--;
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



    @Override
    public Double totaalSum() {
        Double totaal = 0.0;
        for(Map.Entry<Integer, Ticket> e: this.db.entrySet()){
            int e_TicketId = e.getKey();
            Ticket e_Value = e.getValue();
            totaal = e_Value.getAmount() + totaal;
        }
        return totaal;
    }


}
