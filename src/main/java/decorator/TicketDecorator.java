package decorator;

import ticket.Category;
import ticket.Ticket;

public abstract class TicketDecorator extends Ticket
{

    public TicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket.getName(), decoratedTicket.getPayer(), decoratedTicket.getAmount(), decoratedTicket.getCategory(),decoratedTicket.getSplitEvenly(),decoratedTicket.getAmountPerPerson());

    }


    public abstract Ticket decorate(Ticket oldTicket);

}
