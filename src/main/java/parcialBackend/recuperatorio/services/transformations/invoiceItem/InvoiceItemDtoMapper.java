package parcialBackend.recuperatorio.services.transformations.invoiceItem;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.InvoiceItem;
import parcialBackend.recuperatorio.entities.dtos.InvoiceItemDto;

import java.util.function.Function;

@Service
public class InvoiceItemDtoMapper implements Function<InvoiceItem, InvoiceItemDto> {
    @Override
    public InvoiceItemDto apply(InvoiceItem invoiceItem) {
        return new InvoiceItemDto(
                invoiceItem.getInvoiceLineId(),
                invoiceItem.getInvoice().getInvoiceId(),
                invoiceItem.getTrack().getTrackId(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getQuantity()
        );
    }
}