package factory;

import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.util.HashMap;
import java.util.Objects;

public class TicketFactory
{
    public Ticket getTicket(String name, String payer, double amount, Category category, Boolean splitEvenly, HashMap<Person,Double> amountPerPerson)
    {
        if (Objects.equals(category, Category.Food))
            return new Ticket(name, payer, amount, Category.Food,splitEvenly,amountPerPerson);
        else if (Objects.equals(category, Category.Taxi))
            return new Ticket(name, payer, amount, Category.Taxi,splitEvenly,amountPerPerson);
        else if (Objects.equals(category, Category.Drinks))
            return new Ticket(name, payer, amount, Category.Drinks,splitEvenly,amountPerPerson);
        else if (Objects.equals(category, Category.Airplane))
            return new Ticket(name, payer, amount, Category.Airplane,splitEvenly,amountPerPerson);
        else if (Objects.equals(category,Category.Activities))
            return new Ticket(name, payer, amount, Category.Activities,splitEvenly,amountPerPerson);
        else
            return new Ticket(name, payer, amount, Category.Others,splitEvenly,amountPerPerson);
    }
}
