package database;

import observers.PrintUpdated;
import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TicketDB extends DatabaseTickets {

    private final HashMap<Integer, Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int ticketCount = 0;
    private final DatabasePersons dbPersons = PersonDB.getInstance();


    private TicketDB() {
        this.db = new HashMap<>();
        this.addObserver(new PrintUpdated());
    }


    public static DatabaseTickets getInstance() {
        return ticketDB;
    }

    @Override
    public void addTicket(Ticket ticket) throws Exception {
        boolean isOK = true;
        double totalAmount = 0;

        if (ticket.getAmount() <= 0.0) {
            isOK = false;
            //System.out.println("TicketDB: Activity name is empty or amount is equal or smaller than 0.0 ");
            throw new Exception("The total amount is equal or smaller than 0");

        }

        for (Map.Entry<Person, Double> amountPerPerson : ticket.getAmountPerPerson().entrySet()) {
            if (amountPerPerson.getValue() <= 0.0) {
                isOK = false;
                throw new Exception("The amount per person is equal or smaller than 0");
            } else {
                totalAmount += amountPerPerson.getValue();
            }
        }

        if (totalAmount != ticket.getAmount()) {
            isOK = false;
            throw new Exception("Sum of amount per person is not equal to the total amount!");
        }


        if (isOK) {
            if (dbPersons.getNames().contains(ticket.getPayer()))    // is met een ComboBox dus moet normaal altijd true zijn
            {
                support.firePropertyChange("TicketDB add", null, ticket);
                this.db.put(ticketCount, ticket);
                ticketCount++;
            } else {
                //System.out.println("Database doesn't contain this name");
                throw new Exception("Database doesn't contain this name");
            }
        }
    }

    @Override
    public void removeTicket(Ticket ticket) {
        support.firePropertyChange("TicketDB remove", null, ticket);
        this.db.remove(ticketCount, ticket);
        ticketCount--;
    }

    @Override
    public void addObserver(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public void removeObserver(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        for (Map.Entry<Integer, Ticket> e : this.db.entrySet()) {
            Ticket e_Value = e.getValue();
            ticketList.add(e_Value);
        }
        return ticketList;
    }

    @Override
    public Double totaalSum() {
        Double totaal = 0.0;
        for (Map.Entry<Integer, Ticket> e : this.db.entrySet()) {
            Ticket e_Value = e.getValue();
            totaal = e_Value.getAmount() + totaal;
        }
        return totaal;
    }

    @Override
    public HashMap<Person, Double> KostPP(Person person) {

        HashMap<Person, Double> RekeningPP = new HashMap<>();


        for (Map.Entry<Integer, Ticket> e : this.db.entrySet()) {
            Ticket ticket = e.getValue();
            String payer = ticket.getPayer();// Persoon die betaalt
            HashMap<Person, Double> WaardePP = ticket.getAmountPerPerson();
            ArrayList<Person> lijstNamenPersonen = dbPersons.getPersons();
            Person finalPerson = null;

            // Zet onze payer string om in een persoon
            for (int i = 0; i < lijstNamenPersonen.size(); i++) {
                Person tijdelijkPersoon = lijstNamenPersonen.get(i);
                if (payer == tijdelijkPersoon.getName()) {
                    finalPerson = tijdelijkPersoon;
                }
            }


            if (ticket.getSplitEvenly()) {
                Integer aantal = WaardePP.size();
                Double bedrag = ticket.getAmount();
                if (RekeningPP.containsKey(payer)) {
                    if (payer == finalPerson.getName()) {
                        double Oldvalue = RekeningPP.get(payer);
                        double Newvalue = Oldvalue + (bedrag / aantal);
                        RekeningPP.put(finalPerson, Newvalue);
                    } else {
                        double Oldvalue = RekeningPP.get(payer);
                        double Newvalue = Oldvalue - (bedrag / aantal);
                        RekeningPP.put(finalPerson, Newvalue);
                    }
                } else {
                    if (payer == finalPerson.getName()) {
                        RekeningPP.put(finalPerson, bedrag / aantal);
                    } else {
                        RekeningPP.put(finalPerson, -(bedrag / aantal));
                    }
                }
            } else {
                for (Map.Entry<Person, Double> e2 : WaardePP.entrySet()) {
                    Person persoon = e2.getKey();
                    Double bedrag = e2.getValue();


                    if (RekeningPP.containsKey(persoon)) { // Checkt of de persoon al in onze eindrekening zit

                        if (payer == persoon.getName()) {
                            double Oldvalue = RekeningPP.get(finalPerson);
                            double Newvalue = (Oldvalue + (ticket.getAmount() - bedrag));
                            RekeningPP.put(persoon, Newvalue);
                        } else {
                            double Oldvalue = RekeningPP.get(persoon);
                            double Newvalue = Oldvalue - bedrag;
                            RekeningPP.put(persoon, Newvalue);
                        }
                    } else {
                        if (payer == persoon.getName()) {
                            double bedrag2 = ticket.getAmount() - bedrag;
                            RekeningPP.put(persoon, bedrag2);
                        } else {
                            RekeningPP.put(persoon, -bedrag);
                        }


                    }

                }

            }
        }

        return RekeningPP;
    }

    @Override
    public void printSchulden(HashMap<Person, Double> schuldenMap) {
        List<Person> credits = new ArrayList<>();
        List<Person> debts = new ArrayList<>();

        // Splitting the debts and credits
        for (Map.Entry<Person, Double> entry : schuldenMap.entrySet()) {
            if (entry.getValue() > 0) {
                credits.add(entry.getKey());
            } else if (entry.getValue() < 0) {
                debts.add(entry.getKey());
            }
        }
        // Sorting in descending order
        credits.sort((p1, p2) -> Double.compare(schuldenMap.get(p2), schuldenMap.get(p1)));
        // Sorting in ascending order
        debts.sort((p1, p2) -> Double.compare(schuldenMap.get(p1), schuldenMap.get(p2)));

        for (Person credit : credits) {
            double creditAmount = schuldenMap.get(credit);
            System.out.println(credit.getName() + " moet nog " + creditAmount + " euro ontvangen aan:");

            for (Person debt : debts) {
                double debtAmount = schuldenMap.get(debt);
                if (debtAmount < 0) {
                    double settleAmount = Math.min(Math.abs(debtAmount), creditAmount);
                    if (settleAmount > 0) {
                        System.out.println("  - " + debt.getName() + ": " + settleAmount + " euro");
                        creditAmount -= settleAmount;
                        schuldenMap.put(debt, debtAmount + settleAmount);
                    }
                }
            }
        }

    }
}
