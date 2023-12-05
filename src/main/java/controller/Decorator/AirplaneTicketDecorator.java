package controller.Decorator;

import ticket.Category;
import ticket.Ticket;

public class AirplaneTicketDecorator extends TicketDecorator
{
    public AirplaneTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Category getCategory()
    {
        return Category.Airplane;
    }
}
