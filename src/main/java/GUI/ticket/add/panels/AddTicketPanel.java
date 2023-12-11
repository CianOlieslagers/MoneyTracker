package GUI.ticket.add.panels;

import GUI.ticket.add.AddTicketFrame;
import controller.person.PersonController;
import controller.ticket.TicketController;
import factory.TicketFactory;
import person.Person;
import ticket.Category;

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

    public AddTicketPanel(PersonController personController, TicketController ticketController, AddTicketFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.categoryBox = new JComboBox(categories);
        this.save = new JButton("Save");
        this.activityLabel = new JLabel("Activity name:");
        this.payerLabel = new JLabel("Payer:");
        this.amountLabel = new JLabel("Amount:");
        this.activityField = new JTextField();
        this.payerBox = new JComboBox(personController.getNames().toArray());
        this.amountField = new JTextField(String.valueOf(0.0));
        this.splitLabel = new JLabel("Split evenly:");
        this.checkBox = new JCheckBox((Icon) null, true);
        this.categoryLabel = new JLabel("Select category:");
        this.frame = frame;


        saveButtonActionListener();
        checkboxActionListener();


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
                                .addComponent(this.categoryLabel))
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
                                .addComponent(this.save))
        );


    }



    public void saveButtonActionListener()
    {
        this.save.addActionListener(listener ->
        {
            System.out.println("save");
            String name = this.activityField.getText();
            String payer = (String) this.payerBox.getSelectedItem();
            double amount = Double.parseDouble(this.amountField.getText());
            Category category = (Category) this.categoryBox.getSelectedItem();
            Boolean splitEvenly = this.checkBox.isSelected();
            HashMap<Double, Person> amountPerPerson = new HashMap<>();
            amountPerPerson.put(10.0, new Person("Melanie","fd"));
            amountPerPerson.put(20.0, new Person("Mel","snel"));
            amountPerPerson.put(30.0, new Person("Bob","de bouwer"));

            ticketController.addTicket(factory.getTicket(name, payer, amount, category, splitEvenly, amountPerPerson));

        });
    }

    public void checkboxActionListener()
    {
        this.checkBox.addActionListener(listener ->
        {
            System.out.println("checkbox action");
            if (this.checkBox.isSelected())
            {
                this.frame.setField(false);
            }
            else
            {
                this.frame.setField(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("TicketDB add"))
        {
            this.activityField.setText("");
            this.amountField.setText("");
            this.checkBox.setSelected(true);

        }
    }


}
