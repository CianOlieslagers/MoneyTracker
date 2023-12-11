package GUI.ticket.add.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EvenlyPaidPanel extends JPanel
{

    PersonController personController;
    TicketController ticketController;

    private JLabel nameLabel;
    private JTextField amountPerPersonField;
    private JCheckBox checkBox;

    private ArrayList<JTextField> textFieldArrayList;


    public EvenlyPaidPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.textFieldArrayList = new ArrayList<>();

        GridLayout layout = new GridLayout(personController.getPersons().size(),3);
        this.setLayout(layout);

        for (Person p : personController.getPersons())
        {
            this.checkBox = new JCheckBox();
            this.nameLabel = new JLabel(p.getName());
            this.amountPerPersonField = new JTextField(8);
            this.add(checkBox);
            this.add(nameLabel);
            this.add(amountPerPersonField);
            this.amountPerPersonField.setVisible(false);

            this.textFieldArrayList.add(this.amountPerPersonField);
        }

    }

    public void setAmountPerPersonField(boolean bool) {
        for (JTextField jTextField : this.textFieldArrayList)
            jTextField.setVisible(bool);
    }
}
