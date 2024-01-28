import GUI.homescreen.HomeFrame;
import controller.person.PController;
import controller.person.PersonController;
import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import decorator.AirplaneTicketDecorator;
import decorator.TicketDecorator;
import factory.TicketFactory;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import java.util.HashMap;

public class Main
{
    public Main()
    {

    }

    public static void main(String[] args) throws Exception
    {
        Main main = new Main();
        main.run();

        DatabasePersons DbP1 = PersonDB.getInstance();
        DatabaseTickets DbT1 = TicketDB.getInstance();

        TController ticketController = new TicketController(DbT1);
        PController personController = new PersonController(DbP1);


        Person Melanie = new Person("Melanie","BE25 7690 7345 9203");
        Person Ann = new Person("Ann","BE48 3521 5544 9890");
        Person Bob = new Person("Bob","BE34 0987 6543 2100");
        Person Charlie = new Person("Charlie","BE56 2468 0876 3429");

        personController.addPerson(Melanie);
        personController.addPerson(Ann);
        personController.addPerson(Bob);
        personController.addPerson(Charlie);


        HashMap<Person, Double> TestValues1 = new HashMap<>();
        TestValues1.put(Charlie, 20.0);
        TestValues1.put(Bob ,20.0);
        TestValues1.put(Melanie,30.0);

        HashMap<Person, Double> TestValues2 = new HashMap<>();
        TestValues2.put(Ann, 10.0);
        TestValues2.put(Melanie,40.0);
        TestValues2.put(Charlie,10.0);

        HashMap<Person, Double> TestValues3 = new HashMap<>();
        TestValues3.put(Ann, 44.);
        TestValues3.put(Charlie,55.);

        HashMap<Person, Double> TestValues4 = new HashMap<>();
        TestValues4.put(Bob, 20.);
        TestValues4.put(Melanie,20.);
        TestValues4.put(Charlie,4.);

        HashMap<Person, Double> TestValues5 = new HashMap<>();
        TestValues5.put(Ann, 10.0);
        TestValues5.put(Melanie,40.0);
        TestValues5.put(Bob,20.0);

        TicketFactory factory = new TicketFactory();

        Ticket ticket1 = factory.getTicket("Delhaize","Charlie", 70, Category.Food,false, TestValues1);
        Ticket ticket2 = factory.getTicket("Restaurant","Ann", 60, Category.Food,false, TestValues2);
        Ticket ticket3 = factory.getTicket("Tickets","Melanie",99,Category.Airplane,false,TestValues3);
        Ticket ticket4 = factory.getTicket("Paintball","Bob",44,Category.Activities,false,TestValues4);
        Ticket ticket5 = factory.getTicket("Cafe","Melanie",70,Category.Drinks,false,TestValues5);

        ticketController.addTicket(ticket1);
        ticketController.addTicket(ticket2);
        ticketController.addTicket(ticket3);
        ticketController.addTicket(ticket4);
        ticketController.addTicket(ticket5);


        System.out.println(ticket1.getName() + " before the decorator: " + "\n" + ticket1);

        TicketDecorator decorator = new AirplaneTicketDecorator(ticket1);
        ticketController.setActivity(ticket1, decorator);

        System.out.print(ticket1.getName() + " after the decorator: " + "\n" + ticket1);

        HomeFrame view = new HomeFrame();
        view.initialize();

    }


    public void run()
    {
        System.out.println("running...");
    }
}
