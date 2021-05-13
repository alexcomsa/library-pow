package ro.alex.comsa.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.alex.comsa.customer.CustomerDto;
import ro.alex.comsa.customer.dao.CustomerModel;
import ro.alex.comsa.customer.dao.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        if (!customerRepository.existsByEmailAddress(customerDto.getEmailAddress())) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setName(customerDto.getName());
            customerModel.setEmailAddress(customerDto.getEmailAddress());
            customerModel.setHomeAddress(customerDto.getHomeAddress());
            customerRepository.save(customerModel);
        } else {
            throw new CustomerExistsException();
        }

    }

    @Override
    public CustomerDto getCustomerById(long id) {
        validateID(id);
        CustomerModel model = customerRepository.findById(id);
        if (model != null) {
            return mapFromModelToDto(model);
        } else throw new NoCustomerFoundExceptiond();

    }


    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) {
        validateID(id);
        customerRepository.updateById(customerDto.getEmailAddress(), customerDto.getHomeAddress(), customerDto.getName(), id);
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        Iterable<CustomerModel> customerModels = customerRepository.findAll();
        return StreamSupport
                .stream(customerModels.spliterator(), false)
                .map(this::mapFromModelToDto)
                .collect(Collectors.toList());
    }

    private void validateID(long id) {
        if (id < 1) {
            throw new InvalidCustomerIdentifierException("Invalid id: " + id);
        }
    }

    private CustomerDto mapFromModelToDto(CustomerModel model) {
        CustomerDto dto = new CustomerDto();
        dto.setId(model.getId());
        dto.setEmailAddress(model.getEmailAddress());
        dto.setHomeAddress(model.getHomeAddress());
        dto.setName(model.getName());
        return dto;
    }
}
