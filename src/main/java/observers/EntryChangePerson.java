package observers;

import person.Person;
import ticket.Ticket;

import java.util.EventObject;

public class EntryChangePerson extends EventObject {

    private Person person;

    public EntryChangePerson(Object source, Person person){
        super(source);
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }


}
