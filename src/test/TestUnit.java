import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.TicketDB;
import org.junit.Before;
import org.junit.Test;
import ticket.Category;
import ticket.Ticket;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static ticket.Category.Airplane;

public class TestUnit {

    private TController ticketController;
    private TicketDB ticketDB;




    @Before
    public void setup(){

        ticketDB = (TicketDB) TicketDB.getInstance();
        ticketController = new TicketController(ticketDB);

    }
    @Test
    public void testAddTicket() throws Exception {

        Field field = TicketController.class.getDeclaredField("db");
        field.setAccessible(true);


        Ticket ticket = new Ticket("TestTicket", "User1", 50.0, Category.Food, false, new HashMap<>());
        ticketController.addTicket(ticket);

        ArrayList<Ticket> tickets = ticketController.getTickets();
        assertEquals(1, tickets.size());
        assertEquals(ticket, tickets.get(0));
    }

}














