package Exceptions;

/**
 * Exception that gets raised when input data from a script is invalid
 */

public class IncorrectDataException extends Exception{
    public IncorrectDataException(String message) {
        super("Found wrong format while reading the script. Raised error message: \n" + message);
    }
}
