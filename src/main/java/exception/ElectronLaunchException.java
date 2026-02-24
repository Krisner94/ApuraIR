package exception;

public class ElectronLaunchException extends RuntimeException {

    public ElectronLaunchException(String message, Throwable cause) {
        super(message, cause);
    }
}