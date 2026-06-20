import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem {

    private final Map<String, Integer> inventory = new HashMap<>();

    public void addItem(String itemName, int quantity) {
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
    }

    public void updateItem(String itemName, int quantity) {
        inventory.put(itemName, quantity);
    }

    public void removeItem(String itemName) {
        inventory.remove(itemName);
    }

    public Integer getQuantity(String itemName) {
        return inventory.get(itemName);
    }

    public void displayInventory() {
        System.out.println("Inventory items:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Add, update, lookup, and delete are O(1) on average with HashMap.");
        System.out.println("Space complexity is O(n) for n stored items.");
    }

    public static void main(String[] args) {
        InventoryManagementSystem system = new InventoryManagementSystem();
        system.addItem("Laptop", 12);
        system.addItem("Mouse", 50);
        system.updateItem("Laptop", 15);
        system.removeItem("Mouse");

        system.displayInventory();
        System.out.println("Laptop quantity: " + system.getQuantity("Laptop"));
        system.printAnalysis();
    }
}