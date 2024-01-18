package GUI.ticket.add.panels;

import GUI.ticket.add.AddTicketFrame;
import controller.person.PersonController;
import controller.ticket.TicketController;
import factory.TicketFactory;
import person.Person;
import ticket.Category;
import ticket.Ticket;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class AddTicketPanel extends JPanel implements PropertyChangeListener
{
    PersonController personController;
    TicketController ticketController;
    private final TicketFactory factory = new TicketFactory();


    Category[] categories = Category.values();

    private JComboBox categoryBox;
    private JLabel categoryLabel;
    private JButton save;
    private JLabel activityLabel;
    private JLabel payerLabel;
    private JLabel amountLabel;
    private JTextField activityField;
    private JComboBox payerBox;
    private JTextField amountField;
    private JLabel splitLabel;
    private JCheckBox checkBox;
    private AddTicketFrame frame;
    private double amount;
    private JButton back;


    public AddTicketPanel(PersonController personController, TicketController ticketController, AddTicketFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.categoryBox = new JComboBox(categories);
        this.save = new JButton("Save");
        this.activityLabel = new JLabel("Activity name:");
        this.payerLabel = new JLabel("Paid by:");
        this.amountLabel = new JLabel("Amount:");
        this.activityField = new JTextField();
        this.payerBox = new JComboBox(personController.getNames().toArray());
        this.amountField = new JTextField(String.valueOf(0.0));
        this.splitLabel = new JLabel("Split evenly:");
        this.checkBox = new JCheckBox((Icon) null, true);
        this.categoryLabel = new JLabel("Select category:");
        this.frame = frame;
        this.back = new JButton("Back");
        this.amountField.setToolTipText("in euro's");


        saveButtonActionListener();
        checkboxActionListener();
        backButtonActionListener();


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.activityLabel)
                                .addComponent(this.payerLabel)
                                .addComponent(this.amountLabel)
                                .addComponent(this.splitLabel)
                                .addComponent(this.categoryLabel)
                                .addComponent(this.back))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.activityField)
                                .addComponent(this.payerBox)
                                .addComponent(this.amountField)
                                .addComponent(this.checkBox)
                                .addComponent(this.categoryBox)
                                .addComponent(this.save))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.activityLabel)
                                .addComponent(this.activityField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.payerLabel)
                                .addComponent(this.payerBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.amountLabel)
                                .addComponent(this.amountField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.splitLabel)
                                .addComponent(this.checkBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.categoryLabel)
                                .addComponent(this.categoryBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.save)
                                .addComponent(this.back))
        );
    }


    public void saveButtonActionListener()
    {
        this.save.addActionListener(listener ->
        {
            String name = this.activityField.getText();
            String payer = (String) this.payerBox.getSelectedItem();
            Category category = (Category) this.categoryBox.getSelectedItem();
            boolean splitEvenly = this.checkBox.isSelected();
            boolean amountExists = false;


            try
            {
                this.amount = Double.parseDouble(this.amountField.getText());
                amountExists = true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this,"Give a valid value for amount! e.g. '10.45'","Warning",JOptionPane.WARNING_MESSAGE);
            }

            if (name.isEmpty() && amountExists)
            {
                JOptionPane.showMessageDialog(this,"Name of the activity is empty","Warning",JOptionPane.WARNING_MESSAGE);
            }

            if (!name.isEmpty() && amountExists && splitEvenly)
            {
                try
                {
                    HashMap<Person,Double> amountPerPerson = frame.getInformation(amount, splitEvenly);
                    ticketController.addTicket(factory.getTicket(name, payer, amount, category, splitEvenly, amountPerPerson));
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                }

            }

            if (!name.isEmpty() && amountExists && !splitEvenly)
            {
                try
                {
                    HashMap<Person,Double> amountPerPerson = frame.getInformation(amount, splitEvenly);
                    ticketController.addTicket(factory.getTicket(name, payer, amount, category, splitEvenly, amountPerPerson));
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }


    public void checkboxActionListener()
    {
        this.checkBox.addActionListener(listener ->
        {
            this.frame.setField(!this.checkBox.isSelected());
        });
    }


    public void backButtonActionListener()
    {
        this.back.addActionListener(listener ->
        {
            this.frame.dispose();
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        System.out.println("hoeveel");
        if (evt.getPropertyName().equals("TicketDB add"))
        {
            Ticket ticket = (Ticket) evt.getNewValue();
            JOptionPane.showMessageDialog(this,"Ticket: " + ticket.getName() + "(" + ticket.getAmount() + ") is added!","Ticket added",JOptionPane.INFORMATION_MESSAGE);
            this.activityField.setText("");
            this.amountField.setText("0.0");
            this.checkBox.setSelected(true);
            this.frame.resetEvenlyPaidPanel();
        }
    }
}
