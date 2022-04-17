package libraryCatalogue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private static ArrayList<LibraryMember> libraryMembersList = new ArrayList<>();
    private static ArrayList<CopyOfBook> copyOfBookList = new ArrayList<>();
private static double libraryAccountBudget;

    private LibraryManagementSystem() {
    }

    public static ArrayList<CopyOfBook> getCopyOfBookList() {
        return copyOfBookList;
    }

    public static void setCopyOfBookList(ArrayList<CopyOfBook> copyOfBookList) {
        LibraryManagementSystem.copyOfBookList = copyOfBookList;
    }

    public static ArrayList<LibraryMember> getLibraryMembersList() {
        return libraryMembersList;
    }

    public static void payingLibraryFees (LibraryMember libraryMember, double amountPaid) {
        libraryMember.setLibraryFees(libraryMember.getLibraryFees() - amountPaid); ;
        libraryAccountBudget += amountPaid;
    }

    public static boolean checkBookAvailability(CopyOfBook copyOfBook) {
        if (copyOfBook.getAvailability()) {
            return true;
        }
        return false;
    }

    public static void checkOutBook(LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            for (CopyOfBook eachBook : libraryMember.booksToBorrow) {
                if (checkBookAvailability(eachBook)) {
                    libraryMember.borrowedBooks.add(eachBook);
                    changeStatusOfBookToUnavailable(eachBook);
                    createReturnDate(eachBook);
                    System.out.println("You have successfully checked out your books.");
                }
            }
        }
    }

    public static void returnBook(CopyOfBook copyOfBook, LibraryMember libraryMember) {
        libraryMember.borrowedBooks.remove(copyOfBook);
        changeStatusOfBookToAvailable(copyOfBook);
        removeReturnDate(copyOfBook);
        System.out.println("You have returned your books.");
    }

    public static void changeStatusOfBookToUnavailable(CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(false);
    }

    public static void changeStatusOfBookToAvailable(CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(true);
    }

    public static void createReturnDate(CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(LocalDate.now().plusWeeks(3));
    }

    public static void removeReturnDate(CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(null);
    }

    public static void reserveBook(CopyOfBook copyOfBook, LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            if (!copyOfBook.getAvailability()) {
                libraryMember.reservedBooks.add(copyOfBook);
                copyOfBook.listOfReservations.add(libraryMember);
                System.out.println("\nThe requested title has been reserved. Your place in the reservation list for this title is : " + copyOfBook.listOfReservations.size());
            }
        }
    }

    public static void addBooksToCatalogue(CopyOfBook copyOfBook) {
        copyOfBookList.add(copyOfBook);
    }

    List<CopyOfBook> listOfLateBooks = copyOfBookList.stream()
            .filter(copyOfBook -> LocalDate.now().isAfter(copyOfBook.getDueDate()))
            .toList();
}
