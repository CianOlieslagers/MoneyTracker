import GUI.MainFrame;
import controller.person.PController;
import controller.person.PersonController;
import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import factory.TicketFactory;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import javax.swing.*;

public class Main
{
    public Main()
    {

    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();

        DatabasePersons DbP1 = DatabasePersons.getInstance();
        PController Pregister = new PersonController(DbP1);

        DatabaseTickets DbT1 = DatabaseTickets.getInstance();
        TController Tregister = new TicketController(DbT1);

        Person Melanie = new Person("Melanie","1" );
        Pregister.addPerson(Melanie);

        TicketFactory TF1 = new TicketFactory();
        Ticket TicketMelanie = TF1.getTicket("Melanie", 20, Category.Food);
        Tregister.addTicket(TicketMelanie);

        // Frame Logica
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));




    }

    public void run()
    {
        System.out.println("running");
    }
}
