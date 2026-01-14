//File Imports

import java.io.File;            //Enables the representaion of the log file      
import java.io.FileWriter;      //Enables text to be written to log file
import java.io.IOException;     //Enables file errors to be handled safely
import java.time.LocalDateTime; //Enables access to current date and time

public class InventoryLogger {

    /*Private field attribute (encapsulation), prevents other classes from directly manipulating file. 
    'logFile' is the file where logs are written.*/

    private File logFile;

    /*Constructor method. Accepts file name, and creates a new File object. The logger now knows where 
    to log. */

    public InventoryLogger(String fileName) {
        logFile = new File(fileName);

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

        LocalDateTime currentTime = LocalDateTime.now();

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
            System.out.println("Error writing to log file");
        }
    }


}
