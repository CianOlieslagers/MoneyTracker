package database;

import person.Person;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public abstract class DatabasePersons
{

    private static DatabasePersons instance = null;


    public DatabasePersons()
    {

    }
    // Toevoeging van de singleton voor beide Databaseperson
    public static DatabasePersons getInstance() { // First we check if the database already exist. If it does we use that database instead.
        if (instance == null) {
            instance = new PersonDB(); // Or any other implementation you prefer
        }
        return instance;
    }

    public abstract void addPerson(Person person);
    public abstract void removePerson(Person person);
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);

}
