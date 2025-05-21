package src.main.java.com.inventory.business;

import src.main.java.com.inventory.data.DatabaseManager;
import src.main.java.com.inventory.model.Product;
import java.util.List;

public class Inventory {
    private final DatabaseManager dbManager;

    public Inventory() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        if (product.getBrand() == null || product.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Product brand cannot be empty");
        }
        dbManager.addProduct(product);
    }

    public void updateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        if (product.getBrand() == null || product.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Product brand cannot be empty");
        }
        dbManager.updateProduct(product);
    }

    public void deleteProduct(int id) {
        dbManager.deleteProduct(id);
    }

    public List<Product> getProducts() {
        return dbManager.getProducts();
    }

    public Product getProduct(int id) {
        return dbManager.getProduct(id);
    }

    public List<Product> searchProducts(String query) {
        return dbManager.searchProducts(query);
    }

    public void updateStock(int productId, int quantity) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        Product product = dbManager.getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        product.setQuantity(quantity);
        dbManager.updateProduct(product);
    }

    public void close() {
        dbManager.close();
    }
} 