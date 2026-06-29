package UI;

import java.util.Scanner;

public class AdminMenu {

    private static AdminMenu adminMenu = null;
    private static final MainMenu mainMenu = MainMenu.getMainMenu();

    public static AdminMenu getAdminMenu() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void displayAdminMenu(){
        System.out.println("Admin Menu");
        System.out.println("--------------------------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Add test data");
        System.out.println("6. Back to main menu");
        System.out.println("--------------------------------------------");
        Scanner sc = new Scanner(System.in);
        String option = "";
        while(!option.equals("6")) {
            System.out.println("Please select an option for the menu option (Admin): ");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
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
