import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AddressBook {
   
    private ArrayList<Person> _persons;

    public AddressBook (String filePath)
    {
        _persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");
                if (columns.length == 3) {
                    _persons.add(new Person(columns[0], columns[1], columns[2]));
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: File not found.");
        }
    }

    public long AgeComparison (String personA, String personB)
    {
        LocalDate   dateA = LocalDate.now();
        LocalDate   dateB = LocalDate.now();
        Boolean     foundA = false;
        Boolean     foundB = false;

        if (personA == null || personA.isEmpty())
            return -1;
        if (personB == null || personA.isEmpty())
            return -1;
        for (Person person : _persons)
        {
            if (person.getName() == personA)
            {
                dateA = person.getLocalBirthDate();
                foundA = true;
            }
            if (person.getName() == personB)
            {
                dateB = person.getLocalBirthDate();
                foundB = true;
            }
            if (foundA && foundB)
            {
                break ;
            }
        }
        if (!foundA)
            System.err.println(personA + "doesn't exist in this address book");
        if (!foundB)
            System.err.println(personA + "doesn't exist in this address book");
        return ChronoUnit.DAYS.between(dateA, dateB);
    }

    public int GenderCount (String gender)
    {
        int count = 0;

        if (gender == null || gender.isEmpty())
            return -1;
        for (Person person : _persons)
        {
            if (person.getGender() == gender)
                count++;
        }
        return count;
    }

    public Person GetOldestPerson ()
    {
        Person oldest = _persons.get(0);

        for (Person person : _persons)
        {
            if (person.getLocalBirthDate().isBefore(oldest.getLocalBirthDate()))
            {
                oldest = person;
            }
        }
        return oldest;
    }

    public void main(String[] args) {
        
        if (args.length != 1)
            System.out.println("Usage: java AddressBook <filename>");

        String filePath = args[0];
        
        AddressBook addressBook = new AddressBook(filePath);

        int howManyMales = addressBook.GenderCount("male");
        Person oldestPerson = addressBook.GetOldestPerson();
        long comparisonBillPaul = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

        System.out.println(howManyMales);
        System.out.println(oldestPerson);
        System.out.println(comparisonBillPaul);
    }
}
