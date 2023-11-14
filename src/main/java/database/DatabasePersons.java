package database;

import Person.Person;

import java.beans.PropertyChangeListener;

public abstract class DatabasePersons
{
    public DatabasePersons()
    {

    }

    public abstract void addPerson(Person person);

    public abstract void removePerson(Person person);
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);

}
