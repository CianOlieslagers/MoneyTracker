package GUI.calculate.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import database.PersonDB;
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
    private JButton back;
    private JFrame frame;

    public CalculatePanel(PersonController personController, TicketController ticketController, JFrame frame)
    {
        this.personController = personController;
        this.ticketController = ticketController;
        this.frame = frame;

        this.selectBox = new JComboBox<>(personController.getNames().toArray());
        this.selectLabel = new JLabel("Select a person:");
        this.back = new JButton("Back");

        selecBoxActionListener();
        backButtonActionListener();

        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);

        this.setLayout(layout);

        this.billListModel = new DefaultListModel<>();
        this.billJList = new JList<>(billListModel);

        this.add(this.selectLabel);
        this.add(this.selectBox);
        this.add(this.billJList);
        this.add(this.back);

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


    public void backButtonActionListener()
    {
        this.back.addActionListener(listener ->
        {
            this.frame.dispose();
        });
    }


}
