import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

public class Person
{
    private String      _name;
    private String      _gender;
    private String      _dateOfBirth;
    private LocalDate   _localBirthDate;
    private int         _age;

    public Person(String name, String gender, String dateOfBirth)
    {
        if (name.trim().isEmpty() || gender.trim().isEmpty() || dateOfBirth.trim().isEmpty()) // Check if the column(s) is/are empty
            throw new IllegalArgumentException("Missing person's information(s)"); // Throw an exception if it is the case

        this._name = name.trim();
        this._gender = gender.trim();
        this._dateOfBirth = dateOfBirth.trim();

        // Parse the dates of birth in a strict way to LocalDate type, throws an exception if the date is invalid

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .appendValueReduced(
                    ChronoField.YEAR, 2, 2, 1900) // 2 digit year, start from 1900
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);

        this._localBirthDate = LocalDate.parse(this._dateOfBirth, formatter);

        // Calculate the age of the person
        this._age = Period.between(this._localBirthDate, LocalDate.now()).getYears();
    }

    public String getName()
    {
        return this._name;
    }

    public String getGender()
    {
        return this._gender;
    }

    public String getDateOfBirth()
    {
        return this._dateOfBirth;
    }

    public LocalDate getLocalBirthDate ()
    {
        return this._localBirthDate;
    }

    public int getAge ()
    {
        return this._age;
    }
}