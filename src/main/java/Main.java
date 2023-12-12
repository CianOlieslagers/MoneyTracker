import GUI.homescreen.HomeFrame;
import controller.person.PController;
import controller.person.PersonController;
import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import factory.TicketFactory;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import javax.crypto.interfaces.DHPublicKey;
import java.util.HashMap;

public class Main
{
    public Main()
    {

    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();

        DatabasePersons DbP1 = PersonDB.getInstance();
        PController Pregister = new PersonController(DbP1);

        DatabaseTickets DbT1 = TicketDB.getInstance();
        TController Tregister = new TicketController(DbT1);


        Person Melanie = new Person("Melanie","1" );
        Person Melanie2 = new Person("Melanie","55" );
        Person Mel = new Person("Mel","2" );
        Person Bob = new Person("Bob","22" );
        Person Charlie = new Person("Charlie","33");

        Pregister.addPerson(Melanie);
        Pregister.addPerson(Melanie2);
        Pregister.addPerson(Mel);
        Pregister.addPerson(Bob);
        Pregister.addPerson(Charlie);


        HashMap<Person, Double> TestValues = new HashMap<>();
        TestValues.put(Melanie, 10.0);
        TestValues.put(Mel, 20.0);
        TestValues.put(Bob, 30.0);





        TicketFactory TF1 = new TicketFactory();
        Ticket TicketMelanie = TF1.getTicket("x","Melanie", 60, Category.Food,false, TestValues);
        Ticket Ticket2 = TF1.getTicket("y","Charlie", 60, Category.Food,false, TestValues);
        Ticket Ticket3 = TF1.getTicket("y","MEl", 60, Category.Food,false, TestValues);



        System.out.println(DbP1.getNames());

        Tregister.addTicket(TicketMelanie);
        Tregister.addTicket(Ticket2);
        Tregister.addTicket(Ticket3);

        HashMap<String,Double> AfrekeningBob;
        AfrekeningBob = Tregister.KostPP("Bob", DbT1);
        System.out.print("Rekening voor Bob: " + AfrekeningBob+ "\n");

        double Totaal = Tregister.totaalSum(DbT1);
        System.out.print(Totaal+ "\n");




        // Frame Logica
        //SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));

        //HomeFrame view = new HomeFrame();
        //view.initialize();



    }


    public void run()
    {
        System.out.println("running");
    }
}
