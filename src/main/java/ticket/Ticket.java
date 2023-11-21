package ticket;

import java.util.ArrayList;

public class Ticket
{
    private String name;
    private double amount;
    private Category category;
    private ArrayList<String> names;
    private Boolean splitEvenly;

    public Ticket(String name, double amount, Category category)
    {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
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

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }
}
