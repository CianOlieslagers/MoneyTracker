package controller.person;

import database.DatabasePersons;
import observers.PrintUpdated;
import person.Person;

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
}
