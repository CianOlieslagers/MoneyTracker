package GUI.person.show.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.spec.ECField;

public class ShowPersonPanel extends JPanel implements PropertyChangeListener
{
    PersonController personController;
    TicketController ticketController;

    private JLabel nameLabel;
    private JLabel accountnumberLabel;
    private JButton deleteButton;

    private JList<String> namesJList;
    private DefaultListModel<String> namesListModel;
    private JList<String> accountJList;
    private DefaultListModel<String> accountListModel;


    public ShowPersonPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.deleteButton = new JButton("Delete selected person");
        this.nameLabel = new JLabel("Name");
        this.accountnumberLabel = new JLabel("Accountnumber");

        this.namesListModel = new DefaultListModel<>();
        this.accountListModel = new DefaultListModel<>();

        for (Person person : personController.getPersons())
        {
            namesListModel.addElement(person.getName());
            accountListModel.addElement(person.getAccountNumber());
        }

        this.namesJList = new JList<>(namesListModel);
        this.accountJList = new JList<>(accountListModel);

        this.namesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        deleteButtonActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.namesJList)
                                .addComponent(this.deleteButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.accountnumberLabel)
                                .addComponent(this.accountJList))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.accountnumberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.namesJList)
                                .addComponent(this.accountJList))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(deleteButton))
        );

    }


    public void deleteButtonActionListener()
    {
        this.deleteButton.addActionListener(listener ->
        {
            if (!this.namesJList.isSelectionEmpty())
            {
                Person deletedPerson = this.personController.getPerson(this.namesJList.getSelectedValue());

                try
                {
                    this.personController.removePerson(deletedPerson);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,"No person selected","Warning",JOptionPane.WARNING_MESSAGE);
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("PersonDB remove"))
        {
            Person person = (Person) evt.getNewValue();
            this.namesListModel.removeElement(person.getName());
            this.accountListModel.removeElement(person.getAccountNumber());
        }
    }

}
