package decorator;

import ticket.Category;
import ticket.Ticket;

public class AirplaneTicketDecorator extends TicketDecorator
{
    public AirplaneTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Ticket decorate(Ticket oldTicket) {
        oldTicket.setCategory(Category.Airplane);
        return oldTicket;
    }

}
