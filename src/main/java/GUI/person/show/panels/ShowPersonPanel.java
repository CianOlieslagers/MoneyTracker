package GUI.person.show.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;

public class ShowPersonPanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;

    private JLabel nameLabel;
    private JLabel accountnumberLabel;

    private JList<String> namesJList;
    private DefaultListModel<String> namesListModel;
    private JList<String> accountJList;
    private DefaultListModel<String> accountListModel;


    public ShowPersonPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

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


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(this.namesJList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.accountnumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(this.accountJList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.nameLabel)
                                .addComponent(this.accountnumberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.namesJList)
                                .addComponent(this.accountJList))
        );

    }


}
