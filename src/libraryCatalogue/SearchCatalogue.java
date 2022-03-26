package libraryCatalogue;

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

    public static void checkLibraryMemberCardNumber(String libraryMemberCardNumber) {
        boolean isLibraryMember = LibraryManagementSystem.getLibraryMembersList().stream()
                .anyMatch(libraryMember -> libraryMember.getCardNumber().equals(libraryMemberCardNumber));
        if (isLibraryMember) {
            startSearch();
        } else {
            throw new InvalidSearchError("\nPlease enter a valid library card number");
        }
    }

    public static void startSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter 1 to search by title or 2 to search by author.");
        int typeOfSearchRequest = scanner.nextInt();
        typeOfSearch(typeOfSearchRequest);
        scanner.close();
    }


    public static void typeOfSearch(int typeOfSearchRequest) {
        if (typeOfSearchRequest == 1) {
            searchByTitle();
        } else if (typeOfSearchRequest == 2) {
            searchByAuthor();
        } else {
            throw new InvalidSearchError("Please select a valid search option.");
        }
    }

    public static void searchByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the title you would like to search for.");
        String title = scanner.nextLine();
        List<CopyOfBook> searchResults = LibraryManagementSystem.getCopyOfBookList().stream()
                .filter(copyOfBook -> copyOfBook.getTitle().equals(title))
                .toList();
        if (searchResults.isEmpty()) {
            System.out.println("\nSorry, your search yielded zero results.");
        } else {
            System.out.println("\nYour search yielded " + searchResults.size() + " result(s):");
            searchResults.stream()
                    .forEach(System.out::print);
        }
        requestToCheckAvailability();
        scanner.close();
    }


    public static void searchByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the author you would like to search for.");
        String author = scanner.nextLine();
        List<CopyOfBook> searchResults = LibraryManagementSystem.getCopyOfBookList().stream()
                .filter(copyOfBook -> copyOfBook.getAuthor().equals(author))
                .toList();
        if (searchResults.isEmpty()) {
            System.out.println("\nSorry, your search yielded zero results.");
        } else {
            System.out.println("\nYour search yielded " + searchResults.size() + " result(s):");
            searchResults.stream()
                    .forEach(System.out::print);
            requestToCheckAvailability();
        }
        scanner.close();
    }

    public static void requestToCheckAvailability() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nIf you would like to check the availability of a book from your search result. Please enter the barcode.");
        int barcode = scanner.nextInt();
        checkBarcode(barcode);
        scanner.close();
    }

    public static void checkBarcode(int barcode) {
        boolean bookAvailable = LibraryManagementSystem.getCopyOfBookList().stream()
                .anyMatch(copyOfBook -> (copyOfBook.getBarcode() != barcode));
        if (bookAvailable) {
            checkAvailability(barcode);
        } else {
            throw new InvalidSearchError("Please enter a valid barcode");
        }
    }

    public static void checkAvailability(int barcode) {
        Scanner scanner = new Scanner(System.in);
        boolean bookAvailable = LibraryManagementSystem.getCopyOfBookList().stream()
                .anyMatch(copyOfBook -> copyOfBook.getAvailability());
        if (bookAvailable) {
            System.out.println("\nThe following book is available. Enter Y to add it to your basket to checkout.");
            String reset = scanner.nextLine();
            String addToBasket = scanner.nextLine();
            requestAddingBookToBasket(addToBasket, barcode);
        } else {
            System.out.println("\nThe following book is unavailable. Enter Y to reserve the book or S to make another search.");
            String reset2 = scanner.nextLine();
            String reserveBook = scanner.nextLine();
            if (reserveBook.equals("S")) {
                requestNewSearch();
            } else if (reserveBook.equals("Y")) {
                requestBookReservation(reserveBook, barcode);
            } else {
                throw new InvalidSearchError("Please enter a valid search option");
            }
        }
        scanner.close();
    }


    public static void requestAddingBookToBasket(String addToBasket, int barcode) {
        if (addToBasket.equals("Y")) {
            for (LibraryMember member : LibraryManagementSystem.getLibraryMembersList()) {
                for (CopyOfBook copyOfBook : LibraryManagementSystem.getCopyOfBookList())
                    if (member.getCardNumber().equals(libraryMemberCardNumber) && copyOfBook.getBarcode() == barcode) {
                        member.booksToBorrow.add(copyOfBook);
                        System.out.println("\nYour book was successfully added to your basket to checkout.");
                        requestNewSearch();
                    }
            }
        } else {
            throw new InvalidSearchError("Please enter a valid request.");
        }
    }

    public static void requestBookReservation(String reserveBook, int barcode) {
        if (reserveBook.equals("Y")) {
            for (LibraryMember member : LibraryManagementSystem.getLibraryMembersList()) {
                for (CopyOfBook copyOfBook : LibraryManagementSystem.getCopyOfBookList())
                    if (member.getCardNumber().equals(libraryMemberCardNumber) && copyOfBook.getBarcode() == barcode) {
                        LibraryManagementSystem.reserveBook(copyOfBook, member);
                        requestNewSearch();
                    }
            }
        } else {
            throw new InvalidSearchError("Please enter a valid request.");
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
