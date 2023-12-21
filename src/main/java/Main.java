import GUI.homescreen.HomeFrame;
import controller.person.PController;
import controller.person.PersonController;
import controller.ticket.TController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import decorator.ActivitiesTicketDecorator;
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
        Pregister.addPerson(LordPlease);


        HashMap<Person, Double> TestValues1 = new HashMap<>();
        TestValues1.put(Mel, 20.0);
        TestValues1.put(Bob,20.0);
        TestValues1.put(LordPlease,20.0);

        HashMap<Person, Double> TestValues2 = new HashMap<>();
        TestValues2.put(Mel, 20.0);
        TestValues2.put(Bob,10.0);
        TestValues2.put(Charlie,10.0);
        TestValues2.put(Melanie,20.0);
        TestValues2.put(LordPlease,30.0);

        HashMap<Person, Double> TestValues3 = new HashMap<>();
        TestValues3.put(Mel, 50.0);





        TicketFactory TF1 = new TicketFactory();
        TicketFactory TF2 = new TicketFactory();
        Ticket test2 = TF1.getTicket("test1","Bob", 60, Category.Food,false, TestValues1);
        Ticket test1 = TF1.getTicket("test2","Mel", 90, Category.Food,false, TestValues2);
        Ticket test3 = TF1.getTicket("test3","LordPlease", 50, Category.Food,false, TestValues3);


        System.out.println(DbP1.getNames());

        Tregister.addTicket(test1);
        Tregister.addTicket(test2);
        Tregister.addTicket(test3);

        HashMap<Person,Double> AfrekeningBob;
        AfrekeningBob = Tregister.KostPP(Bob);
        System.out.print("Rekening voor Bob: " + AfrekeningBob+ "\n");

        HashMap<Person,Double> AfrekeningMel;
        AfrekeningMel = Tregister.KostPP(Mel);
        System.out.print("Rekening voor Mel: " + AfrekeningMel+ "\n");

        System.out.print("please lord werk: "+ "\n");
        DbT1.printSchulden(AfrekeningBob);

        System.out.print("please lord werk: "+ "\n");
        DbT1.printSchulden(AfrekeningMel);


        double Totaal = Tregister.totaalSum();
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
