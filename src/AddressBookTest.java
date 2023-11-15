import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {
    @Test
    void OldestPerson() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        Person oldestPerson = addressBook.OldestPerson();

        assertEquals("Wes Jackson", oldestPerson.getName());
    }

    @Test
    void NullGenderCount() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        int GenderCount = addressBook.GenderCount(null);

        assertEquals(-1, GenderCount);
    }

    @Test
    void FemaleCount() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        int GenderCount = addressBook.GenderCount("Female");

        assertEquals(2, GenderCount);
    }

    @Test
    void AgeComparison() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        long AgeComparison = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

        assertEquals(2862, AgeComparison);
    }

    //For a leap year
    @Test
    void AgeComparisonLeapYear() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Leap", "Elia Leap");

        assertEquals(2, AgeComparison);
    }

    //For not a leap year
    @Test
    void AgeComparisonNoLeapYear() {
        AddressBook addressBook = new AddressBook("files/test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Noleap", "Elia Noleap");

        assertEquals(1, AgeComparison);
    }
}
