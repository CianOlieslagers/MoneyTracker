package controller.Decorator;

import ticket.Category;
import ticket.Ticket;

public class ActivitiesTicketDecorator extends TicketDecorator {
    public ActivitiesTicketDecorator(Ticket decoratedTicket) {
        super(decoratedTicket);
    }

    @Override
    public Category getCategory() {
        return Category.Activities;
    }
}
