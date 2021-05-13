package ro.alex.comsa.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.alex.comsa.customer.service.CustomerExistsException;
import ro.alex.comsa.customer.service.InvalidCustomerIdentifierException;
import ro.alex.comsa.customer.service.NoCustomerFoundExceptiond;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCustomerIdentifierException.class)
    public ResponseEntity<String> handleInvalidID(InvalidCustomerIdentifierException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Invalid ID");
    }


    @ExceptionHandler(NoCustomerFoundExceptiond.class)
    public ResponseEntity<String> handleNoCustomerFound(NoCustomerFoundExceptiond ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<String> handleExistingCustomer(CustomerExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Provided email address is already in use");
    }
}
