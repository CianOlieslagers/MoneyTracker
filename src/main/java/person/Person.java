package person;

public class Person
{
    private String name;
    private String accountNumber;

    public Person(String name, String accountNumber)
    {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    private void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }


}
