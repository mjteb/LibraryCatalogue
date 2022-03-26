package libraryCatalogue.errors;

public class InvalidSearchException extends RuntimeException {
    private String message;

    public InvalidSearchException(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidSearchError{" +
                "message='" + message + '\'' +
                '}';
    }
}
