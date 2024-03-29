package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import observers.PrintUpdated;
import person.Person;
import ticket.Ticket;


public class PersonDB extends DatabasePersons
{
    private final HashMap<Integer,Person> db;
    private static final PersonDB personDB = new PersonDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int personCount = 0;
    private final DatabaseTickets dbTickets = TicketDB.getInstance();


    private PersonDB()
    {
        this.db = new LinkedHashMap<>();
        this.addObserver(new PrintUpdated());
    }


    public static DatabasePersons getInstance()
    {
        return personDB;
    }


    @Override
    public void addPerson(Person person) throws Exception
    {
        boolean existing = false;

        for(Map.Entry<Integer, Person> entry: db.entrySet())
        {
            Person e_person = entry.getValue();
            if (e_person.getName().equals(person.getName()))
            {
                existing = true;
                throw new Exception(person.getName() + " is already in use!");
            }

            if (e_person.getAccountNumber().equals(person.getAccountNumber()))
            {
                existing = true;
                throw new Exception("Accountnumber: " + person.getAccountNumber() + " is already in use!");
            }
        }

        if (!existing)
        {
            this.db.put(personCount,person);
            support.firePropertyChange("PersonDB add", null, person);
            personCount++;
        }
    }


    @Override
    public void removePerson(Person person) throws Exception
    {
        boolean removable = false;

        for (Map.Entry<Integer,Person> entry: db.entrySet())
        {
            int e_personID = entry.getKey();
            Person e_person = entry.getValue();

            if (e_person.getName().equals(person.getName()) && e_person.getAccountNumber().equals(person.getAccountNumber()))
            {
                for (Ticket e_ticket : dbTickets.getTickets())
                {
                    if (e_ticket.getPayer().equals(person.getName()))
                        throw new Exception(person.getName() + " can not be removed because " +  person.getName() + " is the payer of " + e_ticket.getName());
                    else
                    {
                        for (Map.Entry<Person, Double> entry1 : e_ticket.getAmountPerPerson().entrySet())
                        {
                            if (entry1.getKey().equals(person))
                            {
                                throw new Exception(person.getName() + " can not be removed because " + person.getName() + " needs to pay in " + e_ticket.getName());
                            }
                        }
                    }
                }

                removable = true;
                this.db.remove(e_personID,e_person);
                support.firePropertyChange("PersonDB remove", null, person);
                personCount--;
                break;
            }
        }

        if (!removable)
        {
            throw new Exception(person.getName() + " can not be removed!");
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
    public ArrayList<Person> getPersons()
    {
        ArrayList<Person> personList = new ArrayList<>();
        for(Map.Entry<Integer, Person> e: this.db.entrySet())
        {
            Person e_Value = e.getValue();
            personList.add(e_Value);
        }
        return personList;
    }


    @Override
    public ArrayList<String> getNames()
    {
        ArrayList<String> nameList = new ArrayList<>();
        for(Map.Entry<Integer, Person> e: this.db.entrySet())
        {
            String name = e.getValue().getName();
            nameList.add(name);
        }
        return nameList;
    }


    @Override
    public Person getPerson(String name)
    {
        Person person = null;
        for (Map.Entry<Integer, Person> e : this.db.entrySet())
        {
            if (e.getValue().getName().equals(name))
            {
                person =  e.getValue();
                break;
            }
        }
        return person;
    }
}
