@echo off
echo Building Electronic Inventory Management System...

:: Create bin directory if it doesn't exist
if not exist bin mkdir bin

:: Set source directory
set SRC_DIR=src\main\java

:: Compile Java files in the correct order
javac -d bin -cp "lib/*" ^
    %SRC_DIR%\com\inventory\model\Product.java ^
    %SRC_DIR%\com\inventory\data\DatabaseManager.java ^
    %SRC_DIR%\com\inventory\data\DataInitializer.java ^
    %SRC_DIR%\com\inventory\business\Inventory.java ^
    %SRC_DIR%\com\inventory\ui\InventoryGUI.java ^
    %SRC_DIR%\com\inventory\Main.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo To run the application, use: java -cp "bin;lib/*" src.main.java.com.inventory.Main
echo To initialize sample data, use: java -cp "bin;lib/*" src.main.java.com.inventory.data.DataInitializer
echo.
pause 