import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
   
    private List<Person> _persons;

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

    public static void AgeComparison (String personA, String personB)
    {
    }

    public static void GenderCount(String gender)
    {
    }

    public static void main(String[] args) {
        
        if (args.length != 1)
            System.out.println("Usage: java AddressBook <filename>");

        String filePath = args[0];
        
        AddressBook addressBook = new AddressBook(filePath);

        
    }
}
