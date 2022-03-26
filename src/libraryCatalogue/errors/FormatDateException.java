package libraryCatalogue.errors;

public class FormatDateException extends RuntimeException {
    private String message;

    public FormatDateException (final String message) {
        this.message = message;
    }
}
