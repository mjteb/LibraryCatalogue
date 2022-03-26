package libraryCatalogue;

import libraryCatalogue.model.CopyOfBook;
import libraryCatalogue.model.LibraryMember;

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

    public static ArrayList<LibraryMember> getLibraryMembersList() {
        return libraryMembersList;
    }

    public static double getLibraryAccountBudget() {
        return libraryAccountBudget;
    }


    public static void payingLibraryFees(final LibraryMember libraryMember, final double amountPaid) {
        libraryMember.setLibraryFees(libraryMember.getLibraryFees() - amountPaid);
        libraryAccountBudget += amountPaid;
    }

    public static boolean checkBookAvailability(final CopyOfBook copyOfBook) {
        if (copyOfBook.getAvailability()) {
            return true;
        }
        return false;
    }

    public static void checkOutBook(final LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            for (CopyOfBook eachBook : libraryMember.getBorrowedBooks()) {
                if (checkBookAvailability(eachBook)) {
                    libraryMember.getBooksToBorrow().add(eachBook);
                    changeStatusOfBookToUnavailable(eachBook);
                    createReturnDate(eachBook);
                    System.out.println("You have successfully checked out your books.");
                }
            }
        }
    }

    public static void returnBook(final CopyOfBook copyOfBook, final LibraryMember libraryMember) {
        libraryMember.getBorrowedBooks().remove(copyOfBook);
        changeStatusOfBookToAvailable(copyOfBook);
        removeReturnDate(copyOfBook);
        System.out.println("You have returned your books.");
    }

    public static void changeStatusOfBookToUnavailable(final CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(false);
    }

    public static void changeStatusOfBookToAvailable(final CopyOfBook copyOfBook) {
        copyOfBook.setAvailability(true);
    }

    public static void createReturnDate(final CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(LocalDate.now().plusWeeks(3));
    }

    public static void removeReturnDate(final CopyOfBook copyOfBook) {
        copyOfBook.setDueDate(null);
    }

    public static void reserveBook(final CopyOfBook copyOfBook, final LibraryMember libraryMember) {
        if (libraryMember.verifyAccess()) {
            if (!copyOfBook.getAvailability()) {
                libraryMember.getReservedBooks().add(copyOfBook);
                copyOfBook.getListOfReservations().add(libraryMember);
                System.out.println("\nThe requested title has been reserved. Your place in the reservation list for this title is : " + copyOfBook.getListOfReservations().size());
            }
        }
    }

    public static void addBooksToCatalogue(final List<CopyOfBook> copyOfBookList) {
        copyOfBookList.addAll(copyOfBookList);
    }

    List<CopyOfBook> listOfLateBooks = copyOfBookList.stream()
            .filter(copyOfBook -> LocalDate.now().isAfter(copyOfBook.getDueDate()))
            .toList();
}
