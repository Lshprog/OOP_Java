package org.example;

import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeSort;
import org.example.entities.CoffeeVan;
import org.example.entities.Pack;
import org.example.services.CoffeeService;
import org.example.services.CoffeeServiceImpl;
import org.example.services.VanService;
import org.example.services.VanServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VanService vanService = new VanServiceImpl();
    private static final CoffeeService coffeeService = new CoffeeServiceImpl();

    public static void main(String[] args) {

        disableLogging();

        boolean exit = false;
        while (!exit) {
            System.out.println("===== Menu =====");
            System.out.println("1. List all available coffee");
            System.out.println("2. List all coffee in a van");
            System.out.println("3. List all coffee not in any van");
            System.out.println("4. Load coffee products into a van automatically");
            System.out.println("5. Load specific coffee products into a van");
            System.out.println("6. List all coffee in a van sorted by price/weight ratio");
            System.out.println("7. List all coffee in a van sorted by parameter ");
            System.out.println("8. Create new coffee van");
            System.out.println("81. List all coffee vans");
            System.out.println("82. Get coffee van by name");
            System.out.println("9. Create new Coffee sort ");
            System.out.println("10. Create new Pack for coffee");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    listAllAvailableCoffee();
                    break;
                case 2:
                    listAllCoffeeInVan();
                    break;
                case 3:
                    listAllCoffeeNotInVan();
                    break;
                case 4:
                    loadCoffeeAutomatically();
                    break;
                case 5:
                    loadSpecificCoffeeProducts();
                    break;
                case 6:
                    listCoffeeInVanSortedByPriceWeightRatio();
                    break;
                case 7:
                    listCoffeeInVanSortedByParameter();
                    break;
                case 8:
                    createNewVan();
                    break;
                case 81:
                    getCoffeeVanByName();
                    break;
                case 82:
                    listAllCoffeeVans();
                    break;
                case 9:
                    createNewCoffeeSort();
                    break;
                case 10:
                    createNewPack();
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void listAllAvailableCoffee() {
        List<CoffeeProduct> allCoffee = coffeeService.getAllCoffee();
        System.out.println("All Available Coffee:");
        for (CoffeeProduct coffee : allCoffee) {
            System.out.println(coffee);
        }
    }

    private static void listAllCoffeeNotInVan() {
        List<CoffeeProduct> allCoffee = vanService.getAllCoffeeAvailableToBeAddedToVan();
        System.out.println("All Coffee not in van:");
        for (CoffeeProduct coffee : allCoffee) {
            System.out.println(coffee);
        }
    }

    private static void listAllCoffeeInVan() {
        System.out.print("Enter the van ID: ");
        long vanId = scanner.nextLong();
        List<CoffeeProduct> coffeeInVan = vanService.getAllCoffeeInVan(vanId);
        System.out.println("Coffee in Van:");
        if(coffeeInVan!=null){
            for (CoffeeProduct coffee : coffeeInVan) {
                System.out.println(coffee);
            }
        }
        else{
            System.out.println("No such van in database!");
        }

    }

    private static void loadCoffeeAutomatically() {
        System.out.print("Enter the van ID: ");
        long vanId = scanner.nextLong();
        System.out.print("Enter the budget: ");
        double budget = scanner.nextDouble();

        CoffeeVan van = vanService.getCoffeeVanById(vanId);
        if (van != null) {
            vanService.loadCoffeeVanAutomaticallyBasedOnBudget(van, budget);
            System.out.println("Coffee loaded into van successfully.");
        } else {
            System.out.println("Van with ID " + vanId + " not found.");
        }
    }


    private static void loadSpecificCoffeeProducts() {
        System.out.print("Enter the van ID: ");
        long vanId = scanner.nextLong();
        System.out.print("Enter the IDs of the products (comma-separated): ");
        scanner.nextLine(); // Consume newline character
        String inputIds = scanner.nextLine();
        String[] idStrings = inputIds.split(",");
        List<Long> idsOfProducts = new ArrayList<>();

        for (String idString : idStrings) {
            idsOfProducts.add(Long.parseLong(idString.trim()));
        }

        CoffeeVan van = vanService.getCoffeeVanById(vanId);
        if (van != null) {
            vanService.loadCoffeeProductsByIdToVan(vanId, idsOfProducts);
            System.out.println("Coffee products loaded into van successfully.");
        } else {
            System.out.println("Van with ID " + vanId + " not found.");
        }
    }

    private static void createNewVan() {
        System.out.print("Enter the van name: ");
        String name = scanner.nextLine();

        CoffeeVan newVan = new CoffeeVan();
        newVan.setName(name);

        vanService.saveVan(newVan);
        System.out.println("New van created successfully.");
    }

    private static void createNewCoffeeSort() {
        System.out.print("Enter the coffee sort name: ");
        String name = scanner.nextLine();

        CoffeeSort newCoffeeSort = new CoffeeSort();
        newCoffeeSort.setName(name);

        coffeeService.save(newCoffeeSort);
        System.out.println("New coffee sort created successfully.");
    }

    private static void createNewPack() {
        System.out.print("Enter the pack name: ");
        String name = scanner.nextLine();

        Pack newPack = new Pack();
        newPack.setDescription(name);

        coffeeService.save(newPack);
        System.out.println("New pack created successfully.");
    }

    private static void listCoffeeInVanSortedByPriceWeightRatio() {
        System.out.print("Enter the van ID: ");
        long vanId = scanner.nextLong();

        List<CoffeeProduct> coffeeInVan = vanService.getAllCoffeeInVanBasedOnPriceAndWeightRatio(vanId);
        System.out.println("Coffee in Van (Sorted by price/weight ratio):");
        for (CoffeeProduct coffee : coffeeInVan) {
            System.out.println(coffee);
        }
    }

    private static void listCoffeeInVanSortedByParameter() {
        System.out.print("Enter the van ID: ");
        long vanId = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter the parameter (price/weight, price, volume, weight): ");
        String parameter = scanner.nextLine();

        List<CoffeeProduct> coffeeInVan = vanService.getAllCoffeeInVanSortedByParameter(vanId, parameter);
        System.out.println("Coffee in Van (Sorted by " + parameter + "):");
        for (CoffeeProduct coffee : coffeeInVan) {
            System.out.println(coffee);
        }
    }

    private static void disableLogging() {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.SEVERE); //could be Level.OFF
    }

    private static void listAllCoffeeVans() {
        List<CoffeeVan> coffeeVans = vanService.getAllCoffeeVans();
        System.out.println("All Coffee Vans:");
        for (CoffeeVan van : coffeeVans) {
            System.out.println(van);
        }
    }

    private static void getCoffeeVanByName() {
        System.out.print("Enter the van name: ");
        String name = scanner.nextLine();

        CoffeeVan van = vanService.getCoffeeVanByName(name);
        if (van != null) {
            System.out.println("Coffee Van Found:");
            System.out.println(van);
        } else {
            System.out.println("No Coffee Van found with the name: " + name);
        }
    }



}
