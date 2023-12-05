package controller.person;

import person.Person;

import java.util.ArrayList;

public interface PController
{
    void addPerson(Person person);
    void removePerson(Person person);
    ArrayList<String> getNames();
    ArrayList<Person> getPersons();
}
