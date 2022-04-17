package libraryCatalogue;


public abstract class Book {
    private String title;
    private String author;
    private String isbn;
    private Language language;

    public Book(String title, String author, String isbn, Language language) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Language getLanguage() {
        return language;
    }
}
