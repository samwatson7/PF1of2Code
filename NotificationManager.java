public class NotificationManager {

    /*Method to determine is a specific, individual components quantity is less than its threshold. 
    If it is, the sendNotification method is called. */
    
    public void checkLowStock(Component component) {
        
        if (component.getQuantity() < component.getThreshold()) {
            sendNotification(component);
        } 
    }

    /*Method to print a string detailing the quantity-threshold unbalance, when called on a specific component. */

    private void sendNotification(Component component) {
        System.out.println("WARNING: Component '" 
                    + component.getName() 
                    + "' is below its threshold. Current quantity: " 
                    + component.getQuantity());
    }
    
}
