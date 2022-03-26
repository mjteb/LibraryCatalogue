package libraryCatalogue;

import libraryCatalogue.errors.InvalidSearchException;
import libraryCatalogue.model.CopyOfBook;
import libraryCatalogue.model.LibraryMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchCatalogue {
    static String libraryMemberCardNumber;

    private SearchCatalogue() {
    }

    public static void libraryMemberLogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your library catalogue. Please enter your library card number to begin");
        libraryMemberCardNumber = scanner.nextLine();
        checkLibraryMemberCardNumber(libraryMemberCardNumber);
        scanner.close();
    }

    public static void checkLibraryMemberCardNumber(final String libraryMemberCardNumber) {
        boolean isLibraryMember = LibraryManagementSystem.getLibraryMembersList().stream()
                .anyMatch(libraryMember -> libraryMember.getCardNumber().equals(libraryMemberCardNumber));
        if (isLibraryMember) {
            startSearch();
        } else {
            throw new InvalidSearchException("\nPlease enter a valid library card number");
        }
    }

    public static void startSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter 1 to search by title or 2 to search by author.");
        int typeOfSearchRequest = scanner.nextInt();
        typeOfSearch(typeOfSearchRequest);
        scanner.close();
    }

    public static void typeOfSearch(final int typeOfSearchRequest) {
        if (typeOfSearchRequest == 1) {
            search("title");
        } else if (typeOfSearchRequest == 2) {
            search("author");
        } else {
            throw new InvalidSearchException("Please select a valid search option.");
        }
    }

    public static void search(final String typeRecherche) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the" + typeRecherche + " you would like to search for.");
        String recherche = scanner.nextLine();
        List<CopyOfBook> searchResults = new ArrayList<>();

        if (typeRecherche.equals("title")) {
            searchResults = LibraryManagementSystem.getCopyOfBookList().stream()
                    .filter(copyOfBook -> copyOfBook.getTitle().equals(recherche))
                    .toList();
        } else if (typeRecherche.equals("author")) {
            searchResults = LibraryManagementSystem.getCopyOfBookList().stream()
                    .filter(copyOfBook -> copyOfBook.getAuthor().equals(recherche))
                    .toList();
        }

        if (searchResults.isEmpty()) {
            System.out.println("\nSorry, your search yielded zero results.");
        } else {
            System.out.println("\nYour search yielded " + searchResults.size() + " result(s):");
            searchResults.forEach(System.out::print);
        }
        requestToCheckAvailability();
        scanner.close();
    }

    public static void requestToCheckAvailability() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nIf you would like to check the availability of a book from your search result. Please enter the barcode.");
        int barcode = scanner.nextInt();
        checkBarcode(barcode);
        scanner.close();
    }

    public static void checkBarcode(final int barcode) {
        boolean bookAvailable = LibraryManagementSystem.getCopyOfBookList().stream()
                .anyMatch(copyOfBook -> (copyOfBook.getBarcode() != barcode));
        if (bookAvailable) {
            checkAvailability(barcode);
        } else {
            throw new InvalidSearchException("Please enter a valid barcode");
        }
    }

    public static void checkAvailability(final int barcode) {
        Scanner scanner = new Scanner(System.in);
        boolean bookAvailable = LibraryManagementSystem.getCopyOfBookList().stream()
                .anyMatch(copyOfBook -> copyOfBook.getAvailability());
        if (bookAvailable) {
            System.out.println("\nThe following book is available. Enter Y to add it to your basket to checkout.");
            scanner.nextLine();
            String addToBasket = scanner.nextLine();
            requestAddingBookToBasket(addToBasket, barcode);
        } else {
            System.out.println("\nThe following book is unavailable. Enter Y to reserve the book or S to make another search.");
            scanner.nextLine();
            String reserveBook = scanner.nextLine();
            if (reserveBook.equals("S")) {
                requestNewSearch();
            } else if (reserveBook.equals("Y")) {
                requestBookReservation(reserveBook, barcode);
            } else {
                throw new InvalidSearchException("Please enter a valid search option");
            }
        }
        scanner.close();
    }


    public static void requestAddingBookToBasket(final String addToBasket, final int barcode) {
        if (addToBasket.equals("Y")) {
            for (LibraryMember member : LibraryManagementSystem.getLibraryMembersList()) {
                for (CopyOfBook copyOfBook : LibraryManagementSystem.getCopyOfBookList())
                    if (member.getCardNumber().equals(libraryMemberCardNumber) && copyOfBook.getBarcode() == barcode) {
                        member.getBooksToBorrow().add(copyOfBook);
                        System.out.println("\nYour book was successfully added to your basket to checkout.");
                        requestNewSearch();
                    }
            }
        } else {
            throw new InvalidSearchException("Please enter a valid request.");
        }
    }

    public static void requestBookReservation(final String reserveBook, final int barcode) {
        if (reserveBook.equals("Y")) {
            for (LibraryMember member : LibraryManagementSystem.getLibraryMembersList()) {
                for (CopyOfBook copyOfBook : LibraryManagementSystem.getCopyOfBookList())
                    if (member.getCardNumber().equals(libraryMemberCardNumber) && copyOfBook.getBarcode() == barcode) {
                        LibraryManagementSystem.reserveBook(copyOfBook, member);
                        requestNewSearch();
                    }
            }
        } else {
            throw new InvalidSearchException("Please enter a valid request.");
        }
    }

    public static void requestNewSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Y to start a new search.");
        String startNewSearch = scanner.nextLine();
        if (startNewSearch.equals("Y")) {
            startSearch();
        }
        scanner.close();
    }
}
