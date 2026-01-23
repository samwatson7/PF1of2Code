//Library Imports 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

 

        //Scanner Declaration
        Scanner scanner = new Scanner(System.in);

        ///////////////////////////////
        //Object Instance Declaration//
        ///////////////////////////////

        //InventoryLogger Object
        InventoryLogger logFile = new InventoryLogger("inventory_log.txt");

        //NotificationManager Object
        NotificationManager notificationManager = new NotificationManager();

        //ComponentManager Object
        ComponentManager componentManager = new ComponentManager(logFile, notificationManager);

        //Reload the stored inventory file, into the ArrayList
        componentManager.loadInventory();


        //Boolean variable to signify the program is running or when quit has been hit.
        boolean running = true;

        /*While loop, ensures the main menu continually reappeares after each action. Unless, 
        quit has been hit*/

        while (running) {
            System.out.println("\n ---Component Tracking Tool ---\n");
            System.out.println("1. Add Item to the Component Database\n");
            System.out.println("2. Delete an Item from the Component Database\n");
            System.out.println("3. Modify a Component's Quantity\n");
            System.out.println("4. View the Component Database\n");
            System.out.println("5. View the Operational Log\n");
            System.out.println("6. Quit\n");
            System.out.println("Select an Option: ");

            //Scans the user input line and stores the String value in the variable 'menuInput'
            String menuInput = scanner.nextLine();

            //Vaidates integer input and stores the returned value in the variable 'userMenuChoice'
            Integer userMenuChoice = InputValidator.validateInteger(menuInput, 1, 6);

            /*Checks if InputValidator returned value is invalid, if so an error message is displayed, 
            and the Main Menu options are re-displayed to the user.*/
            if (userMenuChoice == null) {
                System.out.println("Invalid Menu Option.");
                continue;
            }

            //Switch statement is used to direct the data flow dependent on the user input ('userMenuChoice')
            switch (userMenuChoice) {

                /* Case 1 represents the add component to database action. The user is propted for the information, 
                the inputs are verified and are used to create a new component object. */
                case 1:

                    //Component name collection, verification and storage.
                    System.out.println("Enter a component name: ");
                    String componentNameInput = scanner.nextLine();
                    String postValidationName = InputValidator.validateString(componentNameInput, 50);

                    if (postValidationName == null) {
                        System.out.println("Invalid name. This operation is cancelled.");
                        break;
                    } 

                    //Component quantity collection, verification and storage.
                    System.out.println("Enter a component Quantity: ");
                    String componentQuantityInput = scanner.nextLine();
                    Integer postValidationQuantity = InputValidator.validateInteger(componentQuantityInput, 0, Integer.MAX_VALUE);

                    if (postValidationQuantity == null) {
                        System.out.println("Invalid quantity. This operation is cancelled.");
                        break;
                    }

                    //Component threshold collection, verification and storage.
                    System.out.println("Enter component threshold: ");
                    String componentThresholdInput = scanner.nextLine();
                    Integer postValidationThreshold = InputValidator.validateInteger(componentThresholdInput, 0, Integer.MAX_VALUE);

                    if (postValidationThreshold == null) {
                        System.out.println("Invalid Threshold. This operation is cancelled.");
                        break;
                    }

                    //Username collection, verification and storage.
                    System.out.println("Enter your username: ");
                    String usernameInput = scanner.nextLine();
                    String postValidationUsername = InputValidator.validateString(usernameInput, 30);

                    if (postValidationUsername == null) {
                        System.out.println("Invalid username. This operation is cancelled.");
                        break;
                    }

                    //Generation of new ID
                    int newId = componentManager.getNextComponentId();

                    //Creation of new component
                    Component newComponent = new Component(newId, postValidationName, postValidationQuantity, postValidationThreshold);

                    //Addition of new component to database.
                    componentManager.addComponent(newComponent, postValidationUsername);

                    //Success Message.
                    System.out.println("Component added successfully!");

                    break;

                
                /* Case 2 represents the delete component from the database action. The user is propted for the information, 
                the inputs are verified and are used to delete a component object from the database. */
                case 2:

                    //Component ID collection, verification and storage.
                    System.out.println("Enter a component ID for deletion: ");
                    String deletionIdInput = scanner.nextLine();
                    Integer postValidationId = InputValidator.validateInteger(deletionIdInput, 1, Integer.MAX_VALUE);

                    if (postValidationId == null) {
                        System.out.println("Invalid ID. This operation is cancelled.");
                        break;
                    }

                    //Username collection, verification and storage.
                    System.out.println("Enter your username: ");
                    String usernameInput2 = scanner.nextLine();
                    String postValidationUsername2 = InputValidator.validateString(usernameInput2, 30);

                    if (postValidationUsername2 == null) {
                        System.out.println("Invalid Username. This operation is cancelled.");
                        break;
                    }

                    //Checks if deletion was successful, displaying representative message.
                    boolean deletionSuccessful = componentManager.deleteComponent(postValidationId, postValidationUsername2);

                    if (deletionSuccessful) {
                        System.out.println("Component deleted successfully.");
                    } else {
                        System.out.println("Component ID not found.");
                    }

                    break;

                /* Case 3 represents the modify component quantiy aciton. The user is propted for the information, the inputs 
                are verified and are used to modify the components quantity. */
                case 3:

                    //Component ID collection, verification and storage.
                    System.out.println("Enter a component ID for quantity modification: ");
                    String quantityModificationIdInput = scanner.nextLine();
                    Integer postValidationId2 = InputValidator.validateInteger(quantityModificationIdInput, 1, Integer.MAX_VALUE);

                    if (postValidationId2 == null) {
                        System.out.println("Invalid ID. This operation is cancelled.");
                        break;
                    }

                    //New component quantity collection, verification and storage.
                    System.out.println("Enter a new quantity: ");
                    String newQuantityInput = scanner.nextLine();
                    Integer postValidationNewQuantity = InputValidator.validateInteger(newQuantityInput, 0, Integer.MAX_VALUE);

                    if (postValidationNewQuantity == null) {
                        System.out.println("Invalid quantity. This operation is cancelled.");
                        break;
                    }

                    //Username collection, verification and storage.
                    System.out.println("Enter your username: ");
                    String usernameInput3 = scanner.nextLine();
                    String postValidationUsername3 = InputValidator.validateString(usernameInput3, 30);

                    if (postValidationUsername3 == null) {
                        System.out.println("Invalid username. This operation is cancelled.");
                        break;
                    }

                    //Checks if modification was successful, displaying representative message.
                    boolean modificationSuccessful = componentManager.modifyQuantity(postValidationId2, postValidationNewQuantity, postValidationUsername3);

                    if (modificationSuccessful) {
                        System.out.println("Component Quantity updated successfully.");
                    } else {
                        System.out.println("Component ID not found.");
                    }

                    break;

                /* Case 4 represents the displaying of the component database. */
                case 4:
                    System.out.println("\n --- Component Database ---\n");
                    componentManager.displayAllComponents();
                    break;

                 /* Case 5 represents the displaying of the operational log. */
                case 5:
                    System.out.println("\n--- Operational Log ---\n");
                    logFile.displayLog();
                    break;

                /* Case 6 represents the displaying of the quitting of the program. And the saving of the ArrayList to the CSV File.*/
                case 6:
                    componentManager.saveInventory();
                    System.out.println("Exiting Component Tracking Tool. Goodbye!");
                    running = false;
                    break;
 
            }
        } 
        //Closing the scanner
        scanner.close();
    }
}
