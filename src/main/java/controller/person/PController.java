package controller.person;

import person.Person;

import java.util.ArrayList;

public interface PController
{
    void addPerson(Person person) throws Exception;
    void removePerson(Person person) throws Exception;
    ArrayList<String> getNames();
    ArrayList<Person> getPersons();
    Person getPerson(String name);
}
