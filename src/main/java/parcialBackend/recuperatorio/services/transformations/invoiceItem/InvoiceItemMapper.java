package parcialBackend.recuperatorio.services.transformations.invoiceItem;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.InvoiceItem;
import parcialBackend.recuperatorio.entities.dtos.InvoiceItemDto;
import parcialBackend.recuperatorio.repositories.InvoiceRepository;
import parcialBackend.recuperatorio.repositories.TrackRepository;

import java.util.function.Function;

@Service
public class InvoiceItemMapper implements Function<InvoiceItemDto, InvoiceItem> {
    private final InvoiceRepository invoiceRepository;
    private final TrackRepository trackRepository;

    public InvoiceItemMapper(InvoiceRepository invoiceRepository, TrackRepository trackRepository) {
        this.invoiceRepository = invoiceRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public InvoiceItem apply(InvoiceItemDto invoiceItemDto) {
        return new InvoiceItem(
                invoiceItemDto.getInvoiceLineId(),
                invoiceRepository.getReferenceById(invoiceItemDto.getInvoiceId()),
                trackRepository.getReferenceById(invoiceItemDto.getTrackId()),
                invoiceItemDto.getUnitPrice(),
                invoiceItemDto.getQuantity()
        );
    }
}