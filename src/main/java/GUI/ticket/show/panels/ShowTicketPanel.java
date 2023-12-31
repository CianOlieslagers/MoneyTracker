package GUI.ticket.show.panels;

import GUI.ticket.change.ChangeTicketFrame;
import controller.person.PersonController;
import controller.ticket.TicketController;
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
    private JButton editButton;
    private JList<Ticket> ticketJList;
    private DefaultListModel<Ticket> ticketListModel;

    public ShowTicketPanel(PersonController personController, TicketController ticketController)
    {

        this.personController = personController;
        this.ticketController = ticketController;

        this.ticketLabel = new JLabel("Tickets");
        this.deleteButton = new JButton("Delete selected ticket");
        this.editButton = new JButton("Edit selected ticket");
        this.ticketListModel = new DefaultListModel<>();

        for (Ticket ticket : ticketController.getTickets())
        {
            ticketListModel.addElement(ticket);
        }


        this.ticketJList = new JList<>(ticketListModel);

        deleteButtonActionListener();
        editButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel)
                                .addComponent(this.deleteButton)
                                .addComponent(this.editButton)
                                .addComponent(this.ticketJList))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.ticketJList))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.editButton))
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
                System.out.println("You've selected " + this.ticketJList.getSelectedValue() + " to remove");
                this.ticketController.removeTicket(this.ticketJList.getSelectedValue());
            }
            else
            {
                System.out.println("No ticket selected!");
                JOptionPane.showMessageDialog(this,"No ticket selected","",JOptionPane.WARNING_MESSAGE);
            }
        });
    }


    public void editButtonActionListener()
    {
        this.editButton.addActionListener(listener ->
        {
            if (!this.ticketJList.isSelectionEmpty())
            {
                ChangeTicketFrame frame = new ChangeTicketFrame(this.ticketJList.getSelectedValue());
                frame.initialize();
            }
            else
            {
                System.out.println("No ticket selected!");
                JOptionPane.showMessageDialog(this,"No ticket selected","",JOptionPane.WARNING_MESSAGE);
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
