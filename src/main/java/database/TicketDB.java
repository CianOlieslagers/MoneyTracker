package database;

import observers.PrintUpdated;
import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        if (dbPersons.getNames().contains(ticket.getPayer()))
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

    @Override
    public HashMap<String, Double> KostPP(String user) {

        HashMap<String,Double> RekeningPP = new HashMap<>();

        for(Map.Entry<Integer, Ticket> e: this.db.entrySet()){
            Ticket ticket = e.getValue();
            String payer = ticket.getPayer(); // Persoon die betaalt
            HashMap<Double, Person> WaardePP = ticket.getAmountPerPerson();

            if (ticket.getSplitEvenly()){
                Integer aantal = WaardePP.size();
                Double bedrag = ticket.getAmount();
                if (RekeningPP.containsKey(payer)){
                    double Oldvalue = RekeningPP.get(payer);
                    double Newvalue = Oldvalue + (bedrag/aantal);
                    RekeningPP.put(payer,Newvalue);
                }
                else{
                    RekeningPP.put(payer, bedrag/aantal);

                }
            }
            else{
                for(Map.Entry<Double,Person> e2: WaardePP.entrySet()){
                    Person persoon= e2.getValue();
                    if(Objects.equals(persoon.getName(), user)){

                        Double bedrag = e2.getKey();
                        if(RekeningPP.containsKey(payer)){
                            double Oldvalue = RekeningPP.get(payer);
                            double Newvalue = bedrag + Oldvalue;
                            RekeningPP.put(payer,Newvalue);

                        }
                        else{
                            RekeningPP.put(payer,bedrag);
                        }

                    }

                }

            }


        }

        return RekeningPP;
    }

}
