package GUI.homescreen.panels;


import GUI.ticket.add.AddTicketFrame;
import GUI.ticket.show.ShowTicketFrame;
import controller.person.PersonController;
import controller.ticket.TicketController;


import javax.swing.*;

public class HomePanel extends JPanel
{

    private PersonController personController;
    private TicketController ticketController;

    private JButton addPerson;
    private JButton showPersons;
    private JButton addTicket;
    private JButton showTickets;
    private JButton calculate;



    public HomePanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;


        JLabel label = new JLabel("Choise");
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
                        .addComponent(label)
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
                                .addComponent(this.addTicket))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.showPersons)
                                .addComponent(this.showTickets)
                                .addComponent(label))
                        .addComponent(this.calculate)
        );

    }

    public void addPersonButtonActionListener()
    {
        this.addPerson.addActionListener(listener ->
        {
            // Insert here your controller functionality
            System.out.println("[HOME-FRAME] : add person");

        });
    }

    public void showPersonButtonActionListener()
    {
        this.showPersons.addActionListener(listener ->
        {
            System.out.println("[HOME-FRAME] : show persons");

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
        });
    }

}
