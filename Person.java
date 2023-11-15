public class Person
{
    private String _name;
    private String _gender;
    private String _dateOfBirth;

    public Person(String name, String gender, String dateOfBirth)
    {
        this._name = name;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;
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
}