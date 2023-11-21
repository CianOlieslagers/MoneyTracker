package factory;

import ticket.Category;
import ticket.Ticket;

import java.util.Objects;

public class TicketFactory
{
    public Ticket getTicket(String name, double amount, Category category)
    {
        if (Objects.equals(category, Category.Food))
            return new Ticket(name,amount, Category.Food);
        else if (Objects.equals(category, Category.Taxi))
            return new Ticket(name, amount, Category.Taxi);
        else if (Objects.equals(category, Category.Drinks))
            return new Ticket(name, amount, Category.Drinks);
        else if (Objects.equals(category, Category.Airplane))
            return new Ticket(name, amount, Category.Airplane);
        else if (Objects.equals(category,Category.Activities))
            return new Ticket(name, amount, Category.Activities);
        else
            return new Ticket(name, amount, Category.Others);
    }
}
