package guru.springframwork.mssc_brewery.web.controller;

import guru.springframwork.mssc_brewery.services.CustomerService;
import guru.springframwork.mssc_brewery.web.model.CustomerDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto>  getCustomerById(@PathVariable UUID customerId){

        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity  handlePost(@Valid @RequestBody CustomerDto customerDto){

        CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+ savedCustomer.getId().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@PathVariable UUID customerId, @Valid @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(customerId,customerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID customerId){

        customerService.deleteCustomer(customerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List>  validationErrorHandler(ConstraintViolationException e){

        List<String>  errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach( constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
}
