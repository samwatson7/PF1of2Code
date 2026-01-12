public class InputValidator {

//Method to Validate an Integer Input
public static Integer isValidInteger(String intInput, int rangeMinimum, int rangeMaximum) {
    try {
        int convertedIntInput = Integer.parseInt(intInput);

        if (convertedIntInput < rangeMinimum || convertedIntInput > rangeMaximum) {
            return null;
        }

        return convertedIntInput;

    } catch (NumberFormatException e) {
        return null;
    }
}
}