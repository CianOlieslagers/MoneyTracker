package GUI.ticket.add.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import factory.TicketFactory;
import person.Person;
import ticket.Category;

import javax.swing.*;
import java.util.HashMap;

public class AddTicketPanel extends JPanel
{
    PersonController personController;
    TicketController ticketController;
    private final TicketFactory factory = new TicketFactory();

    Category[] categories = Category.values();

    private JComboBox comboBox;
    private JLabel comboLabel;
    private JButton save;
    private JButton back;
    private JLabel activityLabel;
    private JLabel payerLabel;
    private JLabel amountLabel;
    private JTextField activityField;
    private JTextField payerField;
    private JTextField amountField;
    private JLabel splitLabel;
    private JCheckBox checkBox;


    public AddTicketPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.comboBox = new JComboBox(categories);
        this.save = new JButton("Save");
        this.back = new JButton("Back");
        this.activityLabel = new JLabel("Activity name:");
        this.payerLabel = new JLabel("Payer:");
        this.amountLabel = new JLabel("Amount:");
        this.activityField = new JTextField();
        this.payerField = new JTextField();
        this.amountField = new JTextField();
        this.splitLabel = new JLabel("Split evenly:");
        this.checkBox = new JCheckBox();
        this.comboLabel = new JLabel("Select category:");


        boxActionListener();
        saveButtonActionListener();


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
                                .addComponent(this.comboLabel)
                                .addComponent(this.back))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.activityField)
                                .addComponent(this.payerField)
                                .addComponent(this.amountField)
                                .addComponent(this.checkBox)
                                .addComponent(this.comboBox)
                                .addComponent(this.save))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.activityLabel)
                                .addComponent(this.activityField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.payerLabel)
                                .addComponent(this.payerField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.amountLabel)
                                .addComponent(this.amountField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.splitLabel)
                                .addComponent(this.checkBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.comboLabel)
                                .addComponent(this.comboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.back)
                                .addComponent(this.save))
        );



    }

    public void boxActionListener()
    {
        this.comboBox.addActionListener(listener ->
        {
            System.out.println(comboBox.getSelectedItem());
        });
    }

    public void saveButtonActionListener()
    {
        this.save.addActionListener(listener ->
        {
            System.out.println("save");
            String name = this.activityField.getText();
            String payer = this.payerField.getText();
            double amount = Double.parseDouble(this.amountField.getText());
            Category category = (Category) this.comboBox.getSelectedItem();
            Boolean splitEvenly = this.checkBox.isSelected();
            HashMap<Double, Person> amountPerPerson = new HashMap<>();
            amountPerPerson.put(10.0, new Person("Melanie","fd"));
            amountPerPerson.put(20.0, new Person("Mel","snel"));
            amountPerPerson.put(30.0, new Person("Bob","de bouwer"));

            System.out.println(factory.getTicket(name, payer, amount, category, splitEvenly, amountPerPerson));

        });
    }


}
