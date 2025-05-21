package src.main.java.com.inventory.ui;

import src.main.java.com.inventory.business.Inventory;
import src.main.java.com.inventory.model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class InventoryGUI extends JFrame {
    private final Inventory inventory;
    private final JTable productTable;
    private final DefaultTableModel tableModel;
    private final JTextField searchField;
    private final JComboBox<String> categoryFilter;
    private final NumberFormat currencyFormat;

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create top panel for search and filter
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        categoryFilter = new JComboBox<>(new String[]{"All Categories", "Electronics", "Clothing", "Food", "Furniture", "Stationery"});
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryFilter);
        topPanel.add(searchButton);
        topPanel.add(addButton);
        topPanel.add(removeButton);

        // Create table
        String[] columns = {"ID", "Name", "Category", "Price", "Quantity", "Brand", "Description"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Add action listeners
        searchButton.addActionListener(e -> refreshTable());
        addButton.addActionListener(e -> showAddProductDialog());
        removeButton.addActionListener(e -> showRemoveProductDialog());
        categoryFilter.addActionListener(e -> refreshTable());

        // Add double-click listener for editing
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = productTable.getSelectedRow();
                    if (row != -1) {
                        int id = (int) tableModel.getValueAt(row, 0);
                        Product product = inventory.getProduct(id);
                        if (product != null) {
                            showEditProductDialog(product);
                        }
                    }
                }
            }
        });

        // Initial table refresh
        refreshTable();
    }

    private void showRemoveProductDialog() {
        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Enter Product ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Remove Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Product product = inventory.getProduct(id);
                if (product != null) {
                    int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to remove this product?\n" +
                        "ID: " + product.getId() + "\n" +
                        "Name: " + product.getName() + "\n" +
                        "Category: " + product.getCategory(),
                        "Confirm Removal",
                        JOptionPane.YES_NO_OPTION
                    );
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        inventory.deleteProduct(id);
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "Product removed successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Product with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        String searchText = searchField.getText().trim();
        String selectedCategory = (String) categoryFilter.getSelectedItem();

        List<Product> products;
        if (!searchText.isEmpty()) {
            products = inventory.searchProducts(searchText);
        } else {
            products = inventory.getProducts();
        }

        for (Product product : products) {
            if (selectedCategory.equals("All Categories") || product.getCategory().equals(selectedCategory)) {
                tableModel.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    currencyFormat.format(product.getPrice()),
                    product.getQuantity(),
                    product.getBrand(),
                    product.getDescription()
                });
            }
        }
    }

    private void showAddProductDialog() {
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField descriptionField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Price (₹):"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Product product = new Product(
                    0,
                    nameField.getText(),
                    categoryField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    brandField.getText(),
                    descriptionField.getText()
                );
                inventory.addProduct(product);
                refreshTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void showEditProductDialog(Product product) {
        JTextField nameField = new JTextField(product.getName());
        JTextField categoryField = new JTextField(product.getCategory());
        JTextField priceField = new JTextField(String.valueOf(product.getPrice()));
        JTextField quantityField = new JTextField(String.valueOf(product.getQuantity()));
        JTextField brandField = new JTextField(product.getBrand());
        JTextField descriptionField = new JTextField(product.getDescription());

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Price (₹):"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                product.setName(nameField.getText());
                product.setCategory(categoryField.getText());
                product.setPrice(Double.parseDouble(priceField.getText()));
                product.setQuantity(Integer.parseInt(quantityField.getText()));
                product.setBrand(brandField.getText());
                product.setDescription(descriptionField.getText());
                inventory.updateProduct(product);
                refreshTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
} 