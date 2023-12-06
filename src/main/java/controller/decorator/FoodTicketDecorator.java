package controller.decorator;

import ticket.Category;
import ticket.Ticket;

public class FoodTicketDecorator extends TicketDecorator
{
    public FoodTicketDecorator(Ticket decoratedTicket)
    {
        super(decoratedTicket);
    }

    @Override
    public Category getCategory()
    {
        return Category.Food;
    }
}
