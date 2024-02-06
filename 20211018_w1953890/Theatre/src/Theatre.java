import java.io.File;//import to work with  files
import java.io.FileNotFoundException;//Exception for when a file cannot be found
import java.io.FileWriter;//import to write files
import java.io.IOException;//import to handle the errors
import java.util.ArrayList; //import the ArrayList class
import java.util.InputMismatchException;
import java.util.Scanner;//import to read input from various sources

public class Theatre{
    private static final int[][] row = new int[3][]; //2D array to hold the number of seats for each row
    private static final Scanner scanner = new Scanner(System.in); //Create a scanner object to read user input
    static ArrayList<Ticket> tickets = new ArrayList<>(); //Create an arraylist object


    public static void main(String[] args) {
        //Initialize the seating chart with the appropriate no. of seas in respective row
        row[0] = new int[12];
        row[1] = new int[16];
        row[2] = new int[20];
        System.out.println("\n************************************************");
        System.out.println("\n************************************************");
        System.out.println("         *  Welcome to the New Theatre *       ");
        System.out.println("************************************************");
        System.out.println("************************************************");

        // Ask user for input until they choose to quit
        while (true) {
            System.out.println("\n-----------------------------------------------\nPlease select an option: \n1. Buy a ticket\n2. Print seating area\n3.Cancel ticket\n4. List available seats\n5.Save to file\n6.Load from file\n7.Print ticket information and total price\n8.Sort tickets by price\n0.Quit\n---------------------------------------------");
            System.out.println("Enter option:");

            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    System.out.println("\n--------Thanks for using New Theatre-------\n");
                    break;
                }
                switch (choice) {
                    case 1:
                        buy_ticket(); //Buy a ticket
                        break;
                    case 2:
                        print_seating_area(); // Print the seating chart
                        break;
                    case 3:
                        cancel_ticket(); //Cancel ticket
                        break;
                    case 4:
                        show_available(); //List the vacant seats
                        break;
                    case 5:
                        save(); //Saving to the file
                        break;
                    case 6:
                        load(); //Loading from the file
                        break;
                    case 7:
                        show_tickets_info(); //Showing the ticket's information
                        break;
                    case 8:
                        sortTickets(tickets); //Sorting the tickets by the price
                        break;
                    default:
                        System.out.println("Invalid option selected.");
                }
                //Handling the error if user enters a string
            } catch(Exception e){
                System.out.println("Invalid input. Enter an integer");
                scanner.nextLine();
            }
        }
    }
    public static void buy_ticket() {
        System.out.println("----Buy ticket----");

        // Ask for row number
        int rows;
        while (true) {
            System.out.println("\nPlease enter the row number (1-3):");
            try {
                rows = scanner.nextInt();
                if (rows < 1 || rows > 3) {
                    System.out.println("Error:This Row number does not exist. Please select(1-3).");
                    continue; // Ask again for row number
                }
                break; // valid row number entered, exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }

        // Ask for seat number
        int seat;
        while (true) {
            System.out.println("Please enter the seat number (1-" + row[rows - 1].length + "):");
            try {
                seat = scanner.nextInt();
                if (seat < 1 || seat > row[rows - 1].length) {
                    System.out.println("Invalid seat number.");
                    continue; // Ask again for seat number
                }
                if (row[rows - 1][seat - 1] == 1) {
                    System.out.println("That seat is already taken.");
                    continue; // Ask again for seat number
                }
                break; // valid seat number entered, exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }

        scanner.nextLine(); // consume new line character
        // Ask for person information
        System.out.println("Please enter the following information for the person:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        String email="";
        //Validate the email address by checking the "@" sign and dot
        while (true){
            System.out.print("Email: ");
            email = scanner.nextLine();
            if(email.contains("@") && email.contains(".")){
                break;
            }else{
                System.out.println("Please enter a valid email address.");
            }
        }

        // Create new Person object
        Person person = new Person(name, surname, email);

        //Set ticket price based on the row number
        double price;
        if (rows==1){
            price=10.0;
        } else if (rows==2){
            price=20.0;
        } else {
            price=30;
        }

        // Create new Ticket object
        Ticket ticket = new Ticket(rows, seat, price, person);

        // Add ticket to the list of tickets
        tickets.add(ticket);

        row[rows - 1][seat - 1] = 1;
        System.out.println("Ticket purchased successfully for seat- Row "+ rows+" Seat " + seat);
    }
    public static void print_seating_area() {
        System.out.println("-------Seating chart-------");
        System.out.println("\n**************************");
        System.out.println("        * STAGE *");
        System.out.println("**************************");

        //Finding the maximum number of seats in any row
        int maxSeats = row[0].length;
        for (int rows = 1; rows < row.length; rows++) {
            if (row[rows].length > maxSeats) {
                maxSeats = row[rows].length;
            }
        }
        //Printing the row seats
        int halfMaxSeats = maxSeats / 2;
        for (int rows = 0; rows < row.length; rows++) {
            int halfSeats = row[rows].length / 2;
            int numSpaces = halfMaxSeats - halfSeats;

            //Print spaces to center the seats
            System.out.print(" ");
            for (int j = 0; j < numSpaces; j++) {
                System.out.print(" ");
            }

            //Print the first half of the row seats
            for (int seat= 0; seat < halfSeats; seat++) {
                if (row[rows][seat] == 1) {
                    System.out.print("X"); //Booked seat
                } else {
                    System.out.print("O"); //Vacant seat
                }
            }
            //Print the gap in the middle of the row seats
            System.out.print(" ");

            //Print the other half the row seats
            for (int seat= halfSeats; seat < row[rows].length; seat++) {
                if (row[rows][seat] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }

            }
            //Print the spaces to center the rows in other side
            for (int j = 0; j < numSpaces; j++) {
                System.out.print(" ");
            }
            if (rows != row.length - 1) {
                System.out.println(); // Add a space between each row
            }
        }
    }
    public static void cancel_ticket() {
        System.out.println("----Cancel Ticket----");
        //row and seat number initialize to 0
        int rows = 0;
        int seat = 0;
        //Loop to repeat until user enter the valid row and seat number
        while (true) {

            try {
                //getting the row and seat number from the user
                System.out.print("Enter row number: ");
                rows = scanner.nextInt();
                System.out.print("Enter seat number: ");
                seat = scanner.nextInt();
                //checking the row and seat numbers are within the range of the seats
                if (rows < 1 || rows > 3 || seat < 1 || seat > row[rows - 1].length) {
                    throw new Exception("Invalid row or seat number. Please try again\n");
                }
                //search for the ticket in the list of tickets
                boolean ticketFound = false;
                for (Ticket t : tickets) {
                    if (t.getRow() == rows && t.getSeat() == seat) {
                        //if the ticket is found, remove the ticket from the list of tickets and mark corresponding seat as available
                        tickets.remove(t);
                        row[rows - 1][seat - 1] = 0; // make seat available
                        System.out.println("Ticket at row " + rows + ", seat " + seat + " has been canceled.");
                        ticketFound = true;
                        break;
                    }
                }
                if (!ticketFound) {
                    //exception with a message if the ticket is not found
                    throw new Exception("Ticket not found at row " + rows + " seat " + seat);
                }
                //If everything is running successfully,break out of the loop
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer");
                scanner.nextLine(); // clear input buffer
            }
        }
    }
    public static void show_available() {
        System.out.println("Available seats:");
        //Loop through each row in the array
        for (int rows = 0; rows < row.length; rows++) {
            //Print  the row number
            System.out.print("Seats available in row " + (rows+1) + ": ");
            //Loop through each seat in the row
            for (int seat = 0; seat < row[rows].length; seat++) {
                //If the seat is vacant,print the seat number
                if (row[rows][seat] == 0) {
                    System.out.print((seat+1) + ", ");
                }
            }
            //Print a new line after each row
            System.out.println();
        }
    }
    public static void save() {
        try {
            FileWriter writer = new FileWriter("seats.txt");

            //Loop through the seat array
            for (int i = 0; i < row.length; i++) {

                //Creating a StringBuilder to build the row string
                StringBuilder rowBuilder = new StringBuilder("row" + (i+1) + ": ");

                //Loop through each seat in the current row
                for (int j = 0; j < row[i].length; j++) {

                    //If the seat is available (0), add it to the row string
                    if (row[i][j] == 0) {
                        rowBuilder.append(j+1).append(",");
                    }
                }
                String rowStr = rowBuilder.toString();
                // Remove the last comma from the row string
                if (rowStr.endsWith(",")) {
                    rowStr = rowStr.substring(0, rowStr.length() - 1);
                }
                //Write the row String to the file
                writer.write(rowStr + "\n");
            }

            //Close the FileWriter and print a success message
            writer.close();
            System.out.println("Seat data saved to file: seats.txt");

            //Handling the errors that may occur while writing to the file
        } catch (IOException e) {
            System.out.println("An error occurred while saving the seat data to file.");
            e.printStackTrace();
        }
    }
    public static void load(){
        try {
            //Creating a file object representing the input file
            File myfile = new File("seats.txt");
            //Create scanner object to read the file
            Scanner fileReader = new Scanner(myfile);
            //Read each line of the file and print it
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            //Close the scanner object
            fileReader.close();
        }
        //Handling the errors that may occur while reading the file
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void show_tickets_info() {
        System.out.println("----Tickets Info----");

        double total_price = 0.0;
        //Loop through each ticket in the tickets array
        for (Ticket ticket : tickets) {
            //Print the information about the ticket
            ticket.print();
            //Add the ticket price to the total price
            total_price += ticket.getPrice();
        }
        //Print the total price of all tickets
        System.out.printf("\nTotal price of all tickets: £%.2f\n", total_price);
    }
    public static ArrayList<Ticket> sortTickets(ArrayList<Ticket> tickets) {
        // Create a new list to hold the sorted tickets
        ArrayList<Ticket> sortedTickets = new ArrayList<>(tickets.size());

        // Copy the contents of the original list to the new list
        sortedTickets.addAll(tickets);

        // Selection sort on the new list to sort the tickets by price
        for (int unsortedIndex = 0; unsortedIndex < sortedTickets.size() - 1; unsortedIndex++) {
            //Find the index of the minimum ticket price in the unsorted position of the list
            int maxIndex = unsortedIndex;
            for (int j = unsortedIndex + 1; j < sortedTickets.size(); j++) {
                if (sortedTickets.get(j).getPrice() < sortedTickets.get(maxIndex).getPrice()) {
                    maxIndex = j;
                }
            }
            //If the price is not already in its correct position ,change it with the current position
            if (maxIndex != unsortedIndex) {
                Ticket temp = sortedTickets.get(unsortedIndex);
                sortedTickets.set(unsortedIndex, sortedTickets.get(maxIndex));
                sortedTickets.set(maxIndex, temp);
            }
        }

        // Print the sorted tickets
        System.out.println("----- Sorted Tickets (Cheapest First) -----");
        for (Ticket t : sortedTickets) {
            System.out.println(t.toString() + " Price: £" + t.getPrice());
        }

        // Return the sorted list of tickets
        return sortedTickets;
    }


}





