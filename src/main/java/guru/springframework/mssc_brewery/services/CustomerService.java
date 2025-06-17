package guru.springframework.mssc_brewery.services;

import guru.springframework.mssc_brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {


    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
