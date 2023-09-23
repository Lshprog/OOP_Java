package org.example;

import org.example.entities.CoffeeProduct;
import org.example.entities.Pack;
import org.example.services.CoffeeService;
import org.example.services.CoffeeServiceImpl;
import org.example.services.VanService;
import org.example.services.VanServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VanService vanService = new VanServiceImpl();
    private static final CoffeeService coffeeService = new CoffeeServiceImpl();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("===== Menu =====");
            System.out.println("1. List all available coffee");
            System.out.println("2. List all coffee in a van");
            System.out.println("3. Load coffee products into a van automatically");
            System.out.println("4. Load specific coffee products into a van");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    List<CoffeeProduct> allCoffee = coffeeService.getAllCoffee();
                    System.out.println("All Available Coffee:");
                    for (CoffeeProduct coffee : allCoffee) {
                        System.out.println(coffee);
                    }
                    break;
                case 2:
                    System.out.print("Enter the van ID: ");
                    long vanId = scanner.nextLong();
                    List<CoffeeProduct> coffeeInVan = vanService.getAllCoffeeInVan(vanId);
                    System.out.println("Coffee in Van:");
                    for (CoffeeProduct coffee : coffeeInVan) {
                        System.out.println(coffee);
                    }
                    break;
                case 3:
                    // Add code to load coffee automatically
                    break;
                case 4:
                    // Add code to load specific coffee products into a van
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}