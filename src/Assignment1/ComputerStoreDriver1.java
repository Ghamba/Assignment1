package Assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ComputerStoreDriver1 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        // Creating Computer objects
        Computer1 computer1 = createComputer(kb, 1);
        Computer1 computer2 = createComputer(kb, 2);

        // Displaying information of Computer objects
        System.out.println("Computer 1 Information:");
        System.out.println(computer1);

        System.out.println("\nComputer 2 Information:");
        System.out.println(computer2);

        // Modifying attributes of Computer objects
        modifyComputer(computer1, kb, 1);
        modifyComputer(computer2, kb, 2);

        // Displaying modified information
        System.out.println("\nComputer 1 (After Modification) Information:");
        System.out.println(computer1);
        System.out.println("\nComputer 2 (After Modification) Information:");
        System.out.println(computer2);

        // Finding the number of created computers
        System.out.println("\nNumber of Created Computers: " + Computer1.findNumberOfCreatedComputers());

        // Comparing Computer objects for equality
        if (computer1.equals(computer2)) {
            System.out.println("\nComputer 1 and Computer 2 are equal.");
        } else {
            System.out.println("\nComputer 1 and Computer 2 are not equal.");
        }

        kb.close();
    }

    private static Computer1 createComputer(Scanner scanner, int computerNumber) {
        System.out.println("Enter information for Computer " + computerNumber + ":");
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();

        int serialNumber;
        do {
            try {
                System.out.print("Serial Number: ");
                serialNumber = readPositiveIntInput(scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.nextLine(); 
                continue; // Continue the loop
            }

            if (serialNumber <= 0) {
                System.out.println("Invalid input. Please enter a positive integer.");
            } else {
                break; // Exit the loop if a valid positive integer is entered
            }
        } while (true);

        double price;
        do {
            try {
                System.out.print("Price: ");
                price = scanner.nextDouble();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.nextLine(); 
                continue; // Continue the loop
            }

            if (price < 0) {
                System.out.println("Invalid input. Please enter a non-negative price.");
            } else {
                break; // Exit the loop if a valid non-negative price is entered
            }
        } while (true);

        return new Computer1(brand, model, serialNumber, price);
    }

    private static void modifyComputer(Computer1 computer, Scanner scanner, int computerNumber) {
        System.out.println("Modifying attributes of Computer " + computerNumber + ":");
        System.out.print("Enter the new price: ");
        double newPrice;
        do {
            try {
                newPrice = scanner.nextDouble();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.nextLine(); 
                continue; // Continue the loop
            }

            if (newPrice < 0) {
                System.out.println("Invalid input. Please enter a non-negative price.");
            } else {
                break; // Exit the loop if a valid non-negative price is entered
            }
        } while (true);

        computer.setPrice(newPrice);
    }

    // method to read a positive integer input
    private static int readPositiveIntInput(Scanner scanner) throws InputMismatchException {
        int input = scanner.nextInt();
        scanner.nextLine(); 
        
        if (input < 0) {
            throw new InputMismatchException();
        }

        return input;
    }
}
