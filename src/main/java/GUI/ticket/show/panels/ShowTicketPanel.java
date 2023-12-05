package GUI.ticket.show.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;
import ticket.Ticket;

import javax.swing.*;

public class ShowTicketPanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;

    private JLabel ticketLabel;

    private JList<Ticket> ticketJList;
    private DefaultListModel<Ticket> ticketListModel;

    public ShowTicketPanel(PersonController personController, TicketController ticketController)
    {

        this.personController = personController;
        this.ticketController = ticketController;
        this.ticketLabel = new JLabel("Tickets");
        this.ticketListModel = new DefaultListModel<>();

        for (Ticket ticket : ticketController.getTickets())
        {
            ticketListModel.addElement(ticket);
        }

        this.ticketJList = new JList<>(ticketListModel);


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel)
                                .addComponent(this.ticketJList))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketJList))
        );


    }
}
