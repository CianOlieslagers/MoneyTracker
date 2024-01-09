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
    private JButton back;
    private JFrame frame;

    public ShowTicketPanel(PersonController personController, TicketController ticketController, JFrame frame)
    {

        this.personController = personController;
        this.ticketController = ticketController;
        this.frame = frame;

        this.ticketLabel = new JLabel("Tickets");
        this.deleteButton = new JButton("Delete selected ticket");
        this.editButton = new JButton("Edit selected ticket");
        this.ticketListModel = new DefaultListModel<>();
        this.back = new JButton("Back");

        for (Ticket ticket : ticketController.getTickets())
        {
            ticketListModel.addElement(ticket);
        }


        this.ticketJList = new JList<>(ticketListModel);

        deleteButtonActionListener();
        editButtonActionListener();
        backButtonActionListener();

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
                                .addComponent(this.ticketJList)
                                .addComponent(this.back))
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.back))
        );

    }


    public void deleteButtonActionListener()
    {
        this.deleteButton.addActionListener(listener ->
        {
            if (!this.ticketJList.isSelectionEmpty())
            {
                this.ticketController.removeTicket(this.ticketJList.getSelectedValue());
            }
            else
            {
                JOptionPane.showMessageDialog(this,"No ticket selected","",JOptionPane.WARNING_MESSAGE);
            }
        });
    }


    public void backButtonActionListener()
    {
        this.back.addActionListener(listener ->
        {
            this.frame.dispose();
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
        if (evt.getPropertyName().equals("TicketDB add"))
        {
            Ticket ticket = (Ticket) evt.getNewValue();
            this.ticketListModel.addElement(ticket);
        }
    }
}
