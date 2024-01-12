package GUI.person.show;

import GUI.person.show.panels.ShowPersonPanel;
import controller.person.PersonController;
import controller.ticket.TicketController;
import database.DatabasePersons;
import database.PersonDB;
import database.TicketDB;

import javax.swing.*;
import java.awt.*;

public class ShowPersonFrame extends JFrame
{
    PersonController personController = new PersonController(PersonDB.getInstance());
    TicketController ticketController = new TicketController(TicketDB.getInstance());
    DatabasePersons personDB = PersonDB.getInstance();

    ShowPersonPanel buttons;


    public ShowPersonFrame()
    {
        super("SHOW PERSON SCREEN");
    }


    public void initialize()
    {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new ShowPersonPanel(personController, ticketController,this);

        personDB.addObserver(buttons);

        this.add(buttons);
        this.setVisible(true);
    }
}
