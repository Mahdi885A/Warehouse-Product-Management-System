package exception;

public class DatabaseRepositoryException extends RuntimeException {
    public DatabaseRepositoryException(String message) {
        super(message);
    }
}
