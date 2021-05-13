package ro.alex.comsa.book.service;

public class AddNewBookRequest {

    private String author;
    private Long isbn;
    private String publisher;
    private String tile;

    public String getAuthor() {
        return author;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
