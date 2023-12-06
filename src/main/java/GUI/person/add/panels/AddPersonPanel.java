package GUI.person.add.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;

public class AddPersonPanel extends JPanel
{
    PersonController personController;
    TicketController ticketController;

    private JLabel nameLabel;
    private JLabel accountLabel;
    private JTextField nameField;
    private JTextField accountField;
    private JButton save;


    public AddPersonPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.nameLabel = new JLabel("Name:");
        this.accountLabel = new JLabel("Accountnumber:");
        this.nameField = new JTextField(10);
        this.accountField = new JTextField(15);
        this.save = new JButton("Save");


        saveButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.accountLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.nameField)
                                .addComponent(this.accountField)
                                .addComponent(this.save))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.nameLabel)
                                .addComponent(this.nameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.accountLabel)
                                .addComponent(this.accountField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.save))
        );

    }


    public void saveButtonActionListener()
    {
        this.save.addActionListener(listener ->
        {
            String name = this.nameField.getText();
            String accountNumber = this.accountField.getText();
            this.personController.addPerson(new Person(name,accountNumber));
        });
    }

}