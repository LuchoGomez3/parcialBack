package parcialBackend.recuperatorio.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Customer;
import parcialBackend.recuperatorio.entities.dtos.CustomerDto;
import parcialBackend.recuperatorio.repositories.CustomerRepository;
import parcialBackend.recuperatorio.services.CustomerService;
import parcialBackend.recuperatorio.services.transformations.customer.CustomerDtoMapper;
import parcialBackend.recuperatorio.services.transformations.customer.CustomerMapper;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerDtoMapper customerDtoMapper,
                               CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerMapper = customerMapper;
    }
    @Override

    public void add(CustomerDto entity) {
        Customer customer = Optional.of(entity).map(customerMapper).orElseThrow();
        customerRepository.save(customer);

    }

    @Override
    public CustomerDto getById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customerDtoMapper).orElseThrow();
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers
                .stream()
                .map(customerDtoMapper)
                .toList();
    }

    @Override
    public CustomerDto delete(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        optionalCustomer.ifPresent(customerRepository::delete);

        return optionalCustomer
                .map(customerDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(CustomerDto entity) {
        Customer customer = Optional.of(entity).map(customerMapper).orElseThrow();
        customerRepository.save(customer);
    }
}
