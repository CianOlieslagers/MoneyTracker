package GUI.calculate.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.util.HashMap;

public class CalculatePanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;

    private JComboBox selectBox;
    private JLabel amountPerson;

    public CalculatePanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.selectBox = new JComboBox<>(personController.getNames().toArray());
        this.amountPerson = new JLabel("Select a person");

        selecBoxActionListener();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.selectBox)
                                .addComponent(this.amountPerson))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.selectBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(this.amountPerson))
        );
    }


    public void selecBoxActionListener()
    {
        this.selectBox.addActionListener(listener ->
        {
            Person selectedPerson = this.personController.getPerson((String) this.selectBox.getSelectedItem());
            //this.amountPerson.setText(this.ticketController.getBillPerPerson(selectedPerson));

        });
    }


}
