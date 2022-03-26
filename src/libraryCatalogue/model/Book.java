package libraryCatalogue.model;


import libraryCatalogue.Language;

public abstract class Book {
    private String title;
    private String author;
    private String isbn;
    private Language language;

    public Book(final String title, final String author, final String isbn, final Language language) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
