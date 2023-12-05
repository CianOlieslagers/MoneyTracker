package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import observers.PrintUpdated;
import person.Person;


public class PersonDB extends DatabasePersons
{
    private final LinkedHashMap<Integer, Person> db;
    private static final PersonDB personDB = new PersonDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int personCount = 0;

    public PersonDB()
    {
        this.db = new LinkedHashMap<>();
        this.addObserver(new PrintUpdated());
    }

    public static DatabasePersons getInstance()
    {
        return personDB;
    }

    @Override
    public void addPerson(Person person)
    {
        boolean existing = false;
        for(Map.Entry<Integer, Person> entry: db.entrySet())
        {
            Person e_person = entry.getValue();

            if (e_person.getName().equals(person.getName()))
            {
                existing = true;
                System.out.println(person.getName() + " is already in use!");
                break;
            }
            if (e_person.getAccountNumber().equals(person.getAccountNumber()))
            {
                existing = true;
                System.out.println("accountnumber: " + person.getAccountNumber() + " is already in use!");
                break;
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
    public void removePerson(Person person)
    {
        for (Map.Entry<Integer, Person> entry: db.entrySet())
        {
            int e_personID = entry.getKey();
            Person e_person = entry.getValue();

            if (e_person.getName().equals(person.getName()) && e_person.getAccountNumber().equals(person.getAccountNumber()))
            {
                this.db.remove(e_personID,e_person);
                support.firePropertyChange("PersonDB remove", null, person);
                personCount--;
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
    public ArrayList<Person> getPersons()
    {
        return null;
    }
}
