package ro.alex.comsa.book.service;

import ro.alex.comsa.book.BookDto;
import ro.alex.comsa.book.BorrowBookResponse;
import ro.alex.comsa.book.ReturnBookResponse;

import java.util.List;

public interface BookService {

    BorrowBookResponse borrowBook(long isbn, long userId);

    List<BookDto> getAllBooks();

    ReturnBookResponse returnByIsbn(Long isbn);

    ReturnBookResponse returnById(Long id);

    void addNewBook(AddNewBookRequest request);
}
