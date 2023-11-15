public class Main {
    public static void main(String[] args) {
        
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
