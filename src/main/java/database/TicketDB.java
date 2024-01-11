package database;

import observers.PrintUpdated;
import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TicketDB extends DatabaseTickets {

    private final HashMap<Integer,Ticket> db;
    private static final TicketDB ticketDB = new TicketDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int ticketCount = 0;
    private DatabasePersons dbPersons = PersonDB.getInstance();


    private TicketDB()
    {
        this.db = new HashMap<>();
        this.addObserver(new PrintUpdated());
    }


    public static DatabaseTickets getInstance() {
        return ticketDB;
    }

    @Override
    public void addTicket(Ticket ticket) throws Exception
    {
        boolean isOK = true;
        double totalAmount = 0;

        if (ticket.getAmount() <= 0.0)
        {
            isOK = false;
            throw new Exception("The total amount is equal or smaller than 0");

        }

        for (Map.Entry<Person,Double> amountPerPerson : ticket.getAmountPerPerson().entrySet())
        {
            if (amountPerPerson.getValue() <= 0.0)
            {
                isOK = false;
                throw new Exception("The amount per person is equal or smaller than 0");
            }
            else
            {
                totalAmount += amountPerPerson.getValue();
            }
        }

        if (totalAmount != ticket.getAmount())
        {
            isOK = false;
            throw new Exception("Sum of amount per person is not equal to the total amount!");
        }



        if (isOK)
        {
            dbPersons = PersonDB.getInstance();
            if (dbPersons.getNames().contains(ticket.getPayer()))    // is met een ComboBox dus moet normaal altijd true zijn
            {
                support.firePropertyChange("TicketDB add", null, ticket);
                this.db.put(ticketCount,ticket);
                ticketCount++;
            }
            else
            {
                throw new Exception("Database doesn't contain the name of the payer");
            }
        }
    }

    @Override
    public void removeTicket(Ticket ticket)
    {
        for (Map.Entry<Integer,Ticket> entry : this.db.entrySet())
        {
            if (ticket == entry.getValue())
            {
                this.db.remove(entry.getKey());
                support.firePropertyChange("TicketDB remove", null, ticket);
                break;
            }
        }
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
        for (Map.Entry<Integer, Ticket> e : this.db.entrySet())
        {
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
                if (Objects.equals(payer, tijdelijkPersoon.getName())) {
                    finalPerson = tijdelijkPersoon;
                }
            }


            if (ticket.getSplitEvenly()) {
                Integer aantal = WaardePP.size();
                Double bedrag = ticket.getAmount();
                if (RekeningPP.containsKey(payer)) {
                    if (Objects.equals(payer, finalPerson.getName())) {
                        double Oldvalue = RekeningPP.get(payer);
                        double Newvalue = Oldvalue + (bedrag / aantal);
                        RekeningPP.put(finalPerson, Newvalue);
                    } else {
                        double Oldvalue = RekeningPP.get(payer);
                        double Newvalue = Oldvalue - (bedrag / aantal);
                        RekeningPP.put(finalPerson, Newvalue);
                    }
                } else {
                    if (Objects.equals(payer, finalPerson.getName())) {
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

                        if (Objects.equals(payer, persoon.getName())) {
                            double Oldvalue = RekeningPP.get(finalPerson);
                            double Newvalue = (Oldvalue + (ticket.getAmount() - bedrag));
                            RekeningPP.put(persoon, Newvalue);
                        } else {
                            double Oldvalue = RekeningPP.get(persoon);
                            double Newvalue = Oldvalue - bedrag;
                            RekeningPP.put(persoon, Newvalue);
                        }
                    } else {
                        if (Objects.equals(payer, persoon.getName())) {
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

    @Override
    public HashMap<Person,Double> getBill()
    {
        HashMap<Person,Double> bill = new HashMap<>();

        for (Map.Entry<Integer,Ticket> e : this.db.entrySet())
        {
            Ticket e_ticket = e.getValue();

            boolean alreadyAdded = false;

            for (Map.Entry<Person,Double> f : e_ticket.getAmountPerPerson().entrySet())
            {
                Person f_person = f.getKey();

                if (Objects.equals(f_person.getName(), e_ticket.getPayer()))
                {
                    double f_amountToReceive = e.getValue().getAmount() - f.getValue();

                    if (bill.containsKey(f_person))
                    {
                        double alreadyAmount = bill.get(f_person);
                        bill.put(f_person, (alreadyAmount + f_amountToReceive));
                    }
                    else
                    {
                        bill.put(f_person, f_amountToReceive);
                    }
                }
                else
                {
                    double f_amountToPay = -f.getValue();

                    if (bill.containsKey(f_person))
                    {
                        double alreadyAmount = bill.get(f_person);
                        bill.put(f_person, (alreadyAmount + f_amountToPay));
                    }
                    else
                    {
                        bill.put(f_person, f_amountToPay);
                    }
                }


                if (!e_ticket.getAmountPerPerson().containsKey(dbPersons.getPerson(e_ticket.getPayer())) && !alreadyAdded)
                {

                    alreadyAdded = true;
                    if (bill.containsKey(dbPersons.getPerson(e_ticket.getPayer())))
                    {
                        double amountAlready = bill.get(dbPersons.getPerson(e_ticket.getPayer()));
                        bill.put(dbPersons.getPerson(e_ticket.getPayer()), amountAlready + e_ticket.getAmount());
                    }
                    else
                    {
                        bill.put(dbPersons.getPerson(e_ticket.getPayer()), e_ticket.getAmount());
                    }
                }

            }
        }

        System.out.println("BILL: " + bill);

        return bill;
    }

    @Override
    public ArrayList<String> getBillPerPerson(Person person)
    {
        HashMap<Person,Double> bill = getBill();

        HashMap<Person,Double> negative = new HashMap<>();
        HashMap<Person,Double> positive = new HashMap<>();

        ArrayList<String > result = new ArrayList<>();

        for (Map.Entry<Person,Double> e : bill.entrySet())
        {
            if (e.getValue() > 0)
                positive.put(e.getKey(),e.getValue());
            else if (e.getValue() < 0)
                negative.put(e.getKey(),e.getValue());
        }


        for (Map.Entry<Person,Double> e : negative.entrySet())
        {
            Person debtor = e.getKey();
            double debtAmount = e.getValue();

            for (Map.Entry<Person,Double> f : positive.entrySet())
            {

                Person creditor = f.getKey();
                double creditAmount = f.getValue();

                double settlementAmount = Math.min(-debtAmount, creditAmount);
                positive.put(creditor, positive.get(creditor) - settlementAmount);
                negative.put(debtor, negative.get(debtor) + settlementAmount);

                if (Objects.equals(debtor, person) && settlementAmount!=0)
                {
                    result.add(debtor.getName() + " needs to pay " + settlementAmount + " euro to " + creditor.getName());
                }

                if (Objects.equals(creditor, person) && settlementAmount!=0)
                {
                    result.add(creditor.getName() + " needs to receive " + settlementAmount + " euro from " + debtor.getName());
                }

                debtAmount += settlementAmount;
                if (debtAmount == 0)
                    break;
            }
        }

        if (result.isEmpty())
            result.add(person.getName() + " doesn't need to pay/receive any money!");

        return result;
    }

}