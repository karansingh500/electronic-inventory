@echo off
echo Initializing sample data...

java -cp "bin;lib/*" src.main.java.com.inventory.data.DataInitializer

if errorlevel 1 (
    echo Failed to initialize sample data!
    pause
    exit /b 1
)

echo Sample data initialized successfully!
pause 