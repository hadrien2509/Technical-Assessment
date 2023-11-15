import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.format.DateTimeParseException;

public class AddressBookTest {

    /*** Methods tests ***/

    @Test
    void OldestPerson() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        Person oldestPerson0 = addressBook.OldestPerson().get(0);
        Person oldestPerson1 = addressBook.OldestPerson().get(1);


        assertEquals("Hadrien Geissler", oldestPerson0.getName());
        assertEquals("Wes Jackson", oldestPerson1.getName());
    }

    @Test
    void NullGenderCount() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        int GenderCount = addressBook.GenderCount(null);

        assertEquals(0, GenderCount);
    }

    @Test
    void GenderCount() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        int femaleCount = addressBook.GenderCount("Female");
        int maleCount = addressBook.GenderCount("Male");

        assertEquals(2, femaleCount);
        assertEquals(8, maleCount);
    }

    @Test
    void AgeComparison() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        long AgeComparison = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

        assertEquals(2862, AgeComparison);
    }

    //For a leap year
    @Test
    void AgeComparisonLeapYear() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Leap", "Elia Leap");

        assertEquals(2, AgeComparison);
    }

    //For not a leap year
    @Test
    void AgeComparisonNoLeapYear() {
        AddressBook addressBook = new AddressBook("files/Test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Noleap", "Elia Noleap");

        assertEquals(1, AgeComparison);
    }

    @Test
    void AgeComparisonIllegalArgument() {
        AddressBook addressBook = new AddressBook("files/Test.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison(null, "Paul Robinson");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("Bill McKnight", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("", "Paul Robinson");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("Bill McKnight", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("", "");
        }); 
    }

    /*** Invalid file format tests ***/

    @Test
    void DateTimeParseException1() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("files/DateTimeParseException1.txt");

            addressBook.OldestPerson();
        });
    }

    @Test
    void DateTimeParseException2() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("files/DateTimeParseException2.txt");

            addressBook.OldestPerson();
        });
    }

    @Test
    void DateTimeParseException3() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("files/DateTimeParseException3.txt");

            addressBook.OldestPerson();
        });
    }

    //Testing 29th February for Bill McKnight (not leap year)
    @Test
    void DateTimeParseException4() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("files/DateTimeParseException4.txt");

            addressBook.OldestPerson();
        });
    }

    //Testing 31th September for Bill McKnight
    @Test
    void DateTimeParseException5() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("files/DateTimeParseException4.txt");

            addressBook.OldestPerson();
        });
    }

    //Testing if it still works if the file contains lines that don't have 3 columns
    @Test
    void CrappyFileFormat() {
        
        AddressBook addressBook = new AddressBook("files/CrappyFormat.txt");
        Person oldestPerson = addressBook.OldestPerson().get(0);

        assertEquals("Wes Jackson", oldestPerson.getName());
    }
}
