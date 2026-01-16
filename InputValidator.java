public class InputValidator {

/* Method to validate an Integer Input. If int is a true integer and can be converted from string 
to int, and the value is within its range, the int value will be returned. Else, 'null' will be returned*/

public static Integer validateInteger(String intInput, int rangeMinimum, int rangeMaximum) {
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

/*Method to validate String Input. If the input isn't 'null', empty or above maximum character length, 
the cleaned String will be returned for use. Else, 'null' will be returned.*/

public static String validateString(String stringInput, int maximumLength) {

    if (stringInput == null) {
        return null;
    }

    String trimmedStringInput = stringInput.trim();

    if (trimmedStringInput.isEmpty()) {
        return null;
    }

    if (trimmedStringInput.length() > maximumLength) {
        return null;
    }

    return trimmedStringInput;
}
}