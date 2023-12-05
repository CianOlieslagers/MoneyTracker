import GUI.MainFrame;
import controller.Decorator.AirplaneTicketDecorator;
import controller.person.PController;
import controller.person.PersonController;
import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.TicketDB;
import factory.TicketFactory;
import person.Person;
import ticket.Category;
import ticket.Ticket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.util.Iterator;

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
        Person Mel = new Person("Mel","22" );
        Person Bob = new Person("Bob","2" );

        Pregister.addPerson(Melanie);
        Pregister.addPerson(Mel);
        Pregister.addPerson(Bob);

        Pregister.removePerson(Melanie);
        Pregister.removePerson(Mel);
        Pregister.removePerson(Bob);

        TicketFactory TF1 = new TicketFactory();
        Ticket TicketMelanie = TF1.getTicket("Melanie", 20, Category.Food);
        Tregister.addTicket(TicketMelanie);

        double Totaal = Tregister.totaalSum(DbT1);
        System.out.print(Totaal);

        TicketMelanie = new AirplaneTicketDecorator(TicketMelanie);

        Pregister.removePerson(Melanie);
        Tregister.removeTicket(TicketMelanie);




        // Frame Logica
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));



    }



    public void run()
    {
        System.out.println("running");
    }
}
