package Exceptions;

/**
 * Exception that gets thrown when the command requirement for tokens amount wasn't met
 */

public class TokenMismatchException extends Exception{
    public TokenMismatchException(int tokensRequired) {
        super("Incorrect amount of tokens. Need " + tokensRequired + "\nType 'help' to learn about commands");
    }
}
