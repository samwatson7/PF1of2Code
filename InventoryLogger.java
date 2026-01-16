//File Imports

import java.io.File;                       //Enables the representaion of the log file      
import java.io.FileWriter;                 //Enables text to be written to log file
import java.io.IOException;                //Enables file errors to be handled safely
import java.time.LocalDateTime;            //Enables access to current date and time
import java.time.format.DateTimeFormatter; //Enables DateTime to be formatted correctly 

public class InventoryLogger {

    /*Private field attribute (encapsulation), prevents other classes from directly manipulating file. 
    'logFile' is the file where logs are written. 'Formatter', ensures timestamps are formatted.*/

    private File logFile;
    private DateTimeFormatter formatter;

    /*Constructor method. Accepts file name, and creates a new File object. The logger now knows where 
    to log. */

    public InventoryLogger(String fileName) {
        this.logFile = new File(fileName);
        this.formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");


    /*try/catch statement checks if file already exists, and creates it if it is missing. Preventing 
    program crash. */    

    try {
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
    } catch (IOException e) {
        System.out.println("Error creating log file.");
        }

    }

    /*Method to log an action on the Component System. The logger takes the inputs 'username', 'action', 
    'componentName', 'quantityChange', and uses them to format a string to be added to the Logger File. */

    public void logAction(String username, String action, String componentName, int quantity) {

        String currentTime = LocalDateTime.now().format(formatter);

        /*Creates a formatted string called 'logEntry'. This string can be added into the Logger File. */

        String logEntry = currentTime + " | Username: " + username
                         + " | Action: " + action 
                         + " | Component: " + componentName 
                         + " | Quantity: " + quantity;

        /*Try with resources. The 'logWriter' opens the 'logFile' in append mode (ensuring no overwriting of 
        exising data). The formatted string is written and separated from the next line for readability. The 
        file is automatically closed after this block of code is executed, preventing file corruption. */

        try (FileWriter logWriter = new FileWriter(logFile, true)){
            logWriter.write(logEntry + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to log file" + e.getMessage());
        }
    }

        /*Method opens the logFile for reading. It reads it line by line and prints each line to the terminal. 
        Any errors are handled gracefully, if the file is missing or cannot be read.*/

    public void displayLog() {
        try (java.util.Scanner fileScanner = new java.util.Scanner(logFile)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }
    }
}
