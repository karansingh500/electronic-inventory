package com.inventory.business;

import com.inventory.model.Product;
import java.util.List;

public interface InventoryService {
    void addProduct(String name, String category, double price, int quantity, String supplier, String description);
    void removeProduct(int id);
    void updateProduct(int id, String name, String category, double price, int quantity, String supplier, String description);
    List<Product> getAllProducts();
    Product getProduct(int id);
    void updateStock(int id, int quantity);
} 