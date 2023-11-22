package parcialBackend.recuperatorio.services.transformations.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Invoice;
import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;
import parcialBackend.recuperatorio.repositories.CustomerRepository;

import java.util.function.Function;

@Service
public class InvoiceMapper implements Function<InvoiceDto, Invoice> {
    private final CustomerRepository customerRepository;

    @Autowired
    public InvoiceMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Invoice apply(InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getInvoiceId(),
                customerRepository.getReferenceById(invoiceDto.getCustomerId()),
                invoiceDto.getInvoiceDate(),
                invoiceDto.getBillingAddress(),
                invoiceDto.getBillingCity(),
                invoiceDto.getBillingState(),
                invoiceDto.getBillingCountry(),
                invoiceDto.getBillingPostalCode(),
                invoiceDto.getTotal(),
                null
        );
    }
}