package database;

import person.Person;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public abstract class DatabasePersons
{

    private static DatabasePersons instance = null;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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
    //


    // Voor uw Observer te laten werken
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    public void firePropertyChange(Person P) {
        propertyChangeSupport.firePropertyChange("PersonDB", null, P);
    }
    //

    public abstract void addPerson(Person person);
    public abstract void removePerson(Person person);
    public abstract ArrayList<String> getNames();
    public abstract void addObserver(PropertyChangeListener pcl);
    public abstract void removeObserver(PropertyChangeListener pcl);

}
