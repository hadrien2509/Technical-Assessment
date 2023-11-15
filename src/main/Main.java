import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        // Check if the correct number of arguments is provided
        if (args.length != 1)
        {
            System.err.println("Usage: java AddressBook <filename>");
            return;
        }

        // Store the file path from the command line argument
        String filePath = args[0];
        
        try
        {
            // Create an AddressBook object from the file
            AddressBook addressBook = new AddressBook(filePath);

            // Count the number of males in the address book
            int howManyMales = addressBook.GenderCount("Male");

            // Calculate the number of days difference in age between Bill and Paul
            long comparisonBillPaul = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");

            // Get the oldest person(s) from the address book
            ArrayList<Person> oldestPerson = addressBook.OldestPerson();

            // Print the number of males in the address book
            System.out.println("1. How many males are in the address book?\n\n" + howManyMales + "\n");

            // Print the name of the oldest person or persons
            System.out.println("2. Who is the oldest person in the address book?\n");
            for (Person person : oldestPerson)
            {
                System.out.println(person.getName());
            }
            // Print the age of the oldest person or persons
            if (oldestPerson.size() > 1)
                System.out.println("\nThey are " + oldestPerson.get(0).getAge() + " years old.\n");
            else
                System.out.println("\nHe is " + oldestPerson.get(0).getAge() + " years old.\n");

            // Print the number of days difference in age between Bill and Paul
            System.out.println("3. How many days older is Bill than Paul?\n\n" + comparisonBillPaul + "\n");
        }
        catch (Exception e)
        {
            // Print an error message if an exception is thrown
            System.err.println("Error: " + e.getMessage());
        }
    }
}

