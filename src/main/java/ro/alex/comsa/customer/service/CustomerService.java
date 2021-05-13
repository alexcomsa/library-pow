package ro.alex.comsa.customer.service;

import ro.alex.comsa.customer.CustomerDto;

import java.util.List;

public interface CustomerService {

    void createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(long id);

    void updateCustomer( Long id,CustomerDto customerDto);

    void deleteCustomer(long id);

    List<CustomerDto> getAllCustomers();
}
