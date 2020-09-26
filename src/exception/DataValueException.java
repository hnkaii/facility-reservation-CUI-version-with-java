package exception;

public class DataValueException extends Exception {

    public DataValueException() {
        super();
    }

    public DataValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataValueException(String message) {
        super(message);
    }

    public DataValueException(Throwable cause) {
        super(cause);
    }
}
