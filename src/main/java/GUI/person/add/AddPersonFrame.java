package GUI.person.add;

import GUI.person.add.panels.AddPersonPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import java.awt.*;

public class AddPersonFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());

    DatabasePersons personDB = PersonDB.getInstance();

    AddPersonPanel buttons;


    public AddPersonFrame()
    {
        super("ADD PERSON SCREEN");
    }


    public void initialize()
    {
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new AddPersonPanel(personController, ticketController,this);

        personDB.addObserver(buttons);

        this.add(buttons);
        this.setVisible(true);
    }
}
