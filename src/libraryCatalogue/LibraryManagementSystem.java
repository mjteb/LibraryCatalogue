package libraryCatalogue;

import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManagementSystem {
    private static ArrayList<LibraryMember> libraryMembersList;
    private static ArrayList<CopyOfBook> copyOfBookList;
private double libraryAccountBudget;

    public LibraryManagementSystem() {
    }


    public void payingLibraryFees (LibraryMember libraryMember, double amountPaid) {
        libraryMember.libraryFees -= amountPaid;
        libraryAccountBudget += amountPaid;
    }

    public boolean checkBookAvailability(CopyOfBook copyOfBook) {
        if (copyOfBook.getAvailability()) {
            return true;
        }
        return false;
    }

    public void checkOutBook(CopyOfBook copyOfBook, LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            for (CopyOfBook eachBook : libraryMember.booksToBorrow) {
                if (checkBookAvailability(eachBook)) {
                    libraryMember.borrowedBooks.add(eachBook);
                    changeStatusOfBookToUnavailable(eachBook);
                }
            }
        }
    }

    public void returnBook(CopyOfBook copyOfBook, LibraryMember libraryMember) {
        libraryMember.borrowedBooks.remove(copyOfBook);
        changeStatusOfBookToAvailable(copyOfBook);
    }

    public void changeStatusOfBookToUnavailable(CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(false);
    }

    public void changeStatusOfBookToAvailable(CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(true);
    }

    public void createReturnDate(CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(LocalDate.now().plusWeeks(3));
    }

    public void removeReturnDate(CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(null);
    }

    public void reserveBook(CopyOfBook copyOfBook, LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            if (!copyOfBook.getAvailability()) {
                libraryMember.reservedBooks.add(copyOfBook);
                copyOfBook.listOfReservations.add(libraryMember);
                System.out.println("Your place in the reservation list for this title is : " + copyOfBook.listOfReservations.size());
            }
        }
    }
}
