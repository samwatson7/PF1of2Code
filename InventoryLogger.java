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
    to log.*/

    public InventoryLogger(String fileName) {
        logFile = new File(fileName);
    }

    public void logAction() {

    }
    
}
