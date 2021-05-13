package ro.alex.comsa.customer.service;


public class InvalidCustomerIdentifierException extends RuntimeException {

    public InvalidCustomerIdentifierException(String message) {
        super(message);
    }
}
