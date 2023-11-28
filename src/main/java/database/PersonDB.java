package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


import observers.PrintUpdated;
import person.Person;


public class PersonDB extends DatabasePersons
{
    private final ArrayList<Person> db;
    private static final PersonDB personDB = new PersonDB();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PersonDB()
    {
        this.db = new ArrayList<>();
        this.addObserver(new PrintUpdated());
    }

    public static DatabasePersons getInstance()
    {
        return personDB;
    }

    @Override
    public void addPerson(Person person)
    {
        support.firePropertyChange("PersonDB", null, person);
        this.db.add(person);
    }

    @Override
    public void removePerson(Person person)
    {
        support.firePropertyChange("PersonDB", null, person);
        this.db.remove(person);
    }

    @Override
    public ArrayList<String> getNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for (Person person : db)
        {
           names.add(person.getName());
        }
        return names;
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
}
