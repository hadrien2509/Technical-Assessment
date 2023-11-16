import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.format.DateTimeParseException;

public class AddressBookTest {

    /*** OldestPerson() tests ***/

    @Test
    void OldestPersons() {
        AddressBook addressBook = new AddressBook("ressources/Test.txt");
        Person oldestPerson0 = addressBook.OldestPerson().get(0);
        Person oldestPerson1 = addressBook.OldestPerson().get(1);

        assertEquals("Hadrien Geissler", oldestPerson0.getName());
        assertEquals("Wes Jackson", oldestPerson1.getName());
    }

    @Test
    void NullGenderCount() {
        AddressBook addressBook = new AddressBook("ressources/Test.txt");
        int GenderCount = addressBook.GenderCount(null);

        assertEquals(0, GenderCount);
    }

    /*** GenderCount() tests ***/

    @Test
    void GenderCount() {
        AddressBook addressBook1 = new AddressBook("ressources/AddressBook.txt");
        int femaleCount1 = addressBook1.GenderCount("Female");
        int maleCount1 = addressBook1.GenderCount("Male");

        assertEquals(2, femaleCount1);
        assertEquals(3, maleCount1);

        AddressBook addressBook2 = new AddressBook("ressources/Test.txt");
        int femaleCount2 = addressBook2.GenderCount("Female");
        int maleCount2 = addressBook2.GenderCount("Male");

        assertEquals(2, femaleCount2);
        assertEquals(8, maleCount2);

        AddressBook addressBook3 = new AddressBook("ressources/NoMale.txt");
        int femaleCount3 = addressBook3.GenderCount("Female");
        int maleCount3 = addressBook3.GenderCount("Male");

        assertEquals(5, femaleCount3);
        assertEquals(0, maleCount3);
    }

    /*** Age and Oldest/YoungestPerson() tests ***/

    @Test
    void AgeAndYoungestTest () {
        AddressBook addressBook = new AddressBook("ressources/Age.txt");
        assertEquals(46, addressBook.getPersons().get(0).getAge());
        assertEquals(38, addressBook.getPersons().get(1).getAge());
        assertEquals(31, addressBook.getPersons().get(2).getAge());
        assertEquals(43, addressBook.getPersons().get(3).getAge());
        assertEquals(49, addressBook.getPersons().get(4).getAge());
        assertEquals(123, addressBook.getPersons().get(5).getAge());
        assertEquals(23, addressBook.getPersons().get(6).getAge());
        assertEquals(23, addressBook.getPersons().get(7).getAge());
        assertEquals("Charles David", addressBook.OldestPerson().get(0).getName()); //Checking boundary values
        assertEquals("Lea David", addressBook.YoungestPerson().get(0).getName()); //Checking boundary values
        assertEquals("Jack Black", addressBook.YoungestPerson().get(1).getName()); //Checking boundary values
    }

    /*** AgeComparison() tests ***/

    @Test
    void AgeComparison() {
        AddressBook addressBook = new AddressBook("ressources/Age.txt");
        long AgeComparison1 = addressBook.AgeComparison("Lea David", "Jack Black");
        long AgeComparison2 = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

        assertEquals(0, AgeComparison1);
        assertEquals(2862, AgeComparison2);
    }

    //For a leap year testing difference between 28th February and 1st March
    @Test
    void AgeComparisonLeapYear() {
        AddressBook addressBook = new AddressBook("ressources/Test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Leap", "Elia Leap");

        assertEquals(2, AgeComparison);
    }

    //For a non leap year testing difference between 28th February and 1st March
    @Test
    void AgeComparisonNoLeapYear() {
        AddressBook addressBook = new AddressBook("ressources/Test.txt");
        long AgeComparison = addressBook.AgeComparison("Eliott Noleap", "Elia Noleap");

        assertEquals(1, AgeComparison);
    }

    /*** AgeComparison() invalid arguments tests ***/ 
    @Test
    void AgeComparisonIllegalArgument() {
        AddressBook addressBook = new AddressBook("ressources/Test.txt");

        assertThrows(NullPointerException.class, () -> {
            addressBook.AgeComparison(null, "Paul Robinson");
        });

        assertThrows(NullPointerException.class, () -> {
            addressBook.AgeComparison("Bill McKnight", null);
        });

        assertThrows(NullPointerException.class, () -> {
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

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("Billy McKnight", "Bill McKnight");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            addressBook.AgeComparison("Bill McKnight", "Billy McKnight");
        });
    }

    /*** Invalid date tests ***/

    @Test
    void DateTimeParseException1() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DateTimeParseException1.txt");

            addressBook.OldestPerson();
        });
    }

    @Test
    void DateTimeParseException2() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DateTimeParseException2.txt");

            addressBook.OldestPerson();
        });
    }

    @Test
    void DateTimeParseException3() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DateTimeParseException3.txt");

            addressBook.OldestPerson();
        });
    }

    //Testing 29th February for Bill McKnight (not leap year)
    @Test
    void DateTimeParseException4() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DateTimeParseException4.txt");

            addressBook.OldestPerson();
        });
    }

    //Testing 31th September for Bill McKnight
    @Test
    void DateTimeParseException5() {
        
        assertThrows(DateTimeParseException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DateTimeParseException4.txt");

            addressBook.OldestPerson();
        });
    }

    /*** Invalid file format tests ***/

    //Testing if it still works if the file contains lines that don't have 3 columns
    @Test
    void CrappyFileFormat() {
        
        AddressBook addressBook = new AddressBook("ressources/CrappyFormat.txt");
        Person oldestPerson = addressBook.OldestPerson().get(0);

        assertEquals("Wes Jackson", oldestPerson.getName());
    }

    @Test
    void EmptyColumns() {

        assertThrows(IllegalArgumentException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/EmptyColumns.txt");

            addressBook.OldestPerson();
        });
    }

    /*** Invalid file path tests ***/

    @Test
    void FileNotFoundException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/DoesNotExist.txt");

            addressBook.OldestPerson();
        });
    }

    @Test
    void FileNotReadableException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            AddressBook addressBook = new AddressBook("ressources/NotReadable.txt"); //change file permissions to 000 before running this test

            addressBook.OldestPerson();
        });
    }
}
