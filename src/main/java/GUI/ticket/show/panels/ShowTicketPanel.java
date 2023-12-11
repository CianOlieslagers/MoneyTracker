package GUI.ticket.show.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;
import ticket.Ticket;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowTicketPanel extends JPanel implements PropertyChangeListener
{

    PersonController personController;
    TicketController ticketController;

    private JLabel ticketLabel;
    private JButton deleteButton;

    private JList<Ticket> ticketJList;
    private DefaultListModel<Ticket> ticketListModel;

    private JList<String> ticketNameJList;
    private DefaultListModel<String> ticketNameModel;

    private JList<Double> ticketAmountJList;
    private DefaultListModel<Double> ticketAmountModel;

    public ShowTicketPanel(PersonController personController, TicketController ticketController)
    {

        this.personController = personController;
        this.ticketController = ticketController;

        this.ticketLabel = new JLabel("Tickets");
        this.deleteButton = new JButton("Delete selected ticket");
        this.ticketListModel = new DefaultListModel<>();

        for (Ticket ticket : ticketController.getTickets())
        {
            ticketListModel.addElement(ticket);
            //ticketNameModel.addElement(ticket.getName());
            //ticketAmountModel.addElement(ticket.getAmount());
        }


        this.ticketJList = new JList<>(ticketListModel);

        deleteButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel)
                                .addComponent(this.deleteButton)
                                .addComponent(this.ticketJList))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketJList))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.deleteButton))
        );

    }


    public void deleteButtonActionListener()
    {
        this.deleteButton.addActionListener(listener ->
        {
            if (!this.ticketJList.isSelectionEmpty())
            {
                System.out.println("You've selected " + this.ticketJList.getSelectedValue());
                this.ticketController.removeTicket(this.ticketJList.getSelectedValue());
            }
            else
            {
                System.out.println("No ticket selected!");
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("TicketDB remove"))
        {
            Ticket ticket = (Ticket) evt.getNewValue();
            this.ticketListModel.removeElement(ticket);
        }
    }
}
