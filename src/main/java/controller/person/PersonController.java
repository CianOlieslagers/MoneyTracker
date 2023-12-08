package controller.person;

import database.DatabasePersons;
import person.Person;

import java.util.ArrayList;

public class PersonController implements PController
{

    private DatabasePersons db;


    public PersonController(DatabasePersons db)
    {
        this.db = db;
    }

    @Override
    public void addPerson(Person person)
    {
        db.addPerson(person);
    }

    @Override
    public void removePerson(Person person)
    {
        db.removePerson(person);
    }

    @Override
    public ArrayList<String> getNames() {
        return (db.getNames());
    }

    @Override
    public ArrayList<Person> getPersons()
    {
        return db.getPersons();
    }

    @Override
    public Person getPerson(String name)
    {
        return db.getPerson(name);
    }
}
