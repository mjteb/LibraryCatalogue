package libraryCatalogue.model;

import libraryCatalogue.Language;

import java.time.LocalDate;
import java.util.ArrayList;

public class CopyOfBook extends Book {
    private boolean availability = true;
    private LocalDate dueDate;
    private String callNumber;
    private double price;
    private int baseBarcode = 1;
    private final int barcode;
    private ArrayList<LibraryMember> listOfReservations = new ArrayList<>();

    public String toString() {
        return "Title: ".concat(getTitle())
                .concat("\n")
                .concat("Author: ").concat(getAuthor())
                .concat("\n")
                .concat("Call number: ").concat(getCallNumber())
                .concat("\n")
                .concat("Language: ").concat(getLanguage().toString())
                .concat("\n")
                .concat("Barcode: ").concat(String.valueOf(getBarcode()));
    }

    public CopyOfBook(final String title, final String author, final String isbn, final String callNumber, final Language language) {
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

    public void setAvailability(final boolean availability) {
        this.availability = availability;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(final LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public boolean isAvailability() {
        return availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public int getBaseBarcode() {
        return baseBarcode;
    }

    public void setBaseBarcode(final int baseBarcode) {
        this.baseBarcode = baseBarcode;
    }

    public ArrayList<LibraryMember> getListOfReservations() {
        return listOfReservations;
    }

    public void setListOfReservations(final ArrayList<LibraryMember> listOfReservations) {
        this.listOfReservations = listOfReservations;
    }

    public void setCallNumber(final String callNumber) {
        this.callNumber = callNumber;
    }
}
