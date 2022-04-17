package libraryCatalogue;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class LibraryMember {
    String firstName;
    String lastName;
    private String name;
    private String cardNumber;
    private String dateOfBirth;
    LocalDate membershipExpirationDate;
    private double libraryFees = 0;
    List<CopyOfBook> reservedBooks = new ArrayList<>();
    List<CopyOfBook> booksToBorrow = new ArrayList<>();
    List<CopyOfBook> borrowedBooks = new ArrayList<>();
    static final double MAX_AMOUNT_LIBRARY_FEES = 5.00;
    static final double LATE_FEE_PER_DAY = 0.75;
    static Set<LibraryMember> listOfMembers = new HashSet<>();
    private int uniqueID = 1000;

    public LibraryMember(String firstName,
                         String lastName,
                         String dateOfBirth) {
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

    public void setMembershipExpirationDate() {
       membershipExpirationDate =  LocalDate.now().plusYears(1);
    }

    public double getLibraryFees() {
        calculateLibraryFees();
        return libraryFees;
    }

    public void setLibraryFees(double libraryFees) {
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
}
