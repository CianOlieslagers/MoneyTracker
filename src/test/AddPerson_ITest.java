import controller.person.PController;
import controller.person.PersonController;
import database.DatabasePersons;
import database.PersonDB;
import org.junit.Before;
import org.junit.Test;
import person.Person;

import static org.junit.Assert.*;


public class AddPerson_ITest
{

    private PController controllerPerson;
    private Person Ann,Ben,Charlie;


    public AddPerson_ITest()
    {

    }

    @Before
    public void initialize()
    {
        DatabasePersons dbPersons = PersonDB.getInstance();
        this.controllerPerson = new PersonController(dbPersons);


        this.Ann = new Person("Ann","11");
        this.Ben = new Person("Ben", "22");
        this.Charlie = new Person("Charlie", "33");


        try {
            controllerPerson.addPerson(Ann);
            controllerPerson.addPerson(Ben);
            controllerPerson.addPerson(Charlie);
        } catch (Exception e) {
            fail("Something wrong in the initialize");
        }
    }


    @Test
    public void testAddAndRemovePerson()
    {
        Person testPerson = new Person("John", "123456");

        // Add the person to the database
        try {
            controllerPerson.addPerson(testPerson);
        } catch (Exception e) {
            fail("Exception should not be thrown during addPerson");
        }

        // Check if the person was added successfully
        assertTrue(controllerPerson.getPersons().contains(testPerson));
        assertTrue(controllerPerson.getNames().contains("John"));

        // Try to add the same person again (this should throw an exception)
        try {
            controllerPerson.addPerson(testPerson);
            fail("Expected exception not thrown during duplicate addPerson");
        } catch (Exception e) {
            assertEquals("John is already in use!", e.getMessage());
        }

        // Remove the person from the database
        try {
            controllerPerson.removePerson(testPerson);
        } catch (Exception e) {
            fail("Exception should not be thrown during removePerson");
        }

        // Check if the person was removed successfully
        assertFalse(controllerPerson.getPersons().contains(testPerson));
        assertFalse(controllerPerson.getNames().contains("John"));

        // Attempt to remove the same person again (this should throw an exception)
        try {
            controllerPerson.removePerson(testPerson);
            fail("Expected exception not thrown during duplicate removePerson");
        } catch (Exception e) {
            assertEquals("John can not be removed!", e.getMessage());
        }

        // Check if the person was successfully returned by name
        assertEquals(Ann,controllerPerson.getPerson("Ann"));

        assertNotEquals(Ann,controllerPerson.getPerson("Ben"));
    }

}
