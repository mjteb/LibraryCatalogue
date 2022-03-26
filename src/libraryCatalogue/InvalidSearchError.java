package libraryCatalogue;

public class InvalidSearchError extends RuntimeException {
    String message;
    public InvalidSearchError (String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidSearchError{" +
                "message='" + message + '\'' +
                '}';
    }
}
