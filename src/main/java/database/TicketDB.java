package database;

import observers.PrintUpdated;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TicketDB extends DatabaseTickets
{
    private final HashMap<Integer, Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int ticketCount = 0;
    private final DatabasePersons dbPersons = PersonDB.getInstance();


    private TicketDB()
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
        if (ticket.getName().isEmpty() || ticket.getAmount() <= 0.0)
        {
            System.out.println("TicketDB: Activity name is empty or amount is equal or smaller than 0.0 ");
        }
        else
        {
            if (dbPersons.getNames().contains(ticket.getPayer()))    // is met een ComboBox dus moet normaal altijd true zijn
            {
                support.firePropertyChange("TicketDB add", null, ticket);
                this.db.put(ticketCount, ticket);
                ticketCount++;
            }
            else
            {
                System.out.println("Database doesn't contains this name");
            }
        }

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
    public ArrayList<Ticket> getTickets()
    {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> e: this.db.entrySet())
        {
            Ticket e_Value = e.getValue();
            ticketList.add(e_Value);
        }
        return ticketList;
    }


    @Override
    public Double totaalSum() {
        Double totaal = 0.0;
        for(Map.Entry<Integer, Ticket> e: this.db.entrySet()){
            Ticket e_Value = e.getValue();
            totaal = e_Value.getAmount() + totaal;
        }
        return totaal;
    }
}
