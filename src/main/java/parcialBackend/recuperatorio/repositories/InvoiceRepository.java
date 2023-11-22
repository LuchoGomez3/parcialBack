package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
