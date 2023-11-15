import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        if (args.length != 1)
        {
            System.err.println("Usage: java AddressBook <filename>");
            return ;
        }

        String filePath = args[0];
        
        try
        {
            AddressBook addressBook = new AddressBook(filePath);
            if (addressBook.getPersons().isEmpty())
                return ;
            int howManyMales = addressBook.GenderCount("Male");
            long comparisonBillPaul = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");
            ArrayList<Person> oldestPerson = addressBook.OldestPerson();

            System.out.println("1. How many males are in the address book?" + "\n" + howManyMales);
            System.out.println("2. Who is the oldest person in the address book?");
            for (Person person : oldestPerson)
            {
                System.out.println(person.getName());
            }
            System.out.println("3. How many days older is Bill than Paul?" + "\n" + comparisonBillPaul);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
