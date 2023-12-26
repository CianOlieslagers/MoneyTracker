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
    public Ticket decorate(Ticket oldTicket) {
        oldTicket.setCategory(Category.Taxi);
        return oldTicket;
    }

}
