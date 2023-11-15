import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {
    @Test
    void OldestPerson() {
        AddressBook addressBook = new AddressBook("files/AddressBook.txt");
        Person oldestPerson = addressBook.OldestPerson();

        assertEquals("Wes Jackson", oldestPerson.getName());
    }

    @Test
    void NullGenderCount() {
        AddressBook addressBook = new AddressBook("files/AddressBook.txt");
        int GenderCount = addressBook.GenderCount(null);

        assertEquals(-1, GenderCount);
    }

    @Test
    void FemaleCount() {
        AddressBook addressBook = new AddressBook("files/AddressBook.txt");
        int GenderCount = addressBook.GenderCount("Female");

        assertEquals(2, GenderCount);
    }

    @Test
    void AgeComparison() {
        AddressBook addressBook = new AddressBook("files/AddressBook.txt");
        long AgeComparison = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

        assertEquals(2862, AgeComparison);
    }
}
