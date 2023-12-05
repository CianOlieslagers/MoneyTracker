package controller.Decorator;


import ticket.Category;
import ticket.Ticket;

public abstract class TicketDecorator extends Ticket {
    private Ticket decoratedTicket;

    public TicketDecorator(Ticket decoratedTicket) {
        super(decoratedTicket.getName(), decoratedTicket.getPayer(), decoratedTicket.getAmount(), decoratedTicket.getCategory(),decoratedTicket.getSplitEvenly(),decoratedTicket.getAmountPerPerson());
        this.decoratedTicket = decoratedTicket;
    }

    @Override
    public Category getCategory() {
        // Replace this with the desired behavior to modify or replace the category
        return decoratedTicket.getCategory();
    }
}
