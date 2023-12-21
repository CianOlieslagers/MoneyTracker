package decorator;

import ticket.Category;
import ticket.Ticket;

public abstract class TicketDecorator extends Ticket
{
    private Ticket decoratedTicket;

    public TicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket.getName(), decoratedTicket.getPayer(), decoratedTicket.getAmount(), decoratedTicket.getCategory(),decoratedTicket.getSplitEvenly(),decoratedTicket.getAmountPerPerson());
        this.decoratedTicket = decoratedTicket;
    }


    public abstract Category getCategory();

}
