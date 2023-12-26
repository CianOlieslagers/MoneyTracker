package decorator;

import ticket.Category;
import ticket.Ticket;

public class ActivitiesTicketDecorator extends TicketDecorator
{
    public ActivitiesTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Ticket decorate(Ticket oldTicket) {
        oldTicket.setCategory(Category.Activities);
        return oldTicket;}

}
