package GUI.ticket.add;

import GUI.ticket.add.panels.AddTicketPanel;
import GUI.ticket.add.panels.EvenlyPaidPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabaseTickets;
import database.PersonDB;
import database.TicketDB;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AddTicketFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    DatabaseTickets ticketDB = TicketDB.getInstance();

    AddTicketPanel addTicketPanel;
    EvenlyPaidPanel evenlyPaidPanel;

    GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public AddTicketFrame()
    {
        super("ADD TICKET SCREEN");
    }


    public void initialize()
    {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);


        // Pass the controller to the ButtonPanel
        evenlyPaidPanel = new EvenlyPaidPanel(personController, ticketController);
        addTicketPanel = new AddTicketPanel(personController, ticketController, this);

        ticketDB.addObserver(addTicketPanel);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(addTicketPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(addTicketPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(evenlyPaidPanel, gridBagConstraints);

        this.setVisible(true);
    }


    public void setField(Boolean bool)
    {
        this.evenlyPaidPanel.setAmountPerPersonField(bool);
    }


    public HashMap<Person,Double> getInformation(double totalAmount, boolean splitEvenly)
    {
        return this.evenlyPaidPanel.getInformation(totalAmount, splitEvenly);
    }


    public void resetEvenlyPaidPanel()
    {
        this.evenlyPaidPanel.resetPanel();
    }
}
