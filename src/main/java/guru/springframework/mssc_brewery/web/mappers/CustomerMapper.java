package guru.springframework.mssc_brewery.web.mappers;

import guru.springframework.mssc_brewery.domain.Customer;
import guru.springframework.mssc_brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);
    Customer    customerDtoToCustomer(CustomerDto customerDto);
}
