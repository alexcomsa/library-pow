package ro.alex.comsa.book;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
