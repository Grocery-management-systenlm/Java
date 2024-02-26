import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class GroceryItem {
    private String name;
    private double price;

    public GroceryItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

public class GroceryManagementSystem {
    private static ArrayList<GroceryItem> groceryItems = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nGrocery Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item Price");
            System.out.println("4. Search Items");
            System.out.println("5. View Items");
            System.out.println("6. Calculate Total Cost");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    updateItemPrice();
                    break;
                case 4:
                    searchItems();
                    break;
                case 5:
                    viewItems();
                    break;
                case 6:
                    calculateTotalCost();
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice() {
        int choice = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    private static void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.next();
        double price = getDoubleInput("Enter item price: ");

        groceryItems.add(new GroceryItem(name, price));
        System.out.println("Item added successfully!");
    }

    private static void removeItem() {
        if (groceryItems.isEmpty()) {
            System.out.println("No items to remove.");
            return;
        }

        System.out.print("Enter the index of the item to remove: ");
        int index = getUserChoice();

        if (index >= 0 && index < groceryItems.size()) {
            groceryItems.remove(index);
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void updateItemPrice() {
        if (groceryItems.isEmpty()) {
            System.out.println("No items to update.");
            return;
        }

        System.out.print("Enter the index of the item to update: ");
        int index = getUserChoice();

        if (index >= 0 && index < groceryItems.size()) {
            double newPrice = getDoubleInput("Enter the new price: ");
            groceryItems.get(index).setPrice(newPrice);
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void searchItems() {
        if (groceryItems.isEmpty()) {
            System.out.println("No items to search.");
            return;
        }

        System.out.print("Enter the name of the item to search: ");
        String searchName = scanner.next();

        boolean found = false;
        for (GroceryItem item : groceryItems) {
            if (item.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Item found: " + item.getName() + " - $" + item.getPrice());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    private static void viewItems() {
        if (groceryItems.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }

        System.out.println("Grocery Items:");
        for (int i = 0; i < groceryItems.size(); i++) {
            GroceryItem item = groceryItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }

    private static void calculateTotalCost() {
        if (groceryItems.isEmpty()) {
            System.out.println("No items to calculate cost.");
            return;
        }

        double totalCost = groceryItems.stream().mapToDouble(GroceryItem::getPrice).sum();
        System.out.println("Total cost of all items: $" + totalCost);
    }

    private static double getDoubleInput(String message) {
        double value = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(message);
            try {
                value = scanner.nextDouble();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return value;
    }
}
