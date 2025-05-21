@echo off
echo Starting Electronic Inventory Management System...

:: Run the application
java -cp "bin;lib/*" src.main.java.com.inventory.Main

if errorlevel 1 (
    echo Application failed to start!
    pause
    exit /b 1
)

pause 