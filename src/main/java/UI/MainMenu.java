package UI;

import api.HotelResource;

import java.util.Scanner;

public class MainMenu {

    // HotelResource hotelResource = HotelResource.getService();
    private static final AdminMenu adminMenu = AdminMenu.getAdminMenu();


    private static MainMenu mainMenu = null;

    public static MainMenu getMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    public void displayMainMenu(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        while(!option.equals("5")) {
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println();
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
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    adminMenu.displayAdminMenu();
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
}
