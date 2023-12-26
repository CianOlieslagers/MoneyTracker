import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.TicketDB;
import decorator.TicketDecorator;
import org.junit.Before;
import org.junit.Test;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestUnit {

    private TicketController ticketController;
    private DatabaseTickets mockDatabase;

    @Before
    public void initialize() {
        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);
    }

    @Test
    public void testAddTicket() throws Exception {
        Ticket mockTicket = mock(Ticket.class);
        ticketController.addTicket(mockTicket);
        verify(mockDatabase).addTicket(mockTicket);
    }

    @Test
    public void testRemoveTicket() {
        Ticket mockTicket = mock(Ticket.class);
        ticketController.removeTicket(mockTicket);
        verify(mockDatabase).removeTicket(mockTicket);
    }

    @Test
    public void testGetTickets() {
        ArrayList<Ticket> mockTicketList = new ArrayList<>();
        when(mockDatabase.getTickets()).thenReturn(mockTicketList);

        assertEquals(mockTicketList, ticketController.getTickets());
    }

    @Test
    public void testTotaalSum() {
        when(mockDatabase.totaalSum()).thenReturn(100.0);

        assertEquals(100.0, ticketController.totaalSum(), 0.001);
    }

    @Test
    public void testKostPP() {
        Person mockPerson = mock(Person.class);
        HashMap<Person, Double> mockResult = new HashMap<>();
        when(mockDatabase.KostPP(mockPerson)).thenReturn(mockResult);

        assertEquals(mockResult, ticketController.KostPP(mockPerson));
    }

    @Test
    public void testPrintSchulden() {
        HashMap<Person, Double> mockSchuldenMap = new HashMap<>();
        ticketController.printSchulden(mockSchuldenMap);

        // Add your assertions for printing if needed
    }

    @Test
    public void testSetActivity() throws Exception {
        Ticket mockOldTicket = mock(Ticket.class);
        TicketDecorator mockCategory = mock(TicketDecorator.class);
        ticketController.setActivity(mockOldTicket, mockCategory);

        // Add your assertions for setting activity if needed
    }
}













