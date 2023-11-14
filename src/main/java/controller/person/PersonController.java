package controller.person;

import Person.Person;
import database.PersonDB;

public class PersonController implements Controller
{

    private PersonDB db;


    public PersonController(PersonDB db)
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
