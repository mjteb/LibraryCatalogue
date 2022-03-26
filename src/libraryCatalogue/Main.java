package libraryCatalogue;

public class Main {

    public static void main(String[] args) {
        LibraryMember libraryMember1 = new LibraryMember("John", "Doe", "11111995");
        LibraryMember libraryMember2 = new LibraryMember("Marie", "James", "02021963");
        LibraryMember libraryMember3 = new LibraryMember("Louise", "Trembley", "01051970");
        LibraryMember libraryMember4 = new LibraryMember("Elyse", "Smith", "03011952");
        LibraryMember libraryMember5 = new LibraryMember("Elyse", "Smith", "03011952");

        CopyOfBook copyOfBook1 = new CopyOfBook("To kill a mockingbird", "Harper Lee",
                "978-0446310789", "LEE Har to", Language.ENGLISH);
        CopyOfBook copyOfBook2 = new CopyOfBook("Of mice and men", "John Steinbeck",
                "978-0140177398", "STE Joh of", Language.ENGLISH);
        CopyOfBook copyOfBook3 = new CopyOfBook("Les trois mousquetaires", "Alexandre Dumas",
                "978-2253008880", "DUM Ale tr", Language.FRENCH);
        CopyOfBook copyOfBook4 = new CopyOfBook("Une fille comme elle", "Marc Levy",
                "9782266291354", "LEV Mar fi", Language.FRENCH);

        System.out.println(libraryMember5.getCardNumber());


//        for (CopyOfBook e : libraryMember1.borrowedBooks) {
//            System.out.println(e.getTitle());
//        }
//
//        System.out.println(copyOfBook1.getDueDate());
//LibraryManagementSystem.reserveBook(copyOfBook1, libraryMember2);
//
//        for (LibraryMember e : copyOfBook1.listOfReservations) {
//            System.out.println(e.getName());
//        }
//
//
//        LibraryManagementSystem.returnBook(copyOfBook1, libraryMember1);
//libraryMember1.setLibraryFees(50);
//
//LibraryManagementSystem.payingLibraryFees(libraryMember1, 49);
//        libraryMember1.booksToBorrow.add(copyOfBook1);
//        LibraryManagementSystem.checkOutBook(libraryMember1);
//        System.out.println(LibraryManagementSystem.getLibraryAccountBudget());

        LibraryManagementSystem.addBooksToCatalogue(copyOfBook1);
        System.out.println(libraryMember1.getCardNumber());
        copyOfBook1.availability = false;
        SearchCatalogue.libraryMemberLogIn();
    }


}
