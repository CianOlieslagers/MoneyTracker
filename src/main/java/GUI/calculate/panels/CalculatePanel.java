package GUI.calculate.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.util.ArrayList;

public class CalculatePanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;

    private JLabel selectLabel;
    private JComboBox selectBox;
    private JList<String> billJList;
    private DefaultListModel<String> billListModel;

    public CalculatePanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.selectBox = new JComboBox<>(personController.getNames().toArray());
        this.selectLabel = new JLabel("Select a person:");

        selecBoxActionListener();

        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);

        this.setLayout(layout);

        this.billListModel = new DefaultListModel<>();
        this.billJList = new JList<>(billListModel);

        this.add(this.selectLabel);
        this.add(this.selectBox);
        this.add(this.billJList);

    }


    public void selecBoxActionListener()
    {
        this.selectBox.addActionListener(listener ->
        {
            Person selectedPerson = this.personController.getPerson((String) this.selectBox.getSelectedItem());

            ArrayList<String> result = this.ticketController.getBillPerPerson(selectedPerson);

            billListModel.clear();
            for (String elem : result){
                billListModel.addElement(elem);
            }

        });
    }


}
