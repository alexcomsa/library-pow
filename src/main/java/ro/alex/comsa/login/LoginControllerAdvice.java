package ro.alex.comsa.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.alex.comsa.customer.service.InvalidCustomerIdentifierException;
import ro.alex.comsa.customer.service.NoCustomerFoundExceptiond;

@ControllerAdvice
public class LoginControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEmailAddressException.class)
    public ResponseEntity<String> handleInvalidID(InvalidEmailAddressException ex, WebRequest request){
        return ResponseEntity.badRequest().body("Invalid email");
    }

}
