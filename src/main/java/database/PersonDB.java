package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashMap;


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
        support.firePropertyChange("PersonDB add", null, person);
        this.db.put(personCount, person);
        personCount++;
    }

    @Override
    public void removePerson(Person person)
    {
        support.firePropertyChange("PersonDB remove", null, person);
        this.db.remove(personCount, person);
        personCount--;
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
