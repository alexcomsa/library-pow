package ro.alex.comsa.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alex.comsa.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerEndpoint {


    @Autowired
    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    private final CustomerService customerService;


    @PostMapping
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomers();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
