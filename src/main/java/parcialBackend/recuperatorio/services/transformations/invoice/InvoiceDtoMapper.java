package parcialBackend.recuperatorio.services.transformations.invoice;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Invoice;
import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;

import java.util.function.Function;

@Service
public class InvoiceDtoMapper implements Function<Invoice, InvoiceDto> {
    @Override
    public InvoiceDto apply(Invoice invoice) {
        return new InvoiceDto(
                invoice.getInvoiceId(),
                invoice.getCustomer().getCustomerID(),
                invoice.getInvoiceDate(),
                invoice.getBillingAddress(),
                invoice.getBillingCity(),
                invoice.getBillingState(),
                invoice.getBillingCountry(),
                invoice.getBillingPostalCode(),
                invoice.getTotal()
        );
    }
}