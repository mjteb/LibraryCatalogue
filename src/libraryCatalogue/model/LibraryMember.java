package libraryCatalogue.model;

import libraryCatalogue.LibraryManagementSystem;
import libraryCatalogue.errors.FormatDateException;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class LibraryMember {
    private String firstName;
    private String lastName;
    private String name;
    private String cardNumber;
    private String dateOfBirth;
    private LocalDate membershipExpirationDate;
    private double libraryFees = 0;
    private List<CopyOfBook> reservedBooks = new ArrayList<>();
    private List<CopyOfBook> booksToBorrow = new ArrayList<>();
    private List<CopyOfBook> borrowedBooks = new ArrayList<>();
    private static final double MAX_AMOUNT_LIBRARY_FEES = 5.00;
    private static final double LATE_FEE_PER_DAY = 0.75;
    private static Set<LibraryMember> listOfMembers = new HashSet<>();
    private int uniqueID = 1000;

    public LibraryMember(final String firstName,
                         final String lastName,
                         final String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = lastName + ", " + firstName;
        this.dateOfBirth = dateOfBirth;
        listOfMembers.add(this);
        setCardNumber();
        setMembershipExpirationDate();
        LibraryManagementSystem.getLibraryMembersList().add(this);
    }

    public String getDateOfBirth()  {
        Pattern pattern = Pattern.compile("^\\d{2}\\d{2}\\d{4}$");
        if (pattern.matcher(dateOfBirth).matches()) {
            return dateOfBirth;
        }
        else {
            throw new FormatDateException("Please respect the proper date format: ddmmyyyy");
        }
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getName() {
        return name;
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber() {
        cardNumber = this.lastName.substring(0,3).toUpperCase() + this.firstName.charAt(0)+ this.getDateOfBirth() + this.setUniqueID();
    }


    public int setUniqueID() {
        for (LibraryMember e : listOfMembers) {
            if (e.getName().equals(this.name) && e.getDateOfBirth().equals(this.dateOfBirth)) {
                uniqueID++;
            }
        }
        return uniqueID;
    }

    public LocalDate getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate() {
       membershipExpirationDate =  LocalDate.now().plusYears(1);
    }

    public double getLibraryFees() {
        calculateLibraryFees();
        return libraryFees;
    }

    public void setLibraryFees(final double libraryFees) {
        this.libraryFees = libraryFees;
    }

    public void calculateLibraryFees() {
        for (CopyOfBook e : borrowedBooks) {
            if (LocalDate.now().isAfter(e.getDueDate())) {
                int numberOfDaysLate = e.getDueDate().until(LocalDate.now()).getDays();
                libraryFees += LATE_FEE_PER_DAY * numberOfDaysLate;
            }
        }
    }


    public boolean verifyExpirationDate() {
        if (LocalDate.now().isAfter(this.membershipExpirationDate)) {
            System.out.println("Your card is expired");
            return false;
        }
            return true;

    }

    public boolean verifyAccess () {
        if (verifyExpirationDate() && getLibraryFees() < MAX_AMOUNT_LIBRARY_FEES) {
            return true;
        }
        System.out.println("There is a problem with your account. Please verify that your card is not expired and that your library fees are paid.");
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setMembershipExpirationDate(final LocalDate membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public List<CopyOfBook> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(final List<CopyOfBook> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    public List<CopyOfBook> getBooksToBorrow() {
        return booksToBorrow;
    }

    public void setBooksToBorrow(List<CopyOfBook> booksToBorrow) {
        this.booksToBorrow = booksToBorrow;
    }

    public List<CopyOfBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<CopyOfBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public static Set<LibraryMember> getListOfMembers() {
        return listOfMembers;
    }

    public static void setListOfMembers(Set<LibraryMember> listOfMembers) {
        LibraryMember.listOfMembers = listOfMembers;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }
}
