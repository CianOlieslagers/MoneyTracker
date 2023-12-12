package decorator;

import ticket.Category;
import ticket.Ticket;

public class TaxiTicketDecorator extends TicketDecorator
{
    public TaxiTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Category getCategory()
    {
        return Category.Taxi;
    }
}
