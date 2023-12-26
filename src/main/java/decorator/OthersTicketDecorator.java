package decorator;

import ticket.Category;
import ticket.Ticket;

public class OthersTicketDecorator extends TicketDecorator {
    public OthersTicketDecorator(Ticket decoratedTicket) {
        super(decoratedTicket);
    }

    @Override
    public Ticket decorate(Ticket oldTicket) {
        oldTicket.setCategory(Category.Others);
        return oldTicket;
    }
}

