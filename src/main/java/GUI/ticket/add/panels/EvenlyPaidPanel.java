package GUI.ticket.add.panels;

import controller.person.PersonController;
import controller.ticket.TicketController;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EvenlyPaidPanel extends JPanel
{
    PersonController personController;
    TicketController ticketController;

    private JLabel nameLabel;
    private JTextField amountPerPersonField;
    private JCheckBox checkBox;
    private double amountPerson;

    private HashMap<Person, JCheckBox> personJCheckBoxHashMap;
    private HashMap<Person, JTextField> personJTextFieldHashMap;


    public EvenlyPaidPanel(PersonController personController, TicketController ticketController)
    {
        this.personController = personController;
        this.ticketController = ticketController;

        this.personJCheckBoxHashMap = new HashMap<>();
        this.personJTextFieldHashMap = new HashMap<>();

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

            this.personJTextFieldHashMap.put(p, this.amountPerPersonField);
            this.personJCheckBoxHashMap.put(p, this.checkBox);
        }
    }


    public void setAmountPerPersonField(boolean bool) {
        for (Map.Entry<Person, JTextField> e : this.personJTextFieldHashMap.entrySet())
        {
            JTextField textField = e.getValue();
            textField.setVisible(bool);
        }
    }


    public HashMap<Person,Double> getInformation(double totalAmount, boolean splitEvenly) throws Exception {
        HashMap<Person,Double> result = new HashMap<>();
        int totalPersons = getTotalPersons();

        for (Map.Entry<Person,JCheckBox> e : this.personJCheckBoxHashMap.entrySet())
        {
            if (e.getValue().isSelected())
            {
                if (splitEvenly)
                {
                    double amountPerson = totalAmount/totalPersons;
                    result.put(e.getKey(), amountPerson);
                }
                else
                {
                    if (!this.personJTextFieldHashMap.get(e.getKey()).getText().isEmpty())
                    {
                        try
                        {
                             this.amountPerson = Double.parseDouble(this.personJTextFieldHashMap.get(e.getKey()).getText());
                        }
                        catch (NumberFormatException nfe)
                        {
                            throw new NumberFormatException("Give a valid value for amount! e.g. '10.45'");
                        }
                        result.put(e.getKey(), amountPerson);
                    }
                    else
                    {
                        throw new Exception("Fill in an amount for every selected person!");
                    }
                }
            }
        }
        return result;
    }


    private int getTotalPersons() throws Exception
    {
        int totalPersons = 0;

        for (Map.Entry<Person, JCheckBox> e : this.personJCheckBoxHashMap.entrySet())
        {
            if (e.getValue().isSelected())
            {
                totalPersons++;
            }
        }

        if (totalPersons == 0)
            throw new Exception("Select at least one person!");

        return totalPersons;
    }


    public void resetPanel()
    {
        for (Map.Entry<Person,JCheckBox> e : this.personJCheckBoxHashMap.entrySet())
        {
            JCheckBox checkBox = e.getValue();
            checkBox.setSelected(false);
        }
        for (Map.Entry<Person,JTextField> e : this.personJTextFieldHashMap.entrySet())
        {
            JTextField textField = e.getValue();
            textField.setText("");
            e.getValue().setVisible(false);
        }
    }
}
