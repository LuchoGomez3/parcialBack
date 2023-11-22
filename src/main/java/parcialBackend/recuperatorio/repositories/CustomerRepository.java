package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
