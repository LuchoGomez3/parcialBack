package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
}
