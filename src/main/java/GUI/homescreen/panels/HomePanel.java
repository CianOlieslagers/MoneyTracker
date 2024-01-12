package GUI.homescreen.panels;


import GUI.calculate.CalculateFrame;
import GUI.person.add.AddPersonFrame;
import GUI.person.show.ShowPersonFrame;
import GUI.ticket.add.AddTicketFrame;
import GUI.ticket.show.ShowTicketFrame;
import controller.person.PersonController;
import controller.ticket.TicketController;


import javax.swing.*;

public class HomePanel extends JPanel
{
    private PersonController personController;
    private TicketController ticketController;

    private JLabel choise;
    private JButton addPerson;
    private JButton showPersons;
    private JButton addTicket;
    private JButton showTickets;
    private JButton calculate;


    public HomePanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;


        this.choise = new JLabel("Choise:");
        this.addPerson = new JButton("Add a person");
        this.showPersons = new JButton("Show all persons");
        this.addTicket = new JButton("Add a ticket");
        this.showTickets = new JButton("Show all tickets");
        this.calculate = new JButton("Calculate the total");


        // Create your temporary employee here
        addPersonButtonActionListener();
        showPersonButtonActionListener();
        addTicketButtonActionListener();
        showTicketButtonActionListener();
        calculateButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(this.choise)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(this.addPerson)
                            .addComponent(this.showPersons))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(this.addTicket)
                            .addComponent(this.showTickets))
                        .addComponent(this.calculate)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.addPerson)
                                .addComponent(this.addTicket)
                                .addComponent(this.choise))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.showPersons)
                                .addComponent(this.showTickets))
                        .addComponent(this.calculate)
        );

    }


    public void addPersonButtonActionListener()
    {
        this.addPerson.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : add person");
            AddPersonFrame frame = new AddPersonFrame();
            frame.initialize();
        });
    }


    public void showPersonButtonActionListener()
    {
        this.showPersons.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : show persons");
            ShowPersonFrame frame = new ShowPersonFrame();
            frame.initialize();
        });
    }


    public void addTicketButtonActionListener()
    {
        this.addTicket.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : add ticket");
            AddTicketFrame frame = new AddTicketFrame();
            frame.initialize();
        });
    }


    public void showTicketButtonActionListener()
    {
        this.showTickets.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : show tickets");
            ShowTicketFrame frame = new ShowTicketFrame();
            frame.initialize();
        });
    }


    public void calculateButtonActionListener()
    {
        this.calculate.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : calculate");
            CalculateFrame frame = new CalculateFrame();
            frame.initialize();
        });
    }
}
