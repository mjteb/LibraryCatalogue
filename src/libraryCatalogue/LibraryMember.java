package libraryCatalogue;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LibraryMember {
    String name;
    String cardNumber;
    LocalDate membershipExpirationDate;
    double libraryFees;
    List<CopyOfBook> reservedBooks;
    List<CopyOfBook> booksToBorrow;
    List<CopyOfBook> borrowedBooks;
    static final double MAX_AMOUNT_LIBRARY_FEES = 5.00;
    static final double LATE_FEE_PER_DAY = 0.75;


    public LibraryMember(String name, String cardNumber, LocalDate membershipExpirationDate, double libraryFees, List<CopyOfBook> reservedBooks, List<CopyOfBook> booksToBorrow, List<CopyOfBook> borrowedBooks) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.membershipExpirationDate = membershipExpirationDate;
        this.libraryFees = libraryFees;
        this.reservedBooks = reservedBooks;
        this.booksToBorrow = booksToBorrow;
        this.borrowedBooks = borrowedBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(LocalDate membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public double getLibraryFees() {
        getLibraryFees();
        return libraryFees;
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
        if (verifyExpirationDate() && getLibraryFees() > MAX_AMOUNT_LIBRARY_FEES) {
            return true;
        }
        System.out.println("There is a problem with your account. Please verify that your card is not expired and that your library fees are paid.");
        return false;
    }




}
