package observers;

import ticket.Ticket;

import java.util.EventObject;

public class EntryChangeTicket extends EventObject {

    private Ticket ticket;

    public EntryChangeTicket(Object source, Ticket ticket){
        super(source);
        this.ticket = ticket;

    }

    public Ticket getTicket(){
        return ticket;
    }
}
