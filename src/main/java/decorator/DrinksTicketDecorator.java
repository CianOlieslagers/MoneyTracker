package decorator;

import ticket.Category;
import ticket.Ticket;

public class DrinksTicketDecorator extends TicketDecorator
{
    public DrinksTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Ticket decorate(Ticket oldTicket)
    {
        oldTicket.setCategory(Category.Drinks);
        return oldTicket;}

}
