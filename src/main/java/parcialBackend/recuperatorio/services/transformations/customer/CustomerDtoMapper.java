package parcialBackend.recuperatorio.services.transformations.customer;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Customer;
import parcialBackend.recuperatorio.entities.dtos.CustomerDto;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(
                customer.getCustomerID(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCompany(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getCountry(),
                customer.getPostalCode(),
                customer.getPhone(),
                customer.getFax(),
                customer.getEmail(),
                customer.getSupportRepId()
        );
    }
}
