import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        if (args.length != 1)
            System.err.println("Usage: java AddressBook <filename>");

        String filePath = args[0];
        
        AddressBook addressBook = new AddressBook(filePath);

        int howManyMales = addressBook.GenderCount("male");
        long comparisonBillPaul = addressBook.AgeComparison("Bill McKnight", "Paul Robinson");
        ArrayList<Person> oldestPerson = addressBook.OldestPerson();

        System.out.println(howManyMales);
        for (Person person : oldestPerson)
        {
            System.out.println(person.getName());
        }
        System.out.println(comparisonBillPaul);
    }
}
