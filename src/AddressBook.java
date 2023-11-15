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
        _persons = new ArrayList<Person>();
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
            System.err.println("Error: File not found.");
        }
    }

    public long AgeComparison (String personA, String personB)
    {
        LocalDate   dateA = LocalDate.now();
        LocalDate   dateB = LocalDate.now();
        Boolean     foundA = false;
        Boolean     foundB = false;

        if (personA == null || personA.isEmpty())
            throw new IllegalArgumentException("First argument of AgeComparison is null or empty");
        if (personB == null || personB.isEmpty())
            throw new IllegalArgumentException("First argument of AgeComparison is null or empty");
        for (Person person : _persons)
        {
            if (person.getName().equals(personA))
            {
                dateA = person.getLocalBirthDate();
                foundA = true;
            }
            if (person.getName().equals(personB))
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
            return 0;
        if (_persons.isEmpty())
            return 0;
        for (Person person : _persons)
        {
            if (person.getGender().equals(gender))
                count++;
        }
        return count;
    }

    public ArrayList<Person> OldestPerson ()
    {
        if (_persons.isEmpty())
            return null;
        Person oldest = _persons.get(0);
        ArrayList<Person> oldests = new ArrayList<Person>();

        for (Person person : _persons)
        {
            if (person.getLocalBirthDate().isBefore(oldest.getLocalBirthDate()))
            {
                oldest = person;
                oldests.clear();
                oldests.add(person);
            }
            else if (person.getLocalBirthDate().isEqual(oldest.getLocalBirthDate()))
            {
                oldests.add(person);
            }
        }
        return oldests;
    }
}