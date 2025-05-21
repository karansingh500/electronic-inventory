package src.main.java.com.inventory.data;

import src.main.java.com.inventory.model.Product;
import java.sql.Connection;
import java.sql.Statement;

public class DataInitializer {
    private final DatabaseManager dbManager;

    public DataInitializer() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void initializeData() {
        // Sample products data
        Product[] sampleProducts = {
            new Product(0, "Laptop", "Electronics", 899.99, 15, "Dell", "Dell XPS 13 Laptop"),
            new Product(0, "Smartphone", "Electronics", 699.99, 25, "Samsung", "Samsung Galaxy S21"),
            new Product(0, "T-Shirt", "Clothing", 19.99, 100, "Nike", "Cotton T-Shirt"),
            new Product(0, "Jeans", "Clothing", 49.99, 50, "Levi's", "Blue Denim Jeans"),
            new Product(0, "Coffee Beans", "Food", 12.99, 75, "Starbucks", "Arabica Coffee Beans"),
            new Product(0, "Headphones", "Electronics", 199.99, 30, "Sony", "Wireless Noise Cancelling Headphones"),
            new Product(0, "Desk Chair", "Furniture", 149.99, 20, "IKEA", "Ergonomic Office Chair"),
            new Product(0, "Notebook", "Stationery", 4.99, 200, "Moleskine", "Hardcover Notebook"),
            new Product(0, "Protein Powder", "Food", 29.99, 40, "Optimum Nutrition", "Whey Protein Powder"),
            new Product(0, "Running Shoes", "Clothing", 89.99, 35, "Adidas", "Lightweight Running Shoes")
        };

        // Add sample products to database
        for (Product product : sampleProducts) {
            dbManager.addProduct(product);
        }

        System.out.println("Sample data initialized successfully!");
    }

    public static void main(String[] args) {
        DataInitializer initializer = new DataInitializer();
        initializer.initializeData();
    }
} 