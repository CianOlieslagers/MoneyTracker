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
    private DatabasePersons dbPersons;


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
            if (this.dbPersons.getNames().contains(ticket.getPayer()))    // is met een ComboBox dus moet normaal altijd true zijn
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
                    result.add(debtor.getName() + " needs to pay " + settlementAmount + " euro to " + creditor.getName() + " on this account number: " + creditor.getAccountNumber());
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