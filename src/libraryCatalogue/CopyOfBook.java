package libraryCatalogue;

import java.time.LocalDate;
import java.util.ArrayList;

public class CopyOfBook extends Book {
    boolean availability = true;
    LocalDate dueDate;
    String callNumber;
    int baseBarcode = 1;
    private final int barcode;
    ArrayList<LibraryMember> listOfReservations = new ArrayList<>();

    public String toString() {
        return "Title: " + getTitle() + "\nAuthor: " +getAuthor() + "\nCall number: " + getCallNumber() + "\nLanguage: " + getLanguage()
                + "\nBarcode: "+ getBarcode();
    }

    public CopyOfBook(String title, String author, String isbn, String callNumber, Language language) {
        super(title, author, isbn, language);
        this.callNumber = callNumber;
        this.barcode = baseBarcode;
        baseBarcode++;
    }

    public int getBarcode() {
        return barcode;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCallNumber() {
        return callNumber;
    }
}
