public class Ticket {
    private int row; //This holds the row number of the set
    private int seat; //This holds the seat number
    private double price; //This holds the price of a ticket
    private Person person; //This holds the person object associated with the ticket

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters for row, seat, price, and person

    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
    //Returns string representation of ticket
    public String toString() {
        return "Ticket at row " + row + ", seat " + seat;
    }
    //Print the details of the user and the ticket
    public void print() {
        System.out.println("\nPerson name: " + person.getName());
        System.out.println("Person surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
    }
}
