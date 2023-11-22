package parcialBackend.recuperatorio.services.transformations.customer;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Customer;
import parcialBackend.recuperatorio.entities.dtos.CustomerDto;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<CustomerDto, Customer> {
    @Override
    public Customer apply(CustomerDto customerDto) {
        return new Customer(
                customerDto.getCustomerID(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getCompany(),
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getState(),
                customerDto.getCountry(),
                customerDto.getPostalCode(),
                customerDto.getPhone(),
                customerDto.getFax(),
                customerDto.getEmail(),
                customerDto.getSupportRepId(),
                null
        );
    }
}