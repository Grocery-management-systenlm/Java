import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroceryManagementSystem {
    private static List<GroceryItem> groceryItems = new ArrayList<>();
    private static int itemIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Grocery Management System");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    updateItem(scanner);
                    break;
                case 4:
                    deleteItem(scanner);
                    break;
                case 5:
                    System.out.println("Exiting. Have a great day!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();

        GroceryItem newItem = new GroceryItem(itemIdCounter++, name, price, quantity);
        groceryItems.add(newItem);
        System.out.println("Item added successfully!");
    }

    private static void viewItems() {
        System.out.println("Grocery Items:");
        for (GroceryItem item : groceryItems) {
            System.out.println(item);
        }
    }

    private static void updateItem(Scanner scanner) {
        System.out.print("Enter item ID to update: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (GroceryItem item : groceryItems) {
            if (item.getId() == itemId) {
                System.out.print("Enter new item name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new item price: ");
                double newPrice = scanner.nextDouble();
                System.out.print("Enter new item quantity: ");
                int newQuantity = scanner.nextInt();

                item.setName(newName);
                item.setPrice(newPrice);
                item.setQuantity(newQuantity);
                System.out.println("Item updated successfully!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private static void deleteItem(Scanner scanner) {
        System.out.print("Enter item ID to delete: ");
        int itemId = scanner.nextInt();

        for (GroceryItem item : groceryItems) {
            if (item.getId() == itemId) {
                groceryItems.remove(item);
                System.out.println("Item deleted successfully!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    static class GroceryItem {
        private int id;
        private String name;
        private double price;
        private int quantity;

        public GroceryItem(int id, String name, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        // Getters and setters (if needed)

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
        }
    }
}
