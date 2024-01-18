import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.TicketDB;
import decorator.TicketDecorator;
import factory.TicketFactory;
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
    public void t_getBill() throws NoSuchFieldException, IllegalAccessException {
        // Access the private field inside the TicketDB class
        Field field = TicketDB.class.getDeclaredField("db");
        field.setAccessible(true);

        DatabaseTickets ticketDBUnderTest = TicketDB.getInstance();
        HashMap<Integer, Ticket> mockMap = new HashMap<>();



        Person Melanie = new Person("Melanie","1" );
        Person Ann = new Person("Ann","2" );
        Person Bob = new Person("Bob","3" );
        Person Charlie = new Person("Charlie","4");


        HashMap<Person, Double> TestValues1 = new HashMap<>();
        TestValues1.put(Charlie, 20.0);
        TestValues1.put(Bob ,20.0);
        TestValues1.put(Melanie,30.0);

        HashMap<Person, Double> TestValues2 = new HashMap<>();
        TestValues2.put(Ann, 10.0);
        TestValues2.put(Melanie,40.0);
        TestValues2.put(Charlie,10.0);


        // Create and add tickets to the database
        TicketFactory TF1 = new TicketFactory();
        Ticket test1 = TF1.getTicket("test1", "Charlie", 70, Category.Food, false, TestValues1);
        Ticket test2 = TF1.getTicket("test2", "Ann", 60, Category.Food, false, TestValues2);

        mockMap.put(0, test1);
        mockMap.put(1, test2);
        field.set(ticketDBUnderTest, mockMap);

        // Test function
        HashMap<Person, Double> actualBill = ticketDBUnderTest.getBill();

        HashMap<Person, Double> ExpectedBill =  new HashMap<>();

        ExpectedBill.put(Bob,-20.0);
        ExpectedBill.put(null,130.0);
        ExpectedBill.put(Melanie,-70.0);
        ExpectedBill.put(Ann,50.0);
        ExpectedBill.put(Charlie,40.0);


        assertEquals(actualBill,ExpectedBill );
    }

    @Test
    public void testPrintSchulden() throws NoSuchFieldException, IllegalAccessException {

        // Access the private field inside the TicketDB class
        Field field = TicketDB.class.getDeclaredField("db");
        field.setAccessible(true);

        DatabaseTickets ticketDBUnderTest = TicketDB.getInstance();
        HashMap<Integer, Ticket> mockMap = new HashMap<>();

        Person Melanie = new Person("Melanie","1" );
        Person Ann = new Person("Ann","2" );
        Person Bob = new Person("Bob","3" );
        Person Charlie = new Person("Charlie","4");


        HashMap<Person, Double> TestValues1 = new HashMap<>();
        TestValues1.put(Charlie, 20.0);
        TestValues1.put(Bob ,20.0);
        TestValues1.put(Melanie,30.0);

        HashMap<Person, Double> TestValues2 = new HashMap<>();
        TestValues2.put(Ann, 10.0);
        TestValues2.put(Melanie,40.0);
        TestValues2.put(Charlie,10.0);



        // Create and add tickets to the database
        TicketFactory TF1 = new TicketFactory();
        Ticket test1 = TF1.getTicket("test1", "Charlie", 70, Category.Food, false, TestValues1);
        Ticket test2 = TF1.getTicket("test2", "Ann", 60, Category.Food, false, TestValues2);

        mockMap.put(0, test1);
        mockMap.put(1, test2);
        field.set(ticketDBUnderTest, mockMap);



        // Test function
        ArrayList<String> actualStringBillPerPerson = ticketDBUnderTest.getBillPerPerson(Melanie);
        // Compare actualBillPerPerson with the expected values based on your logic
        
        ArrayList<String> ExcetedString = new ArrayList<>();

        assertEquals(actualStringBillPerPerson,ExcetedString );
    }




}















