# Electronic Inventory Management System

A Java-based inventory management system designed for small to medium-sized businesses to efficiently track and manage their stock levels.

## Features

- Add, update, and delete products
- Real-time stock level tracking
- Search and filter products by name, category, or supplier
- Barcode support for future integration
- User-friendly graphical interface
- SQLite database for persistent storage
- Input validation and error handling

## System Requirements

- Java Development Kit (JDK) 17 or later
- SQLite JDBC Driver (included in lib directory)
- Windows operating system (for batch files)

## Installation

1. Clone or download the repository
2. Download the SQLite JDBC driver (sqlite-jdbc-3.45.1.0.jar) and place it in the `lib` directory
3. Create the `lib` directory if it doesn't exist:
   ```
   mkdir lib
   ```

## Building and Running

1. Compile the application:
   ```
   .\build.bat
   ```

2. Run the application:
   ```
   .\run.bat
   ```

## Project Structure

```
src/main/java/com/inventory/
├── Main.java                 # Application entry point
├── business/
│   └── Inventory.java        # Business logic layer
├── data/
│   └── DatabaseManager.java  # Database operations
├── model/
│   └── Product.java          # Product data model
└── ui/
    └── InventoryGUI.java     # Graphical user interface
```

## Development

The project follows a layered architecture:

1. **UI Layer** (`ui` package)
   - Handles user interface and input validation
   - Uses Java Swing for the graphical interface

2. **Business Layer** (`business` package)
   - Implements business logic and rules
   - Validates data and manages transactions

3. **Data Layer** (`data` package)
   - Manages database operations
   - Uses SQLite for data persistence

4. **Model Layer** (`model` package)
   - Defines data structures
   - Contains the Product class

## Future Enhancements

- Multi-user authentication
- Barcode scanning integration
- Advanced reporting and analytics
- Export/import functionality
- Backup and restore features

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 