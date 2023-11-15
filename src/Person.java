import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person
{
    private String      _name;
    private String      _gender;
    private String      _dateOfBirth;
    private LocalDate   _localBirthDate;

    public Person(String name, String gender, String dateOfBirth)
    {
        this._name = name;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;

        // Parse the dates of birth
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        this._localBirthDate = LocalDate.parse(dateOfBirth, formatter);
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
}