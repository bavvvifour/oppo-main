package sfu;

/**
 * Exception thrown when invalid input is encountered.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     */
    public InvalidInputException() {
        super();
    }

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval
     * by the {@link #getMessage()} method.
     */
    public InvalidInputException(final String message) {
        super(message);
    }
}
