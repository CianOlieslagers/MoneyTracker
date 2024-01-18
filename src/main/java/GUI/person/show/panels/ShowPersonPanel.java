package GUI.person.show.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowPersonPanel extends JPanel implements PropertyChangeListener
{
    PersonController personController;
    TicketController ticketController;

    private JFrame frame;

    private JLabel nameLabel;
    private JLabel accountnumberLabel;
    private JButton deleteButton;
    private JButton back;

    private JList<String> namesJList;
    private DefaultListModel<String> namesListModel;
    private JList<String> accountJList;
    private DefaultListModel<String> accountListModel;


    public ShowPersonPanel(PersonController personController, TicketController ticketController, JFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.frame = frame;
        this.deleteButton = new JButton("Delete selected person");
        this.nameLabel = new JLabel("Name");
        this.accountnumberLabel = new JLabel("Account number");
        this.back = new JButton("Back");

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
        backButtonActionListener();


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.namesJList)
                                .addComponent(this.back))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.accountnumberLabel)
                                .addComponent(this.accountJList)
                                .addComponent(this.deleteButton))
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
                                .addComponent(this.deleteButton)
                                .addComponent(this.back))
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
        if (evt.getPropertyName().equals("PersonDB remove"))
        {
            Person person = (Person) evt.getNewValue();
            this.namesListModel.removeElement(person.getName());
            this.accountListModel.removeElement(person.getAccountNumber());
        }
    }

}
