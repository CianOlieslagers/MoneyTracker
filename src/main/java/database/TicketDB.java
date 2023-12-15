package database;

import observers.PrintUpdated;
import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

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
    public void addTicket(Ticket ticket) throws Exception
    {
        boolean isOK = true;
        double totalAmount = 0;

        if  (ticket.getAmount() <= 0.0)
        {
            isOK = false;
            //System.out.println("TicketDB: Activity name is empty or amount is equal or smaller than 0.0 ");
            throw new Exception("The total amount is equal or smaller than 0");

        }

        for (Map.Entry<Person, Double> amountPerPerson : ticket.getAmountPerPerson().entrySet())
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
            if (dbPersons.getNames().contains(ticket.getPayer()))    // is met een ComboBox dus moet normaal altijd true zijn
            {
                support.firePropertyChange("TicketDB add", null, ticket);
                this.db.put(ticketCount, ticket);
                ticketCount++;
            }
            else
            {
                //System.out.println("Database doesn't contain this name");
                throw new Exception("Database doesn't contain this name");
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

    @Override
    public HashMap<String, Double> KostPP(String user) {

        HashMap<String,Double> RekeningPP = new HashMap<>();

        for(Map.Entry<Integer, Ticket> e: this.db.entrySet()){
            Ticket ticket = e.getValue();
            String payer = ticket.getPayer(); // Persoon die betaalt
            HashMap<Person, Double> WaardePP = ticket.getAmountPerPerson();

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
                for(Map.Entry<Person, Double> e2: WaardePP.entrySet()){

                    Person persoon= e2.getKey();
                    if(Objects.equals(persoon.getName(), user)){

                        Double bedrag = e2.getValue();
                        if(RekeningPP.containsKey(payer)){
                            double Oldvalue = RekeningPP.get(payer);
                            double Newvalue = bedrag + Oldvalue;
                            RekeningPP.put(payer,Newvalue);

                        }
                        else
                        {
                            RekeningPP.put(payer,bedrag);
                        }
                    }
                }
            }
        }

        return RekeningPP;
    }


    @Override
    public HashMap<Person,Double> getBill()
    {
        HashMap<Person,Double> bill = new HashMap<>();
        //System.out.println("meegegeven person: " + person);

        for (Map.Entry<Integer,Ticket> e : this.db.entrySet())
        {
            Ticket e_ticket = e.getValue();
            //System.out.println("\nprint ticket: " + e_ticket);

            for (Map.Entry<Person,Double> f : e_ticket.getAmountPerPerson().entrySet())
            {
                Person f_person = f.getKey();
                //System.out.println(f_person);

                if (Objects.equals(f_person.getName(), e_ticket.getPayer()))
                {
                    double f_amountToReceive = e.getValue().getAmount() - f.getValue();
                    //System.out.println("i'm the payer " + f + "and need to receive " + f_amountToReceive) ;

                    if (bill.containsKey(f_person))
                    {
                        //System.out.println("bill contains " + f_person);
                        double alreadyAmount = bill.get(f_person);
                        bill.put(f_person, (alreadyAmount + f_amountToReceive));
                        //System.out.println("BILL: " + bill);
                    }
                    else
                    {
                        //System.out.println("bill doesn't contain " + f_person);
                        bill.put(f_person, f_amountToReceive);
                        //System.out.println("BILL: " + bill);
                    }
                }
                else
                {
                    //System.out.println("not the payer " + f);
                    double f_amountToPay = -f.getValue();

                    if (bill.containsKey(f_person))
                    {
                        double alreadyAmount = bill.get(f_person);
                        bill.put(f_person, (alreadyAmount + f_amountToPay));
                        //System.out.println("BILL: " + bill);
                    }
                    else
                    {
                        bill.put(f_person, f_amountToPay);
                        //System.out.println("BILL: " + bill);
                    }
                }
            }
        }

        System.out.println("BILL: " + bill);

        return bill;
    }


    @Override
    public String getBillPerPerson(Person person)
    {
        HashMap<Person,Double> bill = getBill();

        HashMap<Person,Double> negative = new HashMap<>();
        HashMap<Person,Double> positive = new HashMap<>();

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
                bill.put(debtor, bill.get(debtor) + settlementAmount);
                bill.put(creditor, bill.get(creditor) - settlementAmount);

                //System.out.println(debtor + " needs to pay " + settlementAmount + " to " + creditor);

                if (Objects.equals(debtor, person))
                {
                    return printTransaction(debtor,creditor,settlementAmount);
                }

                debtAmount += settlementAmount;
                if (debtAmount == 0)
                    break;
            }
        }
        return (person.getName() + " doesn't have to pay anything!");
    }

    private String printTransaction(Person debtor, Person creditor, double amount)
    {
        System.out.println(debtor.getName() + " needs to pay " + amount + " euro to " + creditor.getName() + " on the following accountnumber: " + creditor.getAccountNumber());

        return (debtor.getName() + " needs to pay " + amount + " euro to " + creditor.getName() + " on the following accountnumber: " + creditor.getAccountNumber());
    }

}
