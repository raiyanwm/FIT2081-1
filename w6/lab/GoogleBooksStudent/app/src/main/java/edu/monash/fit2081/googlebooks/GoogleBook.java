package edu.monash.fit2081.googlebooks;

public class GoogleBook {
    private String bookTitle;
    private String authors;
    private String publishedDate;

    public GoogleBook(String bookTitle, String authors, String publishedDate) {
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


}
