import java.util.Scanner;

import src.main.java.com.inventory.business.Inventory;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    updateStock();
                    break;
                case 5:
                    inventory.displayInventory();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Inventory Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Inventory Management System ===");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. Update Stock");
        System.out.println("5. Display Inventory");
        System.out.println("6. Exit");
    }

    private static void addProduct() {
        System.out.println("\n=== Add New Product ===");
        String name = getStringInput("Enter product name: ");
        double price = getDoubleInput("Enter product price: ");
        int quantity = getIntInput("Enter product quantity: ");
        String description = getStringInput("Enter product description: ");
        
        inventory.addProduct(name, price, quantity, description);
    }

    private static void removeProduct() {
        System.out.println("\n=== Remove Product ===");
        int id = getIntInput("Enter product ID to remove: ");
        inventory.removeProduct(id);
    }

    private static void updateProduct() {
        System.out.println("\n=== Update Product ===");
        int id = getIntInput("Enter product ID to update: ");
        String name = getStringInput("Enter new product name: ");
        double price = getDoubleInput("Enter new product price: ");
        int quantity = getIntInput("Enter new product quantity: ");
        String description = getStringInput("Enter new product description: ");
        
        inventory.updateProduct(id, name, price, quantity, description);
    }

    private static void updateStock() {
        System.out.println("\n=== Update Stock ===");
        int id = getIntInput("Enter product ID: ");
        int quantity = getIntInput("Enter new quantity: ");
        inventory.updateStock(id, quantity);
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
} 