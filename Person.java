import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person
{
    private String      _name;
    private String      _gender;
    private String      _dateOfBirth;
    private LocalDate   _localDate;

    public Person(String name, String gender, String dateOfBirth)
    {
        this._name = name;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;

        // Parse the dates of birth
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        this._localDate = LocalDate.parse(dateOfBirth, formatter);
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

    public LocalDate getLocalDate ()
    {
        return this._localDate;
    }
}