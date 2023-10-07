public class Person {
    private String name;
    private String surname;
    private String email;

    //Constructor for creating a new Person object
    public Person(String name, String surname, String email) {
        //Set the variables based on the arguments passed in
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getters for name, surname, and email

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

}
