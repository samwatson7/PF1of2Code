public class Component {
    
    //Field Attributes for each componenet
    private final int id;
    private final String name;
    private int quantity;
    private final int threshold;

    //Component Constructor Method
    public Component(int inputId, String inputName, int inputQuantity, int inputThreshold) {
        id        = inputId;
        name      = inputName;
        quantity  = inputQuantity;
        threshold = inputThreshold;
    }
    
    //id Getter Method
    public int getId() {
        return id;
    }

    //name Getter Method
    public String getName() {
        return name;
    }

    //quantity Getter Method
    public int getQuantity() {
        return quantity;
    }

    //threshold Getter Method
    public int getThreshold() {
        return threshold;
    }

    //quantity Setter Method
    public void setQuantity(int newQuantity){
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        }
    }

    //Formatted Component Details String for Display 
    public String getDisplayString() {
        return "Component ID:" + id + ", Name:" + name + ", Quantity:" + quantity + ", Threshold:" + threshold;
    }
}


