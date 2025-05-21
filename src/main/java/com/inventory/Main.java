package src.main.java.com.inventory;

import src.main.java.com.inventory.business.Inventory;
import src.main.java.com.inventory.ui.InventoryGUI;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Set look and feel to system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }

        // Initialize the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Create and initialize the inventory system
                Inventory inventory = new Inventory();
                
                // Create and show the GUI
                InventoryGUI gui = new InventoryGUI(inventory);
                gui.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error initializing application: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
} 