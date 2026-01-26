--- Inventory Management System ---
---       Language: Java        ---
--- Console Based Application   ---

--- Overview ---

This Java program is a console-based inventory management system. The application enables users to add, delete, modify and view components within the database. 
The system also enables the creation, viewing and sotrage of operational logs; additional quantity notifications enable the user to be made aware of low stock 
components. The program contains heavy input validation, ensuring only verified data can access the system. At runtime the database is stored within memory as 
a Java ArrayList; when the application is closed, the database persists as a CSV file. 

--- File Overview ---

Component.java - The class that contains the blueprint for each component, with getter and setter methods 
ComponentManager.java - Contains the inventory ArrayList, and manages all operations on the database
InputValidator.java - Validates all user inputs
InventoryLogger.java - Records all inventory actions in an operational log
NotificationManager.java - Checks the stock quantity of an object and sends notifications if a quantity drops below a threshold
Main.java- Controls the program flow and interfaces with the user inputs
