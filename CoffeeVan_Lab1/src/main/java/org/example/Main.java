package org.example;

import org.example.common.enums.*;
import org.example.common.filters.CoffeeFilter;
import org.example.entities.*;
import org.example.services.CoffeeService;
import org.example.services.CoffeeServiceImpl;
import org.example.services.VanService;
import org.example.services.VanServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VanService vanService = new VanServiceImpl();
    private static final CoffeeService coffeeService = new CoffeeServiceImpl();

    public static void main(String[] args) {

        disableLogging();

        boolean exit = false;
        while (!exit) {
            System.out.println("===== Menu =====");
            System.out.println("0. List all available coffee");
            System.out.println("1. List all coffee in a van");
            System.out.println("2. List all coffee not in any van");
            System.out.println("3. Load coffee products into a van automatically");
            System.out.println("4. Load specific coffee products into a van");
            System.out.println("5. List all coffee in a van sorted by price/weight ratio");
            System.out.println("6. List all coffee in a van sorted by parameter ");
            System.out.println("7. Filter coffee in van based on range of parameters ");
            System.out.println("8. Create new coffee van");
            System.out.println("81. List all coffee vans");
            System.out.println("82. Get coffee van by name");
            System.out.println("801. Update coffee van");
            System.out.println("802. Delete coffee van");
            System.out.println("9. Create new Coffee sort ");
            System.out.println("91. Update coffee sort");
            System.out.println("92. Delete coffee sort");
            System.out.println("10. Create new Pack for coffee");
            System.out.println("101. Update pack");
            System.out.println("102. Delete pack");
            System.out.println("11. Find coffee by id");
            System.out.println("111. Create new coffee product");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    listAllAvailableCoffee();
                    break;
                case 1:
                    listAllCoffeeInVan();
                    break;
                case 2:
                    listAllCoffeeNotInVan();
                    break;
                case 3:
                    loadCoffeeAutomatically();
                    break;
                case 4:
                    loadSpecificCoffeeProducts();
                    break;
                case 5:
                    listCoffeeInVanSortedByPriceWeightRatio();
                    break;
                case 6:
                    listCoffeeInVanSortedByParameter();
                    break;
                case 7:
                    filterBasedOnParameters();
                    break;
                case 8:
                    createNewVan();
                    break;
                case 81:
                    listAllCoffeeVans();
                    break;
                case 82:
                    getCoffeeVanByName();
                    break;
                case 801:
                    updateVan();
                    break;
                case 802:
                    deleteVan();
                    break;
                case 9:
                    createNewCoffeeSort();
                    break;
                case 91:
                    updateCoffeeSort();
                    break;
                case 92:
                    deleteCoffeeSort();
                    break;
                case 93:
                    showAllCoffeeSorts();
                    break;
                case 10:
                    createNewPack();
                    break;
                case 101:
                    updatePack();
                    break;
                case 102:
                    deletePack();
                    break;
                case 103:
                    showAllPackTypes();
                    break;
                case 11:
                    findCoffeeProductById();
                    break;
                case 111:
                    createNewCoffeeProduct();
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createNewCoffeeProduct() {
        System.out.println("===== Create New Coffee Product =====");
        System.out.println("Select the type of coffee product:");
        System.out.println("1. Coffee Beans");
        System.out.println("2. Ground Coffee");
        System.out.println("3. Instant Coffee");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Prompt user for CoffeeSort (with default value if necessary)
        List<CoffeeSort> availableCoffeeSorts = coffeeService.getAllCoffeeSorts();
        // Prompt user for CoffeeSort (with default value if necessary)
        System.out.println("Available Coffee Sorts:");
        for (int i = 0; i < availableCoffeeSorts.size(); i++) {
            System.out.println((i+1) + ". " + availableCoffeeSorts.get(i).toString());
        }
        System.out.print("Enter the number corresponding to the CoffeeSort: ");
        int coffeeSortChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (coffeeSortChoice < 1 || coffeeSortChoice > availableCoffeeSorts.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        CoffeeSort coffeeSort = availableCoffeeSorts.get(coffeeSortChoice - 1);

        // Prompt user for Pack (with default value if necessary)
        List<Pack> availablePacks = coffeeService.getAllPackTypes();
        System.out.println("Available Packs:");
        for (int i = 0; i < availablePacks.size(); i++) {
            System.out.println((i+1) + ". " + availablePacks.get(i).getDescription());
        }
        System.out.print("Enter the number corresponding to the Pack: ");
        int packChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (packChoice < 1 || packChoice > availablePacks.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        Pack pack = availablePacks.get(packChoice - 1);
        // Prompt user for CoffeeVan (assuming you have a way to retrieve or create one)
        CoffeeVan van = null; // Implement logic to get CoffeeVan

        // Set unique attributes for CoffeeBeans
        RoastLevel roastLevel = getEnumFromUserInput("Roast Level (LIGHT, MEDIUM, DARK):", RoastLevel.class);
        if (roastLevel == null) return;

        switch (typeChoice) {
            case 1:
                CoffeeBeans coffeeBeans = new CoffeeBeans(coffeeSort, pack, van);

                coffeeBeans.setRoastLevel(roastLevel);
                coffeeService.save(coffeeBeans);
                System.out.println("Coffee Beans saved successfully.");
                break;
            case 2:
                GroundCoffee groundCoffee = new GroundCoffee(coffeeSort, pack, van);

                // Set unique attributes for GroundCoffee
                GrindType grindType = getEnumFromUserInput("Grind Type (COARSE, MEDIUM, FINE):", GrindType.class);
                if (grindType == null) return;
                groundCoffee.setGrindType(grindType);

                Intensity intensity = getEnumFromUserInput("Intensity (MILD, MEDIUM, STRONG):", Intensity.class);
                if (intensity == null) return;
                groundCoffee.setIntensity(intensity);

                groundCoffee.setRoastLevel(roastLevel);
                coffeeService.save(groundCoffee);
                System.out.println("Ground Coffee saved successfully.");
                break;
            case 3:
                InstantCoffee instantCoffee = new InstantCoffee(coffeeSort, pack, van);

                // Set unique attributes for InstantCoffee
                Dissolvability dissolvability = getEnumFromUserInput("Dissolvability (FAST, MEDIUM, SLOW):", Dissolvability.class);
                if (dissolvability == null) return;
                instantCoffee.setDissolvability(dissolvability);

                Flavor flavor = getEnumFromUserInput("Flavor (VANILLA, HAZELNUT):", Flavor.class);
                if (flavor == null) return;
                instantCoffee.setFlavor(flavor);

                instantCoffee.setRoastLevel(roastLevel);
                coffeeService.save(instantCoffee);
                System.out.println("Instant Coffee saved successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    // Helper method to get enum from user input
    private static <T extends Enum<T>> T getEnumFromUserInput(String prompt, Class<T> enumType) {
        System.out.println(prompt);
        System.out.print("Enter your choice: ");
        String userInput = scanner.nextLine().toUpperCase();

        try {
            return Enum.valueOf(enumType, userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please try again.");
            return null;
        }
    }




    private static void findCoffeeProductById() {
        System.out.print("Enter the ID of the coffee product: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        CoffeeProduct coffeeProduct = coffeeService.getCoffeeProductById(id);

        if (coffeeProduct != null) {
            System.out.println("Coffee Product found:");
            System.out.println(coffeeProduct);
        } else {
            System.out.println("Coffee Product with ID " + id + " not found.");
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

    private static void updateVan() {
        System.out.print("Enter the van ID: ");
        Long vanId = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter the new van name: ");
        String newName = scanner.nextLine();

        CoffeeVan vanToUpdate = vanService.getCoffeeVanById(vanId);
        if (vanToUpdate != null) {
            vanToUpdate.setName(newName);
            vanService.updateVan(vanToUpdate);
            System.out.println("Van updated successfully.");
        } else {
            System.out.println("Van with ID " + vanId + " not found.");
        }
    }

    private static void deleteVan() {
        System.out.print("Enter the van ID: ");
        Long vanId = scanner.nextLong();

        CoffeeVan vanToDelete = vanService.getCoffeeVanById(vanId);
        if (vanToDelete != null) {
            vanService.deleteVan(vanToDelete);
            System.out.println("Van deleted successfully.");
        } else {
            System.out.println("Van with ID " + vanId + " not found.");
        }
    }


    private static void showAllCoffeeSorts(){
        List<CoffeeSort> allSorts = coffeeService.getAllCoffeeSorts();
        for (CoffeeSort sort : allSorts) {
            System.out.println(sort);
        }
    }

    private static void createNewCoffeeSort() {
        System.out.print("Enter the coffee sort name: ");
        String name = scanner.nextLine();

        CoffeeSort newCoffeeSort = new CoffeeSort();
        newCoffeeSort.setName(name);

        coffeeService.save(newCoffeeSort);
        System.out.println("New coffee sort created successfully.");
    }

    private static void updateCoffeeSort() {
        System.out.print("Enter the coffee sort ID: ");
        Long sortId = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter the new coffee sort name: ");
        String newName = scanner.nextLine();

        CoffeeSort sortToUpdate = coffeeService.getCoffeeSortProductById(sortId);
        if (sortToUpdate != null) {
            sortToUpdate.setName(newName);
            coffeeService.update(sortToUpdate);
            System.out.println("Coffee sort updated successfully.");
        } else {
            System.out.println("Coffee sort with ID " + sortId + " not found.");
        }
    }

    private static void deleteCoffeeSort() {
        System.out.print("Enter the coffee sort ID: ");
        Long sortId = scanner.nextLong();

        CoffeeSort sortToDelete = coffeeService.getCoffeeSortProductById(sortId);
        if (sortToDelete != null) {
            coffeeService.delete(sortToDelete);
            System.out.println("Coffee sort deleted successfully.");
        } else {
            System.out.println("Coffee sort with ID " + sortId + " not found.");
        }
    }


    private static void showAllPackTypes() {
        List<Pack> allPacks = coffeeService.getAllPackTypes();
        for (Pack pack : allPacks) {
            System.out.println(pack);
        }
    }
    private static void createNewPack() {
        System.out.print("Enter the pack name: ");
        String name = scanner.nextLine();

        Pack newPack = new Pack();
        newPack.setDescription(name);

        coffeeService.save(newPack);
        System.out.println("New pack created successfully.");
    }

    private static void updatePack() {
        System.out.print("Enter the pack ID: ");
        Long packId = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter the new pack name: ");
        String newName = scanner.nextLine();

        Pack packToUpdate = coffeeService.getPackProductById(packId);
        if (packToUpdate != null) {
            packToUpdate.setDescription(newName);
            coffeeService.update(packToUpdate);
            System.out.println("Pack updated successfully.");
        } else {
            System.out.println("Pack with ID " + packId + " not found.");
        }
    }

    private static void deletePack() {
        System.out.print("Enter the pack ID: ");
        Long packId = scanner.nextLong();

        Pack packToDelete = coffeeService.getPackProductById(packId);
        if (packToDelete != null) {
            coffeeService.delete(packToDelete);
            System.out.println("Pack deleted successfully.");
        } else {
            System.out.println("Pack with ID " + packId + " not found.");
        }
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

    private static void filterBasedOnParameters() {
        CoffeeFilter filter = new CoffeeFilter();
        Long vanId = null;
        System.out.print("Enter the van ID or leave empty if you want to filter coffee not in any van: ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            // User wants to leave it empty
            System.out.println("Filtering for coffee not in any van.");
        } else {
            // User entered a value, try to parse it as a long
            try {
                vanId = Long.parseLong(input);
                CoffeeVan van = vanService.getCoffeeVanById(vanId);
                if (van == null) {
                    System.out.println("Incorrect van id!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                return;
            }
        }


        System.out.println("Types of coffee (0 - CoffeeBeans, 1 - InstantCoffee, 2 - GroundCoffee)");
        System.out.println("Enter the coffee types to filter (012 for all, 02 for all except InstantCoffee, etc):");
        input = scanner.nextLine();

        List<String> classNames = new ArrayList<>();
        if (input.contains("0")) {
            classNames.add("CoffeeBeans");
        }
        if (input.contains("1")) {
            classNames.add("InstantCoffee");
        }
        if (input.contains("2")) {
            classNames.add("GroundCoffee");
        }

        if (classNames.isEmpty()){
            System.out.println("No types!");
            return;
        }

        // Show all available sorts and let the user choose
        System.out.println("\nAvailable Coffee Sorts:");
        List<CoffeeSort> allSorts = coffeeService.getAllCoffeeSorts();
        for (CoffeeSort sort : allSorts) {
            System.out.println(sort);
        }
        System.out.print("Enter the IDs of the sorts to filter by (comma-separated): ");
        String sortIdsInput = scanner.nextLine();
        List<String> sortIds = Arrays.stream(sortIdsInput.split(","))
                .collect(Collectors.toList());
        filter.setSortId(sortIds);

        // Show all available packs and let the user choose
        System.out.println("\nAvailable Packs:");
        List<Pack> allPacks = coffeeService.getAllPackTypes();
        for (Pack pack : allPacks) {
            System.out.println(pack);
        }
        System.out.print("Enter the IDs of the packs to filter by (comma-separated): ");
        String packIdsInput = scanner.nextLine();
        List<String> packIds = Arrays.stream(packIdsInput.split(","))
                .collect(Collectors.toList());
        filter.setPackId(packIds);


        // Prompt user for filter criteria
        System.out.print("Enter minimum price (or leave empty for no minimum): ");
        String minPriceInput = scanner.nextLine();
        if (!minPriceInput.isEmpty()) {
            filter.setMinPrice(Double.parseDouble(minPriceInput));
        }

        System.out.print("Enter maximum price (or leave empty for no maximum): ");
        String maxPriceInput = scanner.nextLine();
        if (!maxPriceInput.isEmpty()) {
            filter.setMaxPrice(Double.parseDouble(maxPriceInput));
        }

        System.out.print("Enter minimum volume (or leave empty for no minimum): ");
        String minVolumeInput = scanner.nextLine();
        if (!minVolumeInput.isEmpty()) {
            filter.setMinVolume(Double.parseDouble(minVolumeInput));
        }

        System.out.print("Enter maximum volume (or leave empty for no maximum): ");
        String maxVolumeInput = scanner.nextLine();
        if (!maxVolumeInput.isEmpty()) {
            filter.setMaxVolume(Double.parseDouble(maxVolumeInput));
        }

        System.out.print("Enter minimum weight (or leave empty for no minimum): ");
        String minWeightInput = scanner.nextLine();
        if (!minWeightInput.isEmpty()) {
            filter.setMinWeight(Double.parseDouble(minWeightInput));
        }

        System.out.print("Enter maximum weight (or leave empty for no maximum): ");
        String maxWeightInput = scanner.nextLine();
        if (!maxWeightInput.isEmpty()) {
            filter.setMaxWeight(Double.parseDouble(maxWeightInput));
        }

        System.out.println("\nAvailable Roast levels: ");
        System.out.println(" LIGHT, MEDIUM, DARK ");
        System.out.print("Enter roast levels (comma-separated, or leave empty for no filter): ");
        String roastLevelsInput = scanner.nextLine();
        if (!roastLevelsInput.isEmpty()) {
            String[] roastLevels = roastLevelsInput.split(",");
            List<String> upperCaseRoastLevels = new ArrayList<>();

            for (String str : roastLevels) {
                upperCaseRoastLevels.add(str.toUpperCase());
            }

            filter.setRoastLevel(upperCaseRoastLevels);
        }

        if (classNames.contains("InstantCoffee")) {
            System.out.println("\nAvailable Dissolvability levels: ");
            System.out.println(" FAST, MEDIUM, SLOW ");
            System.out.print("Enter dissolvability options (comma-separated, or leave empty for no filter): ");
            String dissolvabilityInput = scanner.nextLine();
            if (!dissolvabilityInput.isEmpty()) {
                String[] dissolvabilityOptions = dissolvabilityInput.split(",");
                List<String> upperCaseDissolvabilityOptions = new ArrayList<>();

                for (String str : dissolvabilityOptions) {
                    upperCaseDissolvabilityOptions.add(str.toUpperCase());
                }

                filter.setDissolvability(upperCaseDissolvabilityOptions);
            }

            System.out.println("\nAvailable Flavors: ");
            System.out.println(" VANILLA, HAZELNUT ");
            System.out.print("Enter flavor options (comma-separated, or leave empty for no filter): ");
            String flavorInput = scanner.nextLine();
            if (!flavorInput.isEmpty()) {
                String[] flavorOptions = flavorInput.split(",");
                List<String> upperCaseFlavorOptions = new ArrayList<>();

                for (String str : flavorOptions) {
                    upperCaseFlavorOptions.add(str.toUpperCase());
                }

                filter.setFlavor(upperCaseFlavorOptions);
            }
        }

        if(classNames.contains("GroundCoffee")) {
            System.out.println("\nAvailable Grind types: ");
            System.out.println(" COARSE, MEDIUM, FINE ");
            System.out.print("Enter grind types (comma-separated, or leave empty for no filter): ");
            String grindTypesInput = scanner.nextLine();
            if (!grindTypesInput.isEmpty()) {
                String[] grindTypes = grindTypesInput.split(",");
                List<String> upperCaseGrindTypes = new ArrayList<>();

                for (String str : grindTypes) {
                    upperCaseGrindTypes.add(str.toUpperCase());
                }

                filter.setGrindType(upperCaseGrindTypes);
            }

            System.out.println("\nAvailable Intensity levels: ");
            System.out.println(" MILD, MEDIUM, STRONG ");
            System.out.print("Enter intensity levels (comma-separated, or leave empty for no filter): ");
            String intensityInput = scanner.nextLine();
            if (!intensityInput.isEmpty()) {
                String[] intensityOptions = intensityInput.split(",");
                List<String> upperCaseIntensityOptions = new ArrayList<>();

                for (String str : intensityOptions) {
                    upperCaseIntensityOptions.add(str.toUpperCase());
                }

                filter.setIntensity(upperCaseIntensityOptions);
            }
        }


        // Get the filtered list of coffee products
        List<CoffeeProduct> filteredCoffee = vanService.getCoffeeInVanBasedOnParameters(vanId,filter,
                classNames);

        // Display the filtered list
        System.out.println("Filtered Coffee Products:");
        for (CoffeeProduct coffee : filteredCoffee) {
            System.out.println(coffee);
        }
    }



}
