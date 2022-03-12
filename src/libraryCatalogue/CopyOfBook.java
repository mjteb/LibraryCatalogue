package libraryCatalogue;

import java.time.LocalDate;
import java.util.ArrayList;

public class CopyOfBook extends Book {
    boolean availability;
    LocalDate dueDate;
    String callNumber;
    double price;
    ArrayList<LibraryMember> listOfReservations;


    public CopyOfBook(String title, String author, String isbn, boolean availability, LocalDate dueDate, String callNumber, Language language) {
        super(title, author, isbn, language);
        this.availability = availability;
        this.dueDate = dueDate;
        this.callNumber = callNumber;
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

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
