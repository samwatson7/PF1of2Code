//Library Imports
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComponentManager {


    ////////////////////////////////////
    //Fields for the ComponentManager//
    ////////////////////////////////////
    
    //ArrayList made up of multiple Component, named 'components'. Stores all of the components.
    private ArrayList<Component> components;

    //CSV file to store the component database, when program is not actively running
    private static final String inventoryStorageFile = "inventory_storage_file.csv";

    //Singular shared logger instance. This ensures all actions are recorded consistently.
    private InventoryLogger logFile;

    //Handles low-stock alerts 
    private NotificationManager notificationManager;


    ///////////////////////////////////////////
    //Constructor Method for ComponentManager//
    ///////////////////////////////////////////
    
    //Creates an empty component list. Receives logFile and notificationManager.
    public ComponentManager(InventoryLogger inputLogFile, NotificationManager inpuNotificationManager) {
        components = new ArrayList<>();
        this.logFile = inputLogFile;
        this.notificationManager = inpuNotificationManager;
    }


    //////////////////////////////////////////////
    //Functionality Methods for ComponentManager//
    //////////////////////////////////////////////

    /*Method to add a component to the database (ArrayList). Component is already 
    validated before this point. Component Manager owns the database, therefore 
    this is the correct place to add new components to the database.*/
    
    public void addComponent(Component component, String username) {
        
        components.add(component);

        logFile.logAction(username, "ADD COMPONENT", component.getName(), component.getQuantity());
    }

    /*Method to find a Component ID from within the database. If the component ID 
    is found, the componet will be returned. If the component ID isn't found, null 
    will be returned. The private access modifier ensures encapsulation; only 
    ComponentManager can search the list.*/

    private Component findComponentById(int id) {
        for (Component component : components) {
            if (component.getId() == id) {
                return component;
            }
        }
        return null;
    }

    /*Method to find the highest current ID, and generate the next ID for a newly added 
    component. Loops through all components, finds the highest ID currently, and returns 
    next available ID.*/

    public int getNextComponentId() {
        int maxId = 0;
        for (Component c : components) {
            if (c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        return maxId + 1;
    }

    /*Method to delete a component from the database. Finds the component using its ID, 
    and stores it in 'component'. If the ID cannot be found, false is returned. If the 
    ID is found, the component is removed, the action is logged and true is returned. 
    The Main program uses the boolean value to signify success or failure.*/

    public boolean deleteComponent(int id, String username) {

    Component component = findComponentById(id);

    if (component == null) {
        return false;
    }

    components.remove(component);

    logFile.logAction(username, "DELETE COMPONENT", component.getName(), component.getQuantity());

    return true;

    }

    /*Method to modify a components quantity. Finds the component using its ID, and 
    stores it in 'component'. If the ID cannot be found, false is returned. If the 
    ID is found, the component quantity is modified, the action is logged and true is returned. 
    The Main program uses the boolean value to signify success or failiure.*/

    public boolean modifyQuantity(int id, int newQuantity, String username) {
        Component component = findComponentById(id);

        if (component == null) {
            return false;
        }

        component.setQuantity(newQuantity);

        logFile.logAction(username, "MODIFY QUANTITY", component.getName(), newQuantity);

        notificationManager.checkLowStock(component);

        return true;
    }

    /*Method to display all components in the database. The method checks if the inventory is empty, 
    if so, it prints a message. If the database is not empty, each component in the database has its 
    fields displayed. */

    public void displayAllComponents() {
        if (components.isEmpty()) {
            System.out.println("There are no components in the database.");
            return;
        }

        for (Component component : components) {
            System.out.println(component.getDisplayString());
        }
    }

    /*Method to save the memory ArrayList<Component> to a storage CSV file. CSV file is opened in overwrite mode. 
    Try with resources ensures file closes automatically. ArrayList<Component> is iterated through and for every 
    Component, the inventoryWriter writes (id,name,quantity,threshold). A new line is is created for every 
    component. If the writter is unable to write to the file, the error is caught, and an error message is printed.  */

    public void saveInventory(){
        try (FileWriter inventoryWriter = new FileWriter(inventoryStorageFile)) {
            
            for (Component component : components) {
                inventoryWriter.write(
                    component.getId() + "," + 
                    component.getName() + "," + 
                    component.getQuantity() + "," + 
                    component.getThreshold() + "\n"
                );
            } } catch (IOException e) {
                System.out.println("Error saving inventory to file");
            }

        }

    /*Method to load all previously saved components from the CSV file into the programs memory database 
    (ArrayList<Component>). Try with resources opens the inventoryStorageFile for reading. The file is 
    auto closed after use. 'line' is a temporary variable that stores each CSV line during reading. The 
    CSV is read line by line, stopping at the end (when the line is null). The CSV line is split into 
    substrings representing each component field. Id, quantity, and threshold are converted into integers, 
    while name remains a string. Each object is reconstructed and added back to the ArrayList<Component>. 
    If the file cannot be read, the error is caught and and error message is displayed.*/

    public void loadInventory() {
        try (BufferedReader inventoryReader = new BufferedReader(new FileReader(inventoryStorageFile))) {

            String line;

            while ((line = inventoryReader.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length != 4) continue;

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int quantity = Integer.parseInt(data[2]);
                int threshold = Integer.parseInt(data[3]);

                Component component = new Component(id, name, quantity, threshold);
                components.add(component);
            }
        } catch (IOException e) {
            System.out.println("Unable to load inventory. Starting a new inventory file.");
        }

    }

    }
