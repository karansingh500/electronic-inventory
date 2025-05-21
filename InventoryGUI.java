import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.main.java.com.inventory.business.Inventory;

import java.awt.*;
import java.awt.event.*;

public class InventoryGUI extends JFrame {
    private Inventory inventory;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, categoryField, priceField, quantityField, supplierField, descriptionField;
    private JButton addButton, removeButton, updateButton, clearButton;

    public InventoryGUI() {
        inventory = new Inventory();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table
        String[] columnNames = {"ID", "Name", "Category", "Price", "Quantity", "Supplier", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add input fields
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        categoryField = new JTextField(20);
        inputPanel.add(categoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField(20);
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        inputPanel.add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Supplier:"), gbc);
        gbc.gridx = 1;
        supplierField = new JTextField(20);
        inputPanel.add(supplierField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionField = new JTextField(20);
        inputPanel.add(descriptionField, gbc);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        addButton = new JButton("Add Product");
        removeButton = new JButton("Remove Selected");
        updateButton = new JButton("Update Selected");
        clearButton = new JButton("Clear Fields");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        // Add panels to main panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addProduct());
        removeButton.addActionListener(e -> removeProduct());
        updateButton.addActionListener(e -> updateProduct());
        clearButton.addActionListener(e -> clearFields());

        // Add table selection listener
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    categoryField.setText((String) tableModel.getValueAt(selectedRow, 2));
                    priceField.setText(tableModel.getValueAt(selectedRow, 3).toString().replace("â‚¹", ""));
                    quantityField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    supplierField.setText((String) tableModel.getValueAt(selectedRow, 5));
                    descriptionField.setText((String) tableModel.getValueAt(selectedRow, 6));
                }
            }
        });

        add(mainPanel);
        refreshTable();
    }

    private void addProduct() {
        try {
            String name = nameField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String supplier = supplierField.getText();
            String description = descriptionField.getText();

            if (name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and description cannot be empty!");
                return;
            }

            if (price <= 0 || quantity < 0) {
                JOptionPane.showMessageDialog(this, "Price must be positive and quantity must be non-negative!");
                return;
            }

            inventory.addProduct(name, category, price, quantity, supplier, description);
            refreshTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity!");
        }
    }

    private void removeProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to remove!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        inventory.removeProduct(id);
        refreshTable();
        clearFields();
    }

    private void updateProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to update!");
            return;
        }

        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String supplier = supplierField.getText();
            String description = descriptionField.getText();

            if (name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and description cannot be empty!");
                return;
            }

            if (price <= 0 || quantity < 0) {
                JOptionPane.showMessageDialog(this, "Price must be positive and quantity must be non-negative!");
                return;
            }

            inventory.updateProduct(id, name, category, price, quantity, supplier, description);
            refreshTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity!");
        }
    }

    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
        supplierField.setText("");
        descriptionField.setText("");
        productTable.clearSelection();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product product : inventory.getProducts()) {
            Object[] row = {
                product.getId(),
                product.getName(),
                product.getCategory(),
                String.format("â‚¹%.2f", product.getPrice()),
                product.getQuantity(),
                product.getSupplier(),
                product.getDescription()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryGUI().setVisible(true);
        });
    }
} 