package controller.person;

import database.DatabasePersons;
import person.Person;
import database.PersonDB;

public class PersonController implements Controller
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
}
