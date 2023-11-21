import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import factory.TicketFactory;
import observers.PrintUpdated;
import person.Person;
import ticket.Category;
import ticket.Ticket;

public class Main
{
    public Main()
    {

    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public void run()
    {
        System.out.println("running");

        DatabasePersons dbPersons = PersonDB.getInstance();
        DatabaseTickets dbTickets = TicketDB.getInstance();

        PersonController personController = new PersonController(dbPersons);
        TicketController ticketController = new TicketController(dbTickets);

        PrintUpdated observer = new PrintUpdated();

        dbTickets.addObserver(observer);
        dbPersons.removeObserver(observer);

        TicketFactory factory = new TicketFactory();

        Ticket t1 = factory.getTicket("Alice",22.2,Category.Food);
        Ticket t2 = factory.getTicket("Ben",11,Category.Food);
        Ticket t3 = factory.getTicket("Charlie",44.4,Category.Food);

        ticketController.addTicket(t1);
        ticketController.addTicket(t2);
        ticketController.addTicket(t3);

        Person p1 = new Person("Alice","BE48 0000 0000 0001");
        Person p2 = new Person("Ben","BE48 0000 0000 0002");
        Person p3 = new Person("Charlie","BE48 0000 0000 0003");

        personController.addPerson(p1);
        personController.addPerson(p2);
        personController.addPerson(p3);

    }
}
