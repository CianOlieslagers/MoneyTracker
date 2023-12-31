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

import javax.crypto.interfaces.DHPublicKey;
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
        PController Pregister = new PersonController(DbP1);

        DatabaseTickets DbT1 = TicketDB.getInstance();
        TController Tregister = new TicketController(DbT1);


        Person Melanie = new Person("Melanie","1" );
        Person Mel = new Person("Mel","2" );
        Person Bob = new Person("Bob","22" );
        Person Charlie = new Person("Charlie","33");
        Person LordPlease =  new Person("LordPlease", "666");

        Pregister.addPerson(Melanie);
        Pregister.addPerson(Mel);
        Pregister.addPerson(Bob);
        Pregister.addPerson(Charlie);
        //Pregister.addPerson(LordPlease);


        HashMap<Person, Double> TestValues1 = new HashMap<>();
        TestValues1.put(Charlie, 20.0);
        TestValues1.put(Bob ,20.0);
        TestValues1.put(Melanie,30.0);

        HashMap<Person, Double> TestValues2 = new HashMap<>();
        TestValues2.put(Mel, 10.0);
        TestValues2.put(Melanie,40.0);
        TestValues2.put(Charlie,10.0);

        HashMap<Person, Double> TestValues3 = new HashMap<>();
        TestValues3.put(Mel, 44.);
        TestValues3.put(Charlie,55.);

        HashMap<Person, Double> TestValues4 = new HashMap<>();
        TestValues4.put(Bob, 20.);
        TestValues4.put(Melanie,20.);
        TestValues4.put(Charlie,4.);

        HashMap<Person, Double> TestValues5 = new HashMap<>();
        TestValues5.put(Mel, 10.0);
        TestValues5.put(Melanie,40.0);
        TestValues5.put(Bob,20.0);

        TicketFactory TF1 = new TicketFactory();
        Ticket test1 = TF1.getTicket("test1","Charlie", 70, Category.Food,false, TestValues1);
        Ticket test2 = TF1.getTicket("test2","Mel", 60, Category.Food,false, TestValues2);


        System.out.println(DbP1.getNames());

        Tregister.addTicket(test1);
        Tregister.addTicket(test2);

        Ticket ticket3 = TF1.getTicket("test3","Melanie",99,Category.Airplane,false,TestValues3);
        Ticket ticket4 = TF1.getTicket("test4","Bob",44,Category.Airplane,false,TestValues4);
        Ticket ticket5 = TF1.getTicket("test5","Melanie",70,Category.Food,false,TestValues5);

        Tregister.addTicket(ticket3);
        Tregister.addTicket(ticket4);
        //Tregister.addTicket(ticket5);

        /*
        HashMap<Person,Double> AfrekeningBob;
        AfrekeningBob = Tregister.KostPP(Bob);
        System.out.print("Rekening voor Bob: " + AfrekeningBob+ "\n");

        HashMap<Person,Double> AfrekeningMel;
        AfrekeningMel = Tregister.KostPP(Mel);
        System.out.print("Rekening voor Mel: " + AfrekeningMel+ "\n");

        System.out.print("please lord werk: "+ "\n");
        DbT1.printSchulden(AfrekeningBob);



        // Test voor de decorator te laten zien.
        System.out.print("Voor de decorator: " + "\n" +test1);



        TicketDecorator decorator = new AirplaneTicketDecorator(test1);
        Tregister.setActivity(test1, decorator);


        System.out.print("Na de decorator" + "\n" +test1);
        double Totaal = Tregister.totaalSum();
        System.out.print(Totaal+ "\n");




        System.out.println("Melanie: " + Tregister.KostPP(Melanie));
        System.out.println("Bob: " + Tregister.KostPP(Bob));
        System.out.println("Mel: " + Tregister.KostPP(Mel));
        System.out.println("Charlie: " + Tregister.KostPP(Charlie));

         */

        // Frame Logica
        //SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));

        HomeFrame view = new HomeFrame();
        view.initialize();


    }


    public void run()
    {
        System.out.println("running");
    }
}
