package ro.alex.comsa.book.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "book_inventory", indexes = {
        @Index(columnList = "isbn")
})
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_inventory_number")
    private Long libraryInventoryNumber;

    private Long isbn;

    private String title;

    private String author;

    private String publisher;

    private boolean borrowed;

    public Long getLibraryInventoryNumber() {
        return libraryInventoryNumber;
    }

    public void setLibraryInventoryNumber(Long libraryInventoryNumber) {
        this.libraryInventoryNumber = libraryInventoryNumber;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }


}
