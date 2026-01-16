//ArrayList Library Import
import java.util.ArrayList;

public class ComponentManager {


    ////////////////////////////////////
    //Fields for the ComponentManager//
    ////////////////////////////////////
    
    //ArrayList made up of multiple Component, named 'components'. Stores all of the components.
    private ArrayList<Component> components;

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
}