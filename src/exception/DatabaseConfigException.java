package exception;

public class DatabaseConfigException extends RuntimeException {
    public DatabaseConfigException(String message) {
        super(message);
    }
}
