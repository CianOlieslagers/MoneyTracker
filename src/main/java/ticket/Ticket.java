package ticket;

import person.Person;

import java.util.HashMap;

public class Ticket
{
    private String name;
    private String payer;
    private double amount;
    private Category category;
    private Boolean splitEvenly;
    private HashMap<Person,Double> amountPerPerson;

    public Ticket(String name, String payer, double amount, Category category, Boolean splitEvenly, HashMap<Person,Double> amountPerPerson)
    {
        this.name = name;
        this.payer = payer;
        this.amount = amount;
        this.category = category;
        this.splitEvenly = splitEvenly;
        this.amountPerPerson = amountPerPerson;
    }


    public String getPayer() {
        return payer;
    }

    private void setPayer(String payer) {
        this.payer = payer;
    }

    public Boolean getSplitEvenly() {
        return splitEvenly;
    }

    private void setSplitEvenly(Boolean splitEvenly) {
        this.splitEvenly = splitEvenly;
    }

    public HashMap<Person,Double> getAmountPerPerson() {
        return amountPerPerson;
    }

    private void setAmountPerPerson(HashMap<Person,Double> amountPerPerson) {
        this.amountPerPerson = amountPerPerson;
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public double getAmount()
    {
        return amount;
    }

    private void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }


    public String print()
    {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", payer='" + payer + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", splitEvenly=" + splitEvenly +
                ", amountPerPerson=" + amountPerPerson +
                '}';
    }


    @Override
    public String toString()
    {
        return (name + " (€" + amount + ") paid by " + payer + " in category " + category);
    }
}
