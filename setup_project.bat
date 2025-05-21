@echo off
echo Setting up project structure...

:: Create directory structure
mkdir src\main\java\com\inventory\business 2>nul
mkdir src\main\java\com\inventory\data 2>nul
mkdir src\main\java\com\inventory\model 2>nul
mkdir src\main\java\com\inventory\ui 2>nul
mkdir src\main\java\com\inventory\reporting 2>nul

:: Move files to their correct locations
echo Moving files to their correct locations...

if exist Main.java (
    move /Y Main.java src\main\java\com\inventory\
) else (
    echo Warning: Main.java not found
)

if exist Product.java (
    move /Y Product.java src\main\java\com\inventory\model\
) else (
    echo Warning: Product.java not found
)

if exist Inventory.java (
    move /Y Inventory.java src\main\java\com\inventory\business\
) else (
    echo Warning: Inventory.java not found
)

if exist DatabaseManager.java (
    move /Y DatabaseManager.java src\main\java\com\inventory\data\
) else (
    echo Warning: DatabaseManager.java not found
)

if exist InventoryGUI.java (
    move /Y InventoryGUI.java src\main\java\com\inventory\ui\
) else (
    echo Warning: InventoryGUI.java not found
)

:: Remove old class files
echo Cleaning up old class files...
del *.class 2>nul

echo.
echo Project structure has been set up.
echo You can now run build.bat to compile the project.
pause 