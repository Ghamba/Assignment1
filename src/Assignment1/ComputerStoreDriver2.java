package Assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ComputerStoreDriver2 {
    private static final String Pass = "password";
    private static final int Max_attempts = 3;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int maxComputers;

        System.out.println("Welcome to Pargol's Computer Store Management program!");

        do {
            try {
                System.out.print("Enter the maximum number of computers in your store: ");
                maxComputers = readPositiveIntInput(kb);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                kb.nextLine(); 
                continue; 
            }

            if (maxComputers <= 0) {
                System.out.println("Invalid input. Please enter a positive integer.");
            } else {
                break; 
            }
        } while (true);

        Computer2[] inventory = new Computer2[maxComputers];

        int choice;
        int tries = 0;
        do {
            displayMainMenu();
            System.out.print("Please enter your choice (1-5): ");
            choice = kb.nextInt();
            kb.nextLine(); 

            switch (choice) {
                case 1:
                    if (authenticateUser(kb, tries)) {
                        enterNewComputers(inventory, kb);
                    } else {
                        System.out.println("Too many incorrect password attempts. Returning to the main menu.");
                    }
                    break;
                case 2:
                    if (authenticateUser(kb, tries)) {
                        changeComputerInformation(inventory, kb);
                    } else {
                        System.out.println("Too many incorrect password attempts. Returning to the main menu.");
                    }
                    break;
                case 3:
                    displayComputersByBrand(inventory, kb);
                    break;
                case 4:
                    displayComputersUnderPrice(inventory, kb);
                    break;
                case 5:
                    System.out.println("Exiting the Computer Store Management program. See ya!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (choice != 5);

        kb.close();
    }


    private static int readPositiveIntInput(Scanner scanner) throws InputMismatchException {
        int input = scanner.nextInt();
        scanner.nextLine(); 
        if (input < 0) {
            throw new InputMismatchException();
        }

        return input;
    }


    private static void displayMainMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Enter new computers (password required)");
        System.out.println("2. Change information of a computer (password required)");
        System.out.println("3. Display all computers by a specific brand");
        System.out.println("4. Display all computers under a certain price");
        System.out.println("5. Quit");
    }

    private static boolean authenticateUser(Scanner scanner, int tries) {
        for (int i = 0; i < Max_attempts; i++) {
            System.out.print("Enter the password (attempt " + (i + 1) + "): ");
            String enteredPassword = scanner.nextLine();
            if (enteredPassword.equals(Pass)) {
                return true;
            } else {
                System.out.println("Incorrect password. Try again.");
            }
        }
        return false;
    }

    private static void enterNewComputers(Computer2[] inventory, Scanner scanner) {
        System.out.print("Enter the number of computers you want to enter: ");
        int numComputers = scanner.nextInt();
        scanner.nextLine(); 

        if (numComputers <= inventory.length) {
            for (int i = 0; i < numComputers; i++) {
                System.out.println("Enter information for Computer " + (i + 1) + ":");
                Computer2 newComputer = createComputer(inventory, scanner);
                inventory[i] = newComputer;
            }

            System.out.println("Computers successfully added to the inventory.");
        } else {
            System.out.println("Not enough space in the inventory.");
        }
    }

    private static Computer2 createComputer(Computer2[] inventory, Scanner scanner) {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        int serialNumber = 1000000 + findLastSerialNumber(inventory); 
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 

        return new Computer2(brand, model, serialNumber, price);
    }

    private static int findLastSerialNumber(Computer2[] inventory) {
        int lastSerialNumber = 0;
        for (Computer2 computer : inventory) {
            if (computer != null && computer.getSerialNumber() > lastSerialNumber) {
                lastSerialNumber = computer.getSerialNumber();
            }
        }
        return lastSerialNumber;
    }

    private static void changeComputerInformation(Computer2[] inventory, Scanner scanner) {
        System.out.print("Enter the computer number you wish to update (index in the array): ");
        int computerNumber = scanner.nextInt();
        scanner.nextLine(); 

        if (isValidIndex(computerNumber, inventory.length) && inventory[computerNumber - 1] != null) {
            displayComputerInfo(inventory[computerNumber - 1]);

            char updateChoice;
            do {
                displayUpdateMenu();
                System.out.print("Enter your choice (1-5): ");
                updateChoice = scanner.next().charAt(0);
                scanner.nextLine(); 

                switch (updateChoice) {
                    case '1':
                        System.out.print("Enter the new brand: ");
                        String newBrand = scanner.nextLine();
                        inventory[computerNumber - 1].setBrand(newBrand);
                        break;
                    case '2':
                        System.out.print("Enter the new model: ");
                        String newModel = scanner.nextLine();
                        inventory[computerNumber - 1].setModel(newModel);
                        break;
                    case '3':
                        System.out.print("Enter the new SN: ");
                        int newSerialNumber = scanner.nextInt();
                        scanner.nextLine(); 
                        inventory[computerNumber - 1].setSerialNumber(newSerialNumber);
                        break;
                    case '4':
                        System.out.print("Enter the new price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); 
                        inventory[computerNumber - 1].setPrice(newPrice);
                        break;
                    case '5':
                        System.out.println("Update operation canceled. Returning to the main menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

                displayComputerInfo(inventory[computerNumber - 1]);
            } while (updateChoice != '5');
        } else {
            System.out.println("Invalid computer number or no computer found at the specified index.");
        }
    }

    private static void displayUpdateMenu() {
        System.out.println("\n===== Update Computer Information =====");
        System.out.println("1. Update brand");
        System.out.println("2. Update model");
        System.out.println("3. Update SN");
        System.out.println("4. Update price");
        System.out.println("5. Quit update");
    }

    private static void displayComputersByBrand(Computer2[] inventory, Scanner scanner) {
        System.out.print("Enter the brand name to search for: ");
        String brandToSearch = scanner.nextLine();

        System.out.println("\nComputers with brand \"" + brandToSearch + "\":");
        boolean found = false;
        for (Computer2 computer : inventory) {
            if (computer != null && computer.getBrand().equalsIgnoreCase(brandToSearch)) {
                displayComputerInfo(computer);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No computers found with brand \"" + brandToSearch + "\".");
        }
    }

    private static void displayComputersUnderPrice(Computer2[] inventory, Scanner scanner) {
        System.out.print("Enter the maximum price to search for: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("\nComputers under $" + maxPrice + ":");
        boolean found = false;
        for (Computer2 computer : inventory) {
            if (computer != null && computer.getPrice() < maxPrice) {
                displayComputerInfo(computer);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No computers found under $" + maxPrice + ".");
        }
    }

    private static void displayComputerInfo(Computer2 computer) {
        System.out.println(computer);
        System.out.println("--------------------------");
    }

    private static boolean isValidIndex(int index, int arrayLength) {
        return index >= 1 && index <= arrayLength;
    }
}
