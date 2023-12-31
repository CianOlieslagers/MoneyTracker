package GUI.ticket.change.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import decorator.*;
import ticket.Category;
import ticket.Ticket;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

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


    public ChangeTicketPanel(PersonController personController, TicketController ticketController, Ticket ticket, JFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.ticket = ticket;
        this.frame = frame;

        this.categoryBox = new JComboBox<>(categories);
        this.categoryLabel = new JLabel("Select a new category for the ticket:");
        this.saveButton = new JButton("Save");

        this.add(categoryLabel);
        this.add(categoryBox);
        this.add(saveButton);

        saveButtonActionListener();

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
                e.getMessage();
            }

            frame.dispose();
        });
    }


}
