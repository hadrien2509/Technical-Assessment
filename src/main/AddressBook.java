import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AddressBook {
   
    private ArrayList<Person> _persons;

    public ArrayList<Person> getPersons()
    {
        return this._persons;
    }

    public AddressBook (String filePath)
    {
        _persons = new ArrayList<Person>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null) { // Read the file line by line
                String[] columns = line.split(", ");
                if (columns.length == 3) { // If the line contains 3 columns, add a new person to the list
                    _persons.add(new Person(columns[0], columns[1], columns[2])); // Add a new person to the list
                }
            }
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        if (_persons.isEmpty())
            throw new IllegalArgumentException("File doesn't contain any person's informations.");
    }

    public long AgeComparison (String personA, String personB)
    {
        LocalDate   dateA = LocalDate.now();
        LocalDate   dateB = LocalDate.now();
        Boolean     foundA = false;
        Boolean     foundB = false;

        if (personA == null)
            throw new NullPointerException("First argument of AgeComparison can't be null");
        if (personB == null)
            throw new NullPointerException("Second argument of AgeComparison can't be null");
        if (personA.isEmpty())
            throw new IllegalArgumentException("First argument of AgeComparison can't be empty");
        if (personB.isEmpty())
            throw new IllegalArgumentException("Second argument of AgeComparison can't be empty");
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
            if (foundA && foundB) // If both persons are found, break the loop in order to save time
            {
                break ;
            }
        }
        if (!foundA && !foundB)
            throw new IllegalArgumentException(personA + " and " + personB + " don't exist in this address book");
        if (!foundA)
            throw new IllegalArgumentException(personA + " doesn't exist in this address book");
        if (!foundB)
            throw new IllegalArgumentException(personB + " doesn't exist in this address book");
        return ChronoUnit.DAYS.between(dateA, dateB); // Return the number of days between the two dates
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
                oldests.clear(); // Clear the list of previous oldest person(s) and add the new oldest person
                oldests.add(person);
            }
            else if (person.getLocalBirthDate().isEqual(oldest.getLocalBirthDate()))
            {
                oldests.add(person);
            }
        }
        return oldests;
    }

    public ArrayList<Person> YoungestPerson ()
    {
        if (_persons.isEmpty())
            return null;
        Person youngest = _persons.get(0);
        ArrayList<Person> youngests = new ArrayList<Person>();

        for (Person person : _persons)
        {
            if (person.getLocalBirthDate().isAfter(youngest.getLocalBirthDate()))
            {
                youngest = person;
                youngests.clear(); // Clear the list of previous youngest person(s) and add the new youngest person
                youngests.add(person);
            }
            else if (person.getLocalBirthDate().isEqual(youngest.getLocalBirthDate()))
            {
                youngests.add(person);
            }
        }
        return youngests;
    }
}