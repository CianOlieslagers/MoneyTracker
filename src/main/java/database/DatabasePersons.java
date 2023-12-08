package database;

import person.Person;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public abstract class DatabasePersons
{
    protected DatabasePersons()
    {

    }

    public abstract void addPerson(Person person);
    public abstract void removePerson(Person person);
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);
    public abstract ArrayList<Person> getPersons();
    public abstract ArrayList<String> getNames();
    public abstract Person getPerson(String name);

}
