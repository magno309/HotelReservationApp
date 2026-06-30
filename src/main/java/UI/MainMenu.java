package UI;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getService();
    private static final AdminMenu adminMenu = AdminMenu.getAdminMenu();

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private static MainMenu mainMenu = null;

    public static MainMenu getMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    public void displayMainMenu(Scanner sc) {
        String option = "";
        while (!option.equals("5")) {
            System.out.println("--------------------------------------------");
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println("--------------------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("--------------------------------------------");
            System.out.println("Please select an option for the menu option: ");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    boolean isValidDate = false;
                    List<IRoom> availableRooms = new ArrayList<>();
                    Date checkInDate = null;
                    Date checkOutDate = null;
                    do {
                        try {
                            System.out.println("Please enter the Check In Date mm/dd/yyyy example: 05/01/2020");
                            checkInDate = dateFormat.parse(sc.nextLine());
                            System.out.println("Please enter the Check Out Date mm/dd/yyyy example: 05/05/2020");
                            checkOutDate = dateFormat.parse(sc.nextLine());
                            if (checkInDate.after(checkOutDate)) {
                                System.out.println("Check In Date must be before Check Out Date. Please try again.");
                            } else {
                                isValidDate = true;
                                availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    } while (!isValidDate);

                    while (availableRooms.isEmpty()) {
                        System.out.println("No rooms available for the selected dates. Want to search for alternative dates? (y/n)");
                        String response = sc.nextLine();
                        if (response.equalsIgnoreCase("y")) {
                            checkInDate = new Date(checkInDate.getTime() + (1000 * 60 * 60 * 24 * 7)); // Add 7 days
                            checkOutDate = new Date(checkOutDate.getTime() + (1000 * 60 * 60 * 24 * 7)); // Add 7 days
                            availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                        } else if (response.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                    }

                    if (availableRooms.isEmpty()) {
                        break;
                    }

                    boolean isValidRoom = false;
                    IRoom selectedRoom = null;
                    do {
                        System.out.println("Available rooms:");
                        availableRooms.forEach(System.out::println);
                        System.out.println("Please enter the room number you want to reserve: ");
                        String roomNumber = sc.nextLine();
                        selectedRoom = hotelResource.getRoom(roomNumber);
                        if (selectedRoom == null) {
                            System.out.println("Invalid room number. Please try again.");
                        } else {
                            isValidRoom = true;
                        }
                    } while (!isValidRoom);

                    boolean isValidEmail = false;
                    do {
                        System.out.println("Please enter your email to book the room: ");
                        String customerEmail = sc.nextLine();
                        isValidEmail = validateEmail(customerEmail);
                        if (isValidEmail) {
                            hotelResource.bookARoom(customerEmail, selectedRoom, checkInDate, checkOutDate);
                            System.out.println("Room booked successfully!");

                        }
                    } while (!isValidEmail);
                    break;
                case "2":
                    boolean isEmailValid = false;
                    do {
                        System.out.println("Please enter your email to see your reservations: ");
                        String customerEmail = sc.nextLine();
                        isEmailValid = validateEmail(customerEmail);
                        if (isEmailValid) {
                            List<Reservation> reservations = hotelResource.getCustomerReservations(customerEmail);
                            if (reservations.isEmpty()) {
                                System.out.println("No reservations found for this email.");
                            } else {
                                reservations.forEach(System.out::println);
                            }
                        }
                    } while (!isEmailValid);
                    break;
                case "3":
                    System.out.println("Please enter your email: ");
                    String email = sc.nextLine();
                    System.out.println("Please enter your first name: ");
                    String firstName = sc.nextLine();
                    System.out.println("Please enter your last name: ");
                    String lastName = sc.nextLine();
                    hotelResource.createACustomer(email, firstName, lastName);
                    break;
                case "4":
                    adminMenu.displayAdminMenu(sc);
                    break;
                case "5":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Option not valid!");
                    break;
            }
        }
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty. Please try again.");
            return false;
        } else if (hotelResource.getCustomer(email) == null) {
            System.out.println("Customer does not exist. Please create an account first.");
            return false;
        }
        return true;

    }
}
