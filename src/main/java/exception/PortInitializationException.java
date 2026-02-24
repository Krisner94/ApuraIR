package exception;

public class PortInitializationException extends RuntimeException {

    public PortInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}