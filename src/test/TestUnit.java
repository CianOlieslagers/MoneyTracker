import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.TicketDB;
import decorator.TicketDecorator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestUnit {


    // Test objects
    private TicketController ticketController;
    private DatabaseTickets mockDatabase;


    private Person mockPersons;



    @Before
    public void initialize() {

    }
    @After
    public void restoreStreams() {

    }


    @Test
    public void testAddTicket() throws Exception {

        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);

        Ticket mockTicket = mock(Ticket.class);
        ticketController.addTicket(mockTicket);// Voegt een Mock ticket toe aan onze mockDatabase

        verify(mockDatabase).addTicket(mockTicket);// Checkt of the .addticket method is called.
    }

    @Test
    public void testRemoveTicket() {

        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);
        Ticket mockTicket = mock(Ticket.class);
        ticketController.removeTicket(mockTicket); // Verwijderd een Mock ticket van onze mockDatabase
        verify(mockDatabase).removeTicket(mockTicket);// Checkt of the .removeTicket method is called.
    }

    @Test
    public void testGetTickets() {

        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);
        ArrayList<Ticket> mockTicketList = new ArrayList<>();
        when(mockDatabase.getTickets()).thenReturn(mockTicketList);


        ArrayList<Ticket> result = ticketController.getTickets();

        assertEquals(mockTicketList, result); // Checkt of onze gecreëerde array gelijk is aan de array gecreëerd door onze .getTickets funtion
    }

    @Test
    public void testTotaalSum() {
        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);
        when(mockDatabase.totaalSum()).thenReturn(100.0);

        assertEquals(100.0, ticketController.totaalSum(), 0.001); // Zie of onze .totaalsom gelijk is aan de opvoorhand opgegeven waarde met een foutmarge van 0.001
    }


    @Test
    public void testKostPP() {
        //
    }

    @Test
    public void testPrintSchulden() {

        mockDatabase = mock(DatabaseTickets.class);
        ticketController = new TicketController(mockDatabase);
        // Arrange
        // Arrange
        HashMap<Person, Double> schuldenMap = new HashMap<>();
        Person person1 = new Person("Charlie", "123");
        Person person2 = new Person("Bob", "123");
        Person person3 = new Person("Melanie", "123");
        Person person4 = new Person("Mel", "123");

        schuldenMap.put(person1, 40.0);
        schuldenMap.put(person2, -20.0);
        schuldenMap.put(person3, -40.0);
        schuldenMap.put(person4, 20.0);

        // Act

        List<String> output;
        output = ticketController.printSchulden(schuldenMap);

        // Assert
        List<String> expectedLines = Arrays.asList(
                "Charlie moet nog 40.0 euro ontvangen aan:",
                "  - Melanie: 40.0 euro",
                "Mel moet nog 20.0 euro ontvangen aan:",
                "  - Bob: 20.0 euro"
        );


        assertEquals(output,expectedLines );

    }




}















