package com.inventory.business;

import com.inventory.data.DatabaseManager;
import com.inventory.model.Product;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private final DatabaseManager dbManager;

    public InventoryServiceImpl(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void addProduct(String name, String category, double price, int quantity, String supplier, String description) {
        Product product = new Product(0, name, category, price, quantity, supplier, description);
        dbManager.addProduct(product);
    }

    @Override
    public void removeProduct(int id) {
        dbManager.deleteProduct(id);
    }

    @Override
    public void updateProduct(int id, String name, String category, double price, int quantity, String supplier, String description) {
        Product product = new Product(id, name, category, price, quantity, supplier, description);
        dbManager.updateProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return dbManager.getAllProducts();
    }

    @Override
    public Product getProduct(int id) {
        return dbManager.getProduct(id);
    }

    @Override
    public void updateStock(int id, int quantity) {
        Product product = getProduct(id);
        if (product != null) {
            product.setQuantity(quantity);
            dbManager.updateProduct(product);
        }
    }
} 