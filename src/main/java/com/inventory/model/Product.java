package src.main.java.com.inventory.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String brand;
    private String description;

    public Product(int id, String name, String category, double price, int quantity, String brand, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.description = description;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Category: %s | Price: ₹%.2f | Quantity: %d | Brand: %s | Description: %s",
                id, name, category, price, quantity, brand, description);
    }
} 