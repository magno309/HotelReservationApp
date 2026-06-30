import UI.MainMenu;

import java.util.Scanner;

final MainMenu mainMenu = MainMenu.getMainMenu();

void main() {
    // System.out.println("Hello World!");
    try (Scanner sc = new Scanner(System.in)) {
        mainMenu.displayMainMenu(sc);
    }
}
