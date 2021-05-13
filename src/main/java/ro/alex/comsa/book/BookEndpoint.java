package ro.alex.comsa.book;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alex.comsa.book.service.AddNewBookRequest;
import ro.alex.comsa.book.service.BookService;
import ro.alex.comsa.login.service.TokenService;
import ro.alex.comsa.login.VerifyTokenFilter;

import java.util.List;

@Api("Offers operations associated with a book library." +
        " To use the methods in this controller a valid alexc-token must be provided on every request")
@RestController
@RequestMapping("/book")
public class BookEndpoint {

    @Autowired
    public BookEndpoint(BookService bookService, TokenService tokenService) {
        this.bookService = bookService;
        this.tokenService = tokenService;
    }

    private BookService bookService;

    private TokenService tokenService;

    @PostMapping("/borrow/{isbn}")
    public BorrowBookResponse borrowBook(@PathVariable Long isbn,
                                         @RequestHeader(VerifyTokenFilter.TOKEN_HEADER_NAME) String token) {
        return bookService.borrowBook(isbn, tokenService.getUserIdForToken(token));
    }

    @GetMapping("/all")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/return/{isbn}")
    public ReturnBookResponse returnBorrowedBook(@PathVariable Long isbn) {
        return bookService.returnByIsbn(isbn);
    }


    @PostMapping
    public ResponseEntity<Void> addNewBook(@RequestBody AddNewBookRequest newBookRequest,
                                           @RequestHeader(VerifyTokenFilter.TOKEN_HEADER_NAME) String token) {
        bookService.addNewBook(newBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
