package GUI.person.add.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class AddPersonPanel extends JPanel implements PropertyChangeListener
{
    PersonController personController;
    TicketController ticketController;
    JFrame frame;

    private JLabel nameLabel;
    private JLabel accountLabel;
    private JTextField nameField;
    private JTextField accountField;
    private JButton save;
    private JButton back;


    public AddPersonPanel(PersonController personController, TicketController ticketController, JFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.frame = frame;

        this.nameLabel = new JLabel("Name:");
        this.accountLabel = new JLabel("Account number:");
        this.nameField = new JTextField(10);
        this.accountField = new JTextField(15);
        this.save = new JButton("Save");
        this.back = new JButton("Back");


        saveButtonActionListener();
        backButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.accountLabel)
                                .addComponent(this.back))
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
                                .addComponent(this.save)
                                .addComponent(this.back))
        );

    }


    public void saveButtonActionListener()
    {
        this.save.addActionListener(listener ->
        {
            if (!this.nameField.getText().isEmpty() && !this.accountField.getText().isEmpty())
            {
                String name = this.nameField.getText();
                String accountNumber = this.accountField.getText();
                try
                {
                    this.personController.addPerson(new Person(name,accountNumber));
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
                if (this.nameField.getText().isEmpty())
                    JOptionPane.showMessageDialog(this, "Name field is empty", "Warning", JOptionPane.WARNING_MESSAGE);
                else if (this.accountField.getText().isEmpty())
                    JOptionPane.showMessageDialog(this,"Accountnumber field is empty","Warning",JOptionPane.WARNING_MESSAGE);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        System.out.println("hoeveel");
        if (evt.getPropertyName().equals("PersonDB add"))
        {
            Person person = (Person) evt.getNewValue();
            JOptionPane.showMessageDialog(this,person.getName() + " is added!","Person added", JOptionPane.INFORMATION_MESSAGE);
            this.nameField.setText("");
            this.accountField.setText("");
            this.frame.dispose();
        }
    }
}
