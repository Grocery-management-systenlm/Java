package TcsCaseStudy;

import java.util.*;

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

class Transaction {
    private List<GroceryItem> items;
    private double totalCost;
    private String paymentMethod;

    public Transaction(List<GroceryItem> items, double totalCost, String paymentMethod) {
        this.items = items;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
    }

    public List<GroceryItem> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

public class GroceryManagementSystem {
    private static ArrayList<GroceryItem> groceryItems = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isLoggedIn = false;

    // Hardcoded valid credit card details for demonstration
    private static final String VALID_CREDIT_CARD_NUMBER = "1234-5678-9012-3456";
    private static final String VALID_CREDIT_CARD_EXPIRY = "12/23";
    private static final int VALID_CREDIT_CARD_CVV = 123;

    // Hardcoded valid UPI ID for demonstration
    private static final String VALID_UPI_ID = "example@upi";

    public static void main(String[] args) {
        login();
        if (isLoggedIn) {
            while (true) {
                System.out.println("\nGrocery Management System");
                System.out.println("1. Add Item");
                System.out.println("2. Remove Item");
                System.out.println("3. Update Item Price");
                System.out.println("4. Search Items");
                System.out.println("5. View Items");
                System.out.println("6. Calculate Total Cost");
                System.out.println("7. Make Payment");
                System.out.println("8. Show Previous Orders");
                System.out.println("9. Exit");
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
                        makePayment();
                        break;
                    case 8:
                        showPreviousOrders();
                        break;
                    case 9:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting program...");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Simple hardcoded credentials for demonstration purposes
        if (username.equals("admin") && password.equals("password")) {
            isLoggedIn = true;
            System.out.println("Login successful!");
        } else {
            isLoggedIn = false;
            System.out.println("Login failed!");
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

    private static void makePayment() {
        double totalCost = groceryItems.stream().mapToDouble(GroceryItem::getPrice).sum();
        System.out.println("Total cost of all items: $" + totalCost);

        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.println("3. Debit Card");

        int paymentMethod = getUserChoice();
        String paymentMethodStr = "";
        switch (paymentMethod) {
            case 1:
                paymentMethodStr = "Credit Card";
                if (!verifyCreditCard()) {
                    System.out.println("Credit card verification failed. Payment cannot be processed.");
                    return;
                }
                break;
            case 2:
                paymentMethodStr = "UPI";
                if (!verifyUPI()) {
                    System.out.println("UPI verification failed. Payment cannot be processed.");
                    return;
                }
                break;
            case 3:
                paymentMethodStr = "Debit Card";
                if (!verifyDebitCard()) {
                    System.out.println("Debit card verification failed. Payment cannot be processed.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Transaction transaction = new Transaction(new ArrayList<>(groceryItems), totalCost, paymentMethodStr);
        transactions.add(transaction);
        groceryItems.clear(); // Clear the items after payment
        System.out.println("Payment successful!");
    }

    private static boolean verifyCreditCard() {
        // Hardcoded valid credit card details for demonstration
        System.out.print("Enter credit card number: ");
        String creditCardNumber = scanner.next();
        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = scanner.next();
        System.out.print("Enter CVV: ");
        int cvv = scanner.nextInt();

        return creditCardNumber.equals(VALID_CREDIT_CARD_NUMBER)
                && expiryDate.equals(VALID_CREDIT_CARD_EXPIRY)
                && cvv == VALID_CREDIT_CARD_CVV;
    }

    private static boolean verifyUPI() {
        // Hardcoded valid UPI ID for demonstration
        System.out.print("Enter UPI ID: ");
        String upiId = scanner.next();

        return upiId.equals(VALID_UPI_ID);
    }

    private static boolean verifyDebitCard() {
        // Hardcoded valid debit card details for demonstration
        System.out.print("Enter debit card number: ");
        String debitCardNumber = scanner.next();
        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = scanner.next();
        System.out.print("Enter CVV: ");
        int cvv = scanner.nextInt();

        return debitCardNumber.equals(VALID_CREDIT_CARD_NUMBER)
                && expiryDate.equals(VALID_CREDIT_CARD_EXPIRY)
                && cvv == VALID_CREDIT_CARD_CVV;
    }

    private static void showPreviousOrders() {
        if (transactions.isEmpty()) {
            System.out.println("No previous orders.");
            return;
        }

        System.out.println("Previous Orders:");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            System.out.println("Order " + (i + 1) + ":");
            System.out.println("Payment Method: " + transaction.getPaymentMethod());
            System.out.println("Items:");
            for (GroceryItem item : transaction.getItems()) {
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total Cost: $" + transaction.getTotalCost());
            System.out.println("-------------------");
        }
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

