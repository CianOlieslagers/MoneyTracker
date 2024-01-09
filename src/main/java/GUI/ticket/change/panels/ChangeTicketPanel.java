package GUI.ticket.change.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import decorator.*;
import ticket.Category;
import ticket.Ticket;

import javax.swing.*;

public class ChangeTicketPanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;
    Ticket ticket;
    JFrame frame;

    Category[] categories = Category.values();

    private JComboBox categoryBox;
    private JLabel categoryLabel;
    private JButton saveButton;
    private JButton back;


    public ChangeTicketPanel(PersonController personController, TicketController ticketController, Ticket ticket, JFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.ticket = ticket;
        this.frame = frame;

        this.categoryBox = new JComboBox<>(categories);
        this.categoryLabel = new JLabel("Select a new category for the ticket:");
        this.saveButton = new JButton("Save");
        this.back = new JButton("Back");
        this.categoryBox.setSelectedItem(ticket.getCategory());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.categoryLabel)
                                .addComponent(this.back))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.categoryBox)
                                .addComponent(this.saveButton))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.categoryLabel)
                                .addComponent(this.categoryBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.saveButton)
                                .addComponent(this.back))
        );

        saveButtonActionListener();
        backButtonActionListener();

    }


    private void saveButtonActionListener()
    {
        this.saveButton.addActionListener(listener ->
        {
            try {
                TicketDecorator decorator = null;
                if (this.categoryBox.getSelectedItem().equals(Category.Activities)){
                    decorator = new ActivitiesTicketDecorator(ticket);
                }
                else if (this.categoryBox.getSelectedItem().equals(Category.Airplane)){
                    decorator = new AirplaneTicketDecorator(ticket);
                }
                else if (this.categoryBox.getSelectedItem().equals(Category.Drinks)){
                    decorator = new DrinksTicketDecorator(ticket);
                }
                else if (this.categoryBox.getSelectedItem().equals(Category.Food)){
                    decorator = new FoodTicketDecorator(ticket);
                }
                else if (this.categoryBox.getSelectedItem().equals(Category.Taxi)){
                    decorator = new TaxiTicketDecorator(ticket);
                }
                else if (this.categoryBox.getSelectedItem().equals(Category.Others)){
                    decorator = new OthersTicketDecorator(ticket);
                }
                if (decorator != null)
                    ticketController.setActivity(ticket, decorator);

            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this,"No decorator selected!","Warning",JOptionPane.WARNING_MESSAGE);
            }

            frame.dispose();
        });
    }


    public void backButtonActionListener()
    {
        this.back.addActionListener(listener ->
        {
            this.frame.dispose();
        });
    }


}
