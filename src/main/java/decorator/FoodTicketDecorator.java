package decorator;

import ticket.Category;
import ticket.Ticket;

public class FoodTicketDecorator extends TicketDecorator
{
    public FoodTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Ticket decorate(Ticket oldTicket)
    {
        oldTicket.setCategory(Category.Food);
        return oldTicket;
}
}

