package ro.alex.comsa.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.alex.comsa.book.BookDto;
import ro.alex.comsa.book.BookNotAvailableException;
import ro.alex.comsa.book.BorrowBookResponse;
import ro.alex.comsa.book.ReturnBookResponse;
import ro.alex.comsa.book.dao.BookInventoryRepository;
import ro.alex.comsa.book.dao.BookModel;
import ro.alex.comsa.book.dao.BorrowedBookRepository;
import ro.alex.comsa.book.dao.BorrowedBookStatusModel;
import ro.alex.comsa.customer.dao.CustomerModel;
import ro.alex.comsa.customer.dao.CustomerRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookServiceImpl(BookInventoryRepository bookInventory, BorrowedBookRepository borrowedBookRepository, CustomerRepository customerDAO) {
        this.bookInventory = bookInventory;
        this.borrowedBookRepository = borrowedBookRepository;
        this.customerDAO = customerDAO;
    }

    private BookInventoryRepository bookInventory;
    private BorrowedBookRepository borrowedBookRepository;
    private CustomerRepository customerDAO;

    @Override
    @Transactional
    public BorrowBookResponse borrowBook(long isbn, long userId) {

        List<BookModel> toLendOfOne = bookInventory.reserveBook(isbn, PageRequest.of(0, 1));
        if (!toLendOfOne.isEmpty()) {
            BookModel book = toLendOfOne.get(0);
            book.setBorrowed(Boolean.TRUE);
            bookInventory.save(book);
            CustomerModel customerModel = customerDAO.findById(userId);
            BorrowedBookStatusModel borrowedBookStatusModel = new BorrowedBookStatusModel();
            borrowedBookStatusModel.setBook(book);
            borrowedBookStatusModel.setBorrowDate(Timestamp.from(Instant.now()));
            borrowedBookStatusModel.setCustomer(customerModel);
            borrowedBookStatusModel = borrowedBookRepository.save(borrowedBookStatusModel);
            return new BorrowBookResponse(borrowedBookStatusModel.getId(), book.getLibraryInventoryNumber());
        } else throw new BookNotAvailableException("No books with isbn " + isbn + " are available for lending");
    }

    @Override
    public List<BookDto> getAllBooks() {
        Iterable<BookModel> allBooks = bookInventory.findAll();
        return StreamSupport
                .stream(allBooks.spliterator(), false)
                .map(this::fromBookModelToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReturnBookResponse returnByIsbn(Long isbn) {
        List<BorrowedBookStatusModel> books = borrowedBookRepository.findByIsbnAndUserId(isbn, 1L);
        return doReturn(books.get(0));
    }

    @Transactional
    @Override
    public ReturnBookResponse returnById(Long id) {
        Optional<BorrowedBookStatusModel> book = borrowedBookRepository.findById(id);
        if (book.isPresent()) {
            return doReturn(book.get());
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public void addNewBook(AddNewBookRequest request) {
        BookModel bookModel = new BookModel();
        bookModel.setBorrowed(Boolean.FALSE);
        bookModel.setAuthor(request.getAuthor());
        bookModel.setIsbn(request.getIsbn());
        bookModel.setPublisher(request.getPublisher());
        bookModel.setTitle(request.getTile());
    }


    private ReturnBookResponse doReturn(BorrowedBookStatusModel bookStatusModel) {
        BookModel bookModel = bookStatusModel.getBook();
        bookModel.setBorrowed(Boolean.FALSE);
        bookInventory.save(bookModel);
        bookStatusModel.setReturnDate(Timestamp.from(Instant.now()));
        bookInventory.save(bookModel);
        return new ReturnBookResponse(true);
    }

    private BookDto fromBookModelToDto(BookModel model) {
        BookDto dto = new BookDto();
        dto.setAuthor(model.getAuthor());
        dto.setBookId(model.getLibraryInventoryNumber());
        dto.setIsbn(model.getIsbn());
        dto.setTitle(model.getTitle());
        dto.setPublisher(model.getPublisher());
        return dto;
    }
}
