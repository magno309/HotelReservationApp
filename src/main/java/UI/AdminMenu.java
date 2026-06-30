package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import api.AdminResource;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;

public class AdminMenu {

    private static AdminMenu adminMenu = null;
    private static final AdminResource adminResource = AdminResource.getService();

    public static AdminMenu getAdminMenu() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void displayAdminMenu(Scanner sc) {
        String option = "";
        while (!option.equals("6")) {
            System.out.println("Admin Menu");
            System.out.println("--------------------------------------------");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Add test data");
            System.out.println("6. Back to main menu");
            System.out.println("--------------------------------------------");
            System.out.println("Please select an option for the menu option (Admin): ");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    adminResource.getAllCustomers().forEach(System.out::println);
                    break;
                case "2":
                    adminResource.getAllRooms().forEach(System.out::println);
                    break;
                case "3":
                    adminResource.displayAllReservations();
                    break;
                case "4":
                    List<IRoom> rooms = new ArrayList<>();
                    do {
                        System.out.println("Please enter room number: ");
                        String roomNumber = sc.nextLine();
                        System.out.println("Please enter room price: ");
                        double price = -1;
                        do {
                            try {
                                price = Double.parseDouble(sc.nextLine());
                                if (price < 0) {
                                    System.out.println("Price cannot be negative! Please enter a valid price: ");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input! Please enter a valid price: ");
                            }
                        } while (price < 0);
                        RoomType roomType = null;
                        do {
                            System.out.println("Please enter room type (SINGLE/DOUBLE): ");
                            try {
                                roomType = RoomType.valueOf(sc.nextLine().toUpperCase());
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid room type! Please enter a valid room type.");
                            }
                        } while (true);

                        if (price == 0) {
                            rooms.add(new FreeRoom(roomNumber, roomType));
                        } else {
                            rooms.add(new Room(roomNumber, price, roomType));
                        }

                        System.out.println("Do you want to add another room? (y/n): ");
                    } while (sc.nextLine().equalsIgnoreCase("y"));
                    adminResource.addRoom(rooms);
                    break;
                case "5":
                    System.out.println("Adding test data...");
                    adminResource.addRoom(List.of(
                            new Room("101", 100.0, RoomType.SINGLE),
                            new Room("102", 150.0, RoomType.DOUBLE)));
                    adminResource.createACustomer("test@example.com", "Test", "User");
                    System.out.println("Test data added successfully!");
                    break;
                case "6":
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Option not valid!");
                    break;
            }
        }
    }
}
