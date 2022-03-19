package libraryCatalogue;

public class FormatDateException extends RuntimeException {
    String message;

    public FormatDateException (String message) {
        this.message = message;
    }
}
